package LeetCode;

import java.util.*;

public class Problem_134_CanCompleteCircuit {


    // 1.加工出纯能值数组
    // 2.求累加和从正数点出发绕一圈
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
        for (int i = 0; i < M; i++) {
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
            if (i >= N - 1) { // N-1初次形成窗口位置
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

    // https://leetcode.cn/problems/gas-station/solution/by-nehzil-nnws/
    // 题目说一定有解, 那么
    // 1.选择的出发点一定是一个正数纯能值, 且从该点向后累加是个正数
    // 2.最后总的sum应该是>=0, 即代表如果从i位置可以, 那么 0~i-1的部分 + curSum == sum >=0
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int N = gas.length;
        int startIndex = 0; // 选择的出发点
        int curSum = 0; // 从当前出发点目前富余的可以使用的汽油
        int sum = 0;
        for (int i = 0; i < N; i++) { // 准备登上 i
            int cur = gas[i] - cost[i]; // 纯能值
            sum += cur;
            curSum += cur;
            if (curSum < 0) { // 当前不是良好出发点
                curSum = 0;
                startIndex = i + 1; // 从第一个是正数的点出发(负数必然不是良好出发点)
            }
            // else: leftOil >= 0, 如果新积攒的油>= 之前的欠账, 当前i位置可以走一圈
        }
        return sum >= 0 ? startIndex : -1;
    }


    public static void main(String[] args) {
        Problem_134_CanCompleteCircuit sl = new Problem_134_CanCompleteCircuit();
        // int[] gas = new int[]{10, 20, 10, 0, 30};
        // int[] distances = new int[]{5, 25, 15, 10, 15};
        int[] gas = new int[]{2, 3, 4};
        int[] distances = new int[]{3, 4, 3}; // -1

        int ans1 = sl.canCompleteCircuit2(gas, distances);
        System.out.println(ans1);


    }
}
