package LeetCode.JZOffer;

import java.util.*;

public class Problem_JZII115_ReconstructionSeq {


    // 若按照拓扑排序，入度为0的点只有一个，则一定是一个最短的超序列，否则不是。

    // 拓扑排序
    // 1.根据seq构建邻接表
    // 2.统计每个节点的入度, 入度为零的放前面
    public boolean sequenceReconstruction(int[] nums, int[][] sequences) {
        Map<Integer, List<Integer>> map = new HashMap<>(); // 邻接表
        Map<Integer, Integer> inMap = new HashMap<>(); // 每个点的入度表
        Set<Integer> set = new HashSet<>();
        for (int[] s : sequences) {
            int len = s.length;
            for (int i = 1; i < len; i++) {
                int from = s[i-1];
                int to = s[i];
                set.add(from);
                set.add(to);
                map.computeIfAbsent(from, key -> new ArrayList<>()).add(to);
                inMap.put(to, inMap.getOrDefault(to, 0) + 1);
            }
        }
        // 如果seq里的元素数< n个, 说明nums必无效
        if (set.size() < nums.length) return false;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int num : nums) { // 1...n所有整数
            if (inMap.getOrDefault(num, 0) == 0) {
                queue.add(num); // 入度为零的进队列
            }
        }
        while (!queue.isEmpty()) {
            if (queue.size() > 1) return false; // 有1个以上0入度, 序列不唯一
            int cur = queue.poll();
            for (int next : map.getOrDefault(cur, new ArrayList<>())) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    queue.add(next);
                }
            }
        }
        return true;
    }

    public boolean sequenceReconstruction1(int[] nums, int[][] sequences) {
        Map<Integer, List<Integer>> map = new HashMap<>(); // 邻接表
        int N = nums.length;
        int[] inMap = new int[N + 1];
        Set<Integer> set = new HashSet<>(); // 大过滤, seq里数字出现个数, 如果<nums必然false
        for (int[] s : sequences) {
            int len = s.length;
            set.add(s[0]);
            for (int i = 1; i < len; i++) {
                set.add(s[i]);
                int from = s[i-1];
                int to = s[i];
                map.computeIfAbsent(from, key -> new ArrayList<>()).add(to);
                inMap[to]++;
            }
        }
        if(set.size()< N) return false; // 如果seq里的元素数< n个, 说明nums必无效
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int num : nums) { // 1...n所有整数
            if (inMap[num] == 0) {
                queue.add(num); // 入度为零的进队列
            }
        }
        while (!queue.isEmpty()) {
            if (queue.size() > 1) return false; // 有1个以上入度为零的点, 序列不唯一
            int cur = queue.poll();
            // 没有下级的直接跳过
            for (int next : map.getOrDefault(cur, new ArrayList<>())) {
                inMap[next]--;
                if (inMap[next] == 0) {
                    queue.add(next);
                }
            }
        }
        return true;
    }

    public boolean sequenceReconstruction2(int[] nums, int[][] sequences) {
        Map<Integer, List<Integer>> map = new HashMap<>(); // 邻接表
        int[] inMap = new int[nums.length + 1];
        for (int[] s : sequences) {
            int len = s.length;
            for (int i = 1; i < len; i++) {
                int from = s[i-1];
                int to = s[i];
                // map.computeIfAbsent(from, key -> new ArrayList<>()).add(to);
                if(!map.containsKey(from)) {
                    map.put(from, new ArrayList<>());
                }
                map.get(from).add(to);
                inMap[to]++;
            }
        }
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int num : nums) { // 1...n所有整数
            if (inMap[num] == 0) {
                queue.add(num); // 入度为零的进队列
            }
        }
        while (!queue.isEmpty()) {
            if (queue.size() > 1) return false; // 有1个以上入度为零的点, 序列不唯一
            int cur = queue.poll();
            // 没有下级的直接跳过
            for (int next : map.getOrDefault(cur, new ArrayList<>())) {
                inMap[next]--;
                if (inMap[next] == 0) {
                    queue.add(next);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // int[] nums = {1,2,3};
        // int[][] seq = {{1,2},{1,3},{2,3}}; // true

        int[] nums = {1,2,3};
        int[][] seq = {{1,2},{1,3}}; //false

        // int[] nums = {4,1,5,2,6,3};
        // int[][] seq = {{5,2,6,3},{4,1,5,2}}; // true

        // int[] nums = {1};
        // int[][] seq = {{1}}; // true


        // int[] nums = {1, 4, 2, 3};
        // int[][] seq = {{1, 2}, {1, 3}, {2, 3}, {4, 2}}; // false
        var ans = new Problem_JZII115_ReconstructionSeq().sequenceReconstruction(nums, seq);
        System.out.println(ans);
    }
}
