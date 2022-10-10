package _Contest.LCP.LCP2022_FALL;

import java.util.*;

public class Problem_B {


    // 所有地点（除自身外）均有一条 单向 专线 直接 通往该地点；

    public int transportationHub(int[][] path) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inMap = new HashMap<>();
        Map<Integer, Integer> outMap = new HashMap<>();

        for (int[] p : path) {
            int from = p[0];
            int to = p[1];
            graph.computeIfAbsent(to, k -> new ArrayList<>());
            graph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);

            inMap.put(to, inMap.getOrDefault(to, 0) + 1);
            outMap.put(from, outMap.getOrDefault(from, 0) + 1);
        }
        int maxIn = 0;
        int maxInNode = -1;
        int outCnt = 0;
        for (int node : graph.keySet()) {
            if (inMap.getOrDefault(node, 0) > maxIn) {
                maxIn = inMap.getOrDefault(node, 0);
                maxInNode = node;
            }
            if (outMap.getOrDefault(node, 0) == 0) {
                outCnt++;
            }
        }

        if (outMap.getOrDefault(maxInNode, 0) != 0 || outCnt != 1) {
            return -1;
        }

        // 所有地点（除自身外）均有一条 单向 专线 直接 通往该地点；
        for (int node : graph.keySet()) {
            if (node == maxInNode) continue;
            Set<Integer> set = new HashSet<>(graph.getOrDefault(node, new ArrayList<>()));
            if (!set.contains(maxInNode)) {
                return -1;
            }
        }
        return maxInNode;
    }

    public static void main(String[] args) {
        // int[][] path = {{0, 1}, {0, 3}, {1, 3}, {2, 0}, {2, 3}}; //3
        // int[][] path = {{2, 5}, {4, 3}, {2, 3}}; // -1
        // int[][] path = {{2, 5}, {4, 3}, {2, 3}}; // -1
        // int[][] path = {{2, 4}, {3, 4}, {1, 4}, {0, 3}, {3, 0}, {0, 4}}; // 4
        int[][] path = {{1, 3}, {1, 2}, {0, 2}, {3, 2}}; // 2
        var ans = new Problem_B().transportationHub(path);
        System.out.println(ans);
    }
}
