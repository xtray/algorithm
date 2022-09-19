package _Contest.LC.W294;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem_2280_MinLines {

    public int minimumLines(int[][] points) {
        if (points.length <= 1) {
            return 0;
        }

        // 按时间排序
        Arrays.sort(points, (o1, o2) -> o1[0] - o2[0]);
        Map<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer, Integer>>();
        int preX = points[0][0];
        int preY = points[0][1];
        String preSlope = "";
        int segNum = 0;
        for (int i = 1; i < points.length; i++) {
            int curX = points[i][0];
            int curY = points[i][1];
            curX -= preX;
            curY -= preY;
            int gcd = gcd(curX, curY);
            curX /= gcd;
            curY /= gcd;
            String curSlope = String.valueOf(curX) + "_" + String.valueOf(curY);
            if (!curSlope.equals(preSlope)) {
                segNum++;
                preSlope = curSlope;
            }
            preX = points[i][0];
            preY = points[i][1];
        }
        return segNum;
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        // int[][] points = {{1, 7}, {2, 6}, {3, 5}, {4, 4}, {5, 4}, {6, 3}, {7, 2}, {8, 1}};
        int[][] points = {{3,4},{1,2},{7,8},{2,3}};
        var ans = new Problem_2280_MinLines().minimumLines(points);
        System.out.println(ans);
    }
}
