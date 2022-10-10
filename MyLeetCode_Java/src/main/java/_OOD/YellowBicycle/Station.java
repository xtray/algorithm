package _OOD.YellowBicycle;

public class Station {
    private int x;
    private int y;
    private int amount;
    private String name;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    // 向车站增加车辆
    public void addBicycle(int add) {
        amount += add;
    }

    // 从车站挪走车
    public void removeBicycle(int minus) {
        amount -= minus;
    }

    public Station(int x, int y, int amount, String name) {
        this.x = x;
        this.y = y;
        this.amount = amount;
        this.name = name;
    }


}
