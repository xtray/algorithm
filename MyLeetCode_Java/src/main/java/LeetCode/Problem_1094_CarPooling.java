package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_1094_CarPooling {

    // 扫描线
    public static boolean carPooling(int[][] trips, int capacity) {
        if (trips == null || trips.length == 0 || trips[0] == null || trips[0].length == 0 || capacity < 1) {
            return false;
        }
        List<int[]> list = new ArrayList<>();
        for (int[] trip : trips) {
            list.add(new int[]{trip[1], trip[0]});
            list.add(new int[]{trip[2], -trip[0]});
        }
        list.sort((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        int maxCap = 0;
        int onSeat = 0;
        for (int[] item : list) {
            onSeat += item[1];
            maxCap = Math.max(maxCap, onSeat);
        }
        return maxCap <= capacity;
    }

    public static void main(String[] args) {
        int[][] trips = {{2,1,5},{3,3,7}};
        int capacity = 4;
        var ans = carPooling(trips, capacity);
        System.out.println(ans);
    }
}
