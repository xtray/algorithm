package _OOD.YellowBicycle;

public abstract class Trip {

    private Station start; // 出发车站
    private Station end; // 终止车站

    public Station getStart() {
        return start;
    }

    public Station getEnd() {
        return end;
    }

    abstract int getSpeed();
    abstract int getTripStartTime();
    abstract int getLoad();

    public Trip(Station start, Station end) {
        this.start = start;
        this.end = end;
    }

}
