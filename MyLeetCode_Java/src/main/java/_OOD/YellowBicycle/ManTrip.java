package _OOD.YellowBicycle;

public class ManTrip extends Trip {

    private int speed;
    private int startTime;
    private int load;

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

    public ManTrip(Station start, Station end, int time, int speed, int load) {
        super(start, end);
        this.startTime = time;
        this.speed = speed;
        this.load = load;
    }

}
