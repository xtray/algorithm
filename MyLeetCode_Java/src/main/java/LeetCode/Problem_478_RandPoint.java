package LeetCode;

import java.util.Random;

public class Problem_478_RandPoint {

    private Random random;
    private double size, x, y;

    public Problem_478_RandPoint(double radius, double x_center, double y_center) {
        x = x_center;
        y = y_center;
        size = Math.PI * radius * radius;
        random = new Random();
    }

    public double[] randPoint() {
        double theta = random.nextDouble() * 2 * Math.PI, r = Math.sqrt(random.nextDouble() * size / Math.PI);
        return new double[]{x + Math.cos(theta) * r, y + Math.sin(theta) * r};
    }
}
