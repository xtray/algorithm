package _DailyTarget;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Problem_JZII115_SequenceReconsttuction {


    // 拓扑排序: 每次入度为零的数都为1个, 序列就是唯一的
    // 题目确定sequences一定是nums的子序列, 不用去验证了
    public boolean sequenceReconstruction(int[] nums, int[][] sequences) {
        int N = nums.length;
        // nums 是 [1, n] 范围内所有整数的排列
        List<List<Integer>> graph = new ArrayList<>(); // 邻接表
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        int[] inMap = new int[N + 1];
        for (int[] seq : sequences) {
            int M = seq.length;
            for (int j = 1; j < M; j++) {
                int from = seq[j - 1];
                int to = seq[j];
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
        var ans = new Problem_JZII115_SequenceReconsttuction().sequenceReconstruction(nums, seq);
        System.out.println(ans);
    }
}
