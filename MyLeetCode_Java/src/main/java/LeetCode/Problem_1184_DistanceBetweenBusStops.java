package LeetCode;

import java.util.Arrays;

public class Problem_1184_DistanceBetweenBusStops {

    public int distanceBetweenBusStops(int[] dist, int start, int dest) {
        // int total = Arrays.stream(dist).sum();
        int total = 0;
        for(int num : dist) {
            total += num;
        }
        int s = Math.min(start, dest);
        int e = s == start ? dest : start;
        int n = dist.length;
        int d = 0;
        for (int i = 0; i < n; i++) {
            if (i >= s && i<e) {
                d += dist[i];
            } else if (i == e) {
                break;
            }
        }
        return Math.min(d, total - d);
    }
}
