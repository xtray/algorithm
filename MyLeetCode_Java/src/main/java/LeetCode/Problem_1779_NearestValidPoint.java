package LeetCode;

public class Problem_1779_NearestValidPoint {

    public int nearestValidPoint(int x, int y, int[][] points) {
        if(points == null || points.length ==0) {
            return 0;
        }
        int manDis = Integer.MAX_VALUE;
        int idx = -1;
        for(int i =0; i< points.length;i++) {
            if(points[i][0] == x || points[i][1] == y) {
                int curDis = getManDis(x,y, points[i][0], points[i][1]);
                if(curDis < manDis) {
                    manDis = curDis;
                    idx = i;
                } else if(curDis == manDis && i < idx) {
                    idx = i;
                }
            }
        }
        return idx;
    }

    private int getManDis(int x, int y, int curX, int curY) {
        return Math.abs(curX - x) + Math.abs(curY - y);
    }
}
