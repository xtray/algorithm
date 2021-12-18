package LeetCode;

public class Problem_1266_MinTimeToVisitAllPoints {


    public static int minTimeToVisitAllPoints(int[][] points) {
        if(points == null || points.length == 0) {
            return -1;
        }
        // 出发点
        int fromX = points[0][0];
        int fromY = points[0][1];
        int time = 0;
        // 水平, 垂直, 对角线, 斜线错开
        for(int i=1;i<points.length;i++) {
            int toX = points[i][0];
            int toY = points[i][1];
            int gapX = Math.abs(toX - fromX);
            int gapY = Math.abs(toY - fromY);
            if (gapX == 0 || gapY == 0) {
                time += gapX + gapY;
            } else if (gapX == gapY) {
                time += gapX;
            } else {
                time += Math.max(gapX, gapY);
            }
            fromX = toX;
            fromY = toY;
        }
        return time;
    }

    public static void main(String[] args) {
        int[][]points = new int[][] {{3,2},{-2,2}};
        int ans =minTimeToVisitAllPoints(points);
        System.out.println(ans);
    }
}
