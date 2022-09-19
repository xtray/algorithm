package LeetCode;

import _Exercise.Problem_444_SequenceReconsttuction;

import java.util.*;

public class Problem_444_ReconstructionSeq {


    // 若按照拓扑排序，入度为0的点只有一个，则一定是一个最短的超序列，否则不是。

    // 拓扑排序
    // 1.根据seq构建邻接表
    // 2.统计每个节点的入度, 入度为零的放前面
    public boolean sequenceReconstruction(int[] nums, List<List<Integer>> sequences) {
        Map<Integer, List<Integer>> map = new HashMap<>(); // 邻接表
        Map<Integer, Integer> inMap = new HashMap<>(); // 每个点的入度表
        Set<Integer> set = new HashSet<>();
        for (List<Integer> s : sequences) {
            int len = s.size();
            set.add(s.get(0));
            for (int i = 1; i < len; i++) {
                set.add(s.get(i));
                int from = s.get(i - 1);
                int to = s.get(i);
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

    public boolean sequenceReconstruction1(int[] nums, List<List<Integer>> sequences) {
        Map<Integer, List<Integer>> map = new HashMap<>(); // 邻接表
        int N = nums.length;
        int[] inMap = new int[N + 1];
        Set<Integer> set = new HashSet<>(); // 大过滤, seq里数字出现个数, 如果<nums必然false
        for (var s : sequences) {
            int len = s.size();
            set.add(s.get(0));
            for (int i = 1; i < len; i++) {
                set.add(s.get(i));
                int from = s.get(i - 1);
                int to = s.get(i);
                map.computeIfAbsent(from, key -> new ArrayList<>()).add(to);
                inMap[to]++;
            }
        }
        if (set.size() < N) return false; // 如果seq里的元素数< n个, 说明nums必无效
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

    public boolean sequenceReconstruction2(int[] nums, List<List<Integer>> sequences) {
        Map<Integer, List<Integer>> map = new HashMap<>(); // 邻接表
        int N = nums.length;
        int[] inMap = new int[N + 1];
        for (var s : sequences) {
            int len = s.size();
            for (int i = 1; i < len; i++) {
                int from = s.get(i - 1);
                int to = s.get(i);
                map.computeIfAbsent(from, key -> new ArrayList<>()).add(to);
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

    // 拓扑排序: 每次入度为零的数都为1个, 序列就是唯一的
    // 题目确定sequences一定是nums的子序列, 不用去验证了
    public boolean sequenceReconstruction3(int[] nums, List<List<Integer>> sequences) {
        int N = nums.length;
        // nums 是 [1, n] 范围内所有整数的排列
        List<List<Integer>> graph = new ArrayList<>(); // 邻接表
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        int[] inMap = new int[N + 1];
        for (List<Integer> seq : sequences) {
            int M = seq.size();
            for (int j = 1; j < M; j++) {
                int from = seq.get(j - 1);
                int to = seq.get(j);
                graph.get(from).add(to);
                inMap[to]++;
            }
        }
        // 入度为0 的点的队列
        ArrayDeque<Integer> zeroInQueue = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (inMap[i] == 0) {
                zeroInQueue.addLast(i);
            }
        }
        while (!zeroInQueue.isEmpty()) {
            int size = zeroInQueue.size();
            if (size > 1) return false;
            int cur = zeroInQueue.pollFirst();
            for (int next : graph.get(cur)) {
                --inMap[next];
                if (inMap[next] == 0) {
                    zeroInQueue.addLast(next);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // int[] nums = {1, 2, 3};
        // int[][] seq = {{1, 2}, {1, 3}, {2, 3}};
        int[] nums = {4, 1, 5, 2, 6, 3};
        int[][] seq = {{5, 2, 6, 3}, {4, 1, 5, 2}}; // true
        List<List<Integer>> seqList = new ArrayList<>();
        for (int[] s : seq) {
            List<Integer> list = new ArrayList<>();
            for (int n : s) {
                list.add(n);
            }
            seqList.add(list);
        }
        var ans = new Problem_444_SequenceReconsttuction().sequenceReconstruction(nums, seqList);
        System.out.println(ans);
    }

}
