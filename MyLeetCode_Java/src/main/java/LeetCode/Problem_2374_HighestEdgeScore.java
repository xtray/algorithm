package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class Problem_2374_HighestEdgeScore {

    public int edgeScore(int[] edges) {
        int N = edges.length;
        // [idx, score]
        Map<Integer, Long> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            // i : from
            int to = edges[i];
            // from -> to, to: i
            map.put(to, map.getOrDefault(to, 0L) + i);
        }
        long maxScore = 0;
        int maxIdx = -1;
        for (int to : map.keySet()) {
            if (map.get(to) > maxScore) {
                maxScore = map.get(to);
                maxIdx = to;
            }
        }
        return maxIdx;
    }

    public int edgeScore1(int[] edges) {
        int N = edges.length;
        // [idx, score]
        Map<Integer, Long> map = new HashMap<>();
        long maxScore = 0;
        int maxIdx = N;
        for (int i = 0; i < N; i++) {
            // i : from
            int to = edges[i];
            // from -> to, to: i
            map.put(to, map.getOrDefault(to, 0L) + i);
            if (map.get(to) > maxScore || (map.get(to) == maxScore && to < maxIdx)) {
                maxScore = map.get(to);
                maxIdx = to;
            }
        }
        return maxIdx;
    }

    public static void main(String[] args) {
        // int[] edges = {3, 3, 3, 0}; // 0
        int[] edges = {1, 0, 0, 0, 0, 7, 7, 5}; // 7
        var ans = new Problem_2374_HighestEdgeScore().edgeScore(edges);
        System.out.println(ans);

        var ans1 = new Problem_2374_HighestEdgeScore().edgeScore1(edges);
        System.out.println(ans1);
    }
}
