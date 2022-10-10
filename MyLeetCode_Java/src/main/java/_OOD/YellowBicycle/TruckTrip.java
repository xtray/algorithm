package _OOD.YellowBicycle;

public class TruckTrip extends Trip {

    private int speed;
    private int startTime;
    private int load; // 预装载量
    private Truck truck; // 使用的卡车

    @Override
    int getSpeed() {
        return speed;
    }

    @Override
    int getTripStartTime() {
        return startTime;
    }

    @Override
    int getLoad() {
        return load;
    }

    public Truck getTruck() {
        return truck;
    }

    public TruckTrip(Station start, Station end, int time, int speed, int load, Truck truck) {
        super(start, end);
        this.speed = speed;
        this.startTime = time;
        this.load = load;
        this.truck = truck;
    }
}
