package _OOD.YellowBicycle;

import java.util.List;

public class MainApplication {

    public static void main(String[] args) {

        int timeLineStart = 0;
        Scheduler scheduler = new Scheduler(timeLineStart);

        // 加载3个车站
        Station stationA = new Station(0, 0, 30, "A");
        Station stationB = new Station(7, 7, 40, "B");
        Station stationC = new Station(3, 2, 30, "C");
        scheduler.addStation(stationA);
        scheduler.addStation(stationB);
        scheduler.addStation(stationC);

        // 加载货车
        Truck truck1 = new Truck(20); // 容量只用来校验合法性
        Truck truck2 = new Truck(20);
        scheduler.addTruck(truck1);
        scheduler.addTruck(truck2);

        List<Station> stationList = scheduler.getStations();

        // int runningSession = 200; // 最大运行时间
        int runningSession = 200; // 最大运行时间
        int maxLoad = 40; // 卡车的最大装货数量
        int truckSpeed = 3; // 卡车速度
        int manSpeed = 1; // 人速度
        int manLoad = 1;

        for (int time = timeLineStart; time < timeLineStart + runningSession; time++) {
            List<Truck> availableTruckList = scheduler.getAvailTrucks(); // 当前可以派单的卡车
            // 1.每秒从可用的Truck里随机一个车生成运货Trip
            if (!availableTruckList.isEmpty()) {
                int size = availableTruckList.size();
                int randId = (int) (Math.random() * size);
                Truck truck = availableTruckList.get(randId);
                // 随机生成车站
                Station[] randStations = genRandStations(stationList);
                // 生成运货Trip
                int load = (int) (Math.random() * maxLoad) + 1;
                if (load > truck.getCapacity()) {
                    // System.out.println("无效的Trip, Skip");
                } else {
                    Trip truckTrip = new TruckTrip(randStations[0], randStations[1], time, truckSpeed, load, truck);
                    // 处理Trip
                    try {
                        scheduler.handleTrip(truckTrip);
                    } catch (IllegalAmountException e) {
                        // System.out.println(e.getMessage());
                    }
                }
            }
            // 2. 每秒一个生成一个乘客的Trip
            // 随机生成车站
            Station[] randStations = genRandStations(stationList);
            // 生成Trip
            Trip manTrip = new ManTrip(randStations[0], randStations[1], time, manSpeed, manLoad);
            // 处理Trip
            try {
                scheduler.handleTrip(manTrip);
            } catch (IllegalAmountException e) {
                // System.out.println(e.getMessage());
            }
            // 生成当前时间的报告
            scheduler.genReport(time);
        }
    }

    // 每次随机两个车站
    private static Station[] genRandStations(List<Station> stationList) {
        int size = stationList.size();
        int randId1 = (int) (Math.random() * size);
        int randId2 = randId1;
        while (randId2 == randId1) {
            randId2 = (int) (Math.random() * size);
        }
        return new Station[]{stationList.get(randId1), stationList.get(randId2)};
    }
}
