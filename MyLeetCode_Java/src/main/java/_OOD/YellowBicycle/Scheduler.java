package _OOD.YellowBicycle;

import java.util.*;

public class Scheduler {

    private List<Station> stations;
    // [ 卡车, 是否可用]
    private Map<Truck, Boolean> trucks;
    private int lastReportTime; // 上次开始生成报告的时间


    private TreeMap<Integer, List<Trip>> tripManager;

    public Scheduler(int startTime) {
        lastReportTime = startTime - 1;
        stations = new ArrayList<>();
        trucks = new HashMap<>();
        tripManager = new TreeMap<>();
    }

    public List<Station> getStations() {
        return stations;
    }

    public void addStation(Station station) {
        stations.add(station);
    }

    // 找到可用的卡车
    public List<Truck> getAvailTrucks() {
        List<Truck> list = new ArrayList<>();
        for (Truck truck : trucks.keySet()) {
            if (trucks.get(truck)) {
                list.add(truck);
            }
        }
        return list;
    }

    public void addTruck(Truck truck) {
        trucks.put(truck, true);
    }

    public void handleTrip(Trip trip) throws IllegalAmountException {
        int curTimeline = trip.getTripStartTime(); // 当前系统运行到的时间

        // 1.检查trip有效性
        Station start = trip.getStart();
        if (trip.getLoad() > start.getAmount()) {
            String name = start.getName();
            throw new IllegalAmountException("车站 " + name + " 没有足够的车辆, 当前Trip丢弃.");
        }
        if (trip instanceof TruckTrip) {
            trucks.put(((TruckTrip) trip).getTruck(), false);
        }
        // 2.调整Station车子数量
        start.removeBicycle(trip.getLoad());
        // 3. Trip按到达时间加入管理表
        int tripEndTime = curTimeline + getManhattanDistance(trip.getStart(), trip.getEnd()) / trip.getSpeed();
        tripManager.computeIfAbsent(tripEndTime, k -> new ArrayList<>()).add(trip);
    }

    // 生成 lastReportTime ~ curTimeline 报告
    public void genReport(int curTimeline) {
        //   在路上: 人, 卡车两种类型
        for (int time = lastReportTime + 1; time <= curTimeline; time++) {
            // 把<= 当前时间的trip全部取出来
            List<Trip> curSecTrip = tripManager.get(time); // 当前秒到站的trip
            // List<Trip> removeTrip = new ArrayList<>();
            tripManager.remove(time);
            if (curSecTrip != null) {
                // 把所有到站trip的load加到对应车站amount上
                for (Trip t : curSecTrip) {
                    Station endStation = t.getEnd();
                    int load = t.getLoad();
                    endStation.addBicycle(load);
                    // removeTrip.add(t);
                    if (t instanceof TruckTrip) {
                        trucks.put(((TruckTrip) t).getTruck(), true);
                    }
                }
                // curSecTrip.removeAll(removeTrip);
            }
            genSecReport(time);
        }
        lastReportTime = curTimeline;
    }

    // 生成指定秒的报告
    private void genSecReport(int time) {

        int manOnRoad = 0;
        StringBuilder sb = new StringBuilder();
        StringBuilder truckSb = new StringBuilder();
        StringBuilder stationSb = new StringBuilder();
        for (Station station : stations) {
            stationSb.append(station.getName()).append(" 站车 ");
            stationSb.append(station.getAmount()).append(", ");
        }

        for (List<Trip> list : tripManager.values()) {
            for (Trip trip : list) {
                if (trip instanceof ManTrip) {
                    manOnRoad++;
                }
                if (trip instanceof TruckTrip) {
                    truckSb.append(trip.getStart().getName()).append(" 到 ");
                    truckSb.append(trip.getEnd().getName()).append(" 运输 ");
                    truckSb.append(trip.getLoad()).append(" 量车, ");
                }
            }
        }
        sb.append(genTimeStr(time)).append(stationSb).append("路上车 ").append(manOnRoad).append(", ").append(truckSb);
        int len = sb.length();
        if (len != 0 && sb.charAt(len - 2) == ',') {
            sb.deleteCharAt(len - 2);
        }
        System.out.println(sb);
    }

    private String genTimeStr(int time) {
        int min = time / 60;
        int sec = time % 60;
        return String.format("%02d:", min) + String.format("%02d:", sec) + "00 ";
    }

    private int getManhattanDistance(Station A, Station B) {
        return Math.abs(A.getX() - B.getX()) + Math.abs(A.getY() - B.getY());
    }


}
