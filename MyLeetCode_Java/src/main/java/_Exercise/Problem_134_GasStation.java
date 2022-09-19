package _Exercise;

// PENDING

import java.util.ArrayDeque;

public class Problem_134_GasStation {

    // 哪个点出发，再回到自己之前纯能累加不跑到 0 以下，他就是良好出发点
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || gas.length == 0 || cost == null || cost.length == 0) {
            return -1;
        }
        int N = gas.length;
        int M = N << 1;
        int[] sum = new int[M];
        // 生成纯能值数组, 长度为2倍
        for (int i = 0; i < N; i++) {
            sum[i] = gas[i] - cost[i];
            sum[i + N] = gas[i] - cost[i];
        }
        // 纯能值数组原地调整为累加和数组
        for (int i = 1; i < M; i++) {
            sum[i] = sum[i - 1] + sum[i];
        }
        // 滑窗, 窗口大小N, 寻找窗口内最小值
        // 如果<0就不可以通过
        // 单调队列, 队列头部是最小值
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        boolean[] ans = new boolean[N];
        // i为区间尾部, 最后的尾部为 2N - 2, 即从 N-1 ~ 2N-2
        // 最后一个区间 N-1 开头, 长度为 N, 到2N-2
        for (int i = 0; i < M - 1; i++) {
            // 1.sum[i]到来, 尾部数据该抛弃的抛弃
            while (!queue.isEmpty() && sum[i] <= sum[queue.peekLast()]) {
                queue.pollLast();
            }
            queue.addLast(i);
            // 2.头部该出的出队列
            if (!queue.isEmpty() && queue.peekFirst() == i - N) {
                queue.pollFirst();
            }
            // 3.收集答案
            if (i >= N - 1) { // 尾部>= N-1, 初次形成窗口
                int start = i - N + 1;
                int pre = start == 0 ? 0 : sum[start - 1];
                // 窗口内的最小值>=0, 代表良好出发点
                if (!queue.isEmpty() && sum[queue.peekFirst()] - pre >= 0) {
                    ans[start] = true;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            if (ans[i]) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // int[] gas = {1, 2, 3, 4, 5};
        // int[] cost = {3, 4, 5, 1, 2}; // 3

        int[] gas = {3, 1, 1};
        int[] cost = {1, 2, 2};

        var ans = new Problem_134_GasStation().canCompleteCircuit(gas, cost);
        System.out.println(ans);
    }
}
