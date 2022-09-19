package _Contest.LC.W305;

import java.util.*;

public class Problem_2368_ReachableNodes {


    // 总的点数 - 受限的访问到的点数
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] e : edges) {
            int from = e[0];
            int to = e[1];
            map.computeIfAbsent(from, key -> new ArrayList<>()).add(to);
            map.computeIfAbsent(to, key -> new ArrayList<>()).add(from);
        }
        Set<Integer> set = new HashSet<>();
        Set<Integer> used = new HashSet<>();
        for (int num : restricted) set.add(num);
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        used.add(0);
        int cnt = 1;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : map.getOrDefault(cur, new ArrayList<>())) {
                if (used.contains(next) || set.contains(next)) continue;
                used.add(next);
                cnt++;
                queue.add(next);
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int n = 7;
        int[][] edges = {{0, 1}, {1, 2}, {3, 1}, {4, 0}, {0, 5}, {5, 6}};
        int[] restricted = {4, 5};
        var ans = new Problem_2368_ReachableNodes().reachableNodes(n, edges, restricted);
        System.out.println(ans);
    }
}
