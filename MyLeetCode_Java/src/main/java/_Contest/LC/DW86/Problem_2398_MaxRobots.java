package _Contest.LC.DW86;

import java.util.ArrayDeque;

/**
 * 题意: 找寻最大的连续k个可以满足 窗口最大值 + 窗口累加和 * k <= 预算的 最大的k值,
 * 长度为N的数组, 有任何一个长度为k的子数组满足, 则当前长度Ok
 */

public class Problem_2398_MaxRobots {

    // TAG: 单调队列 + 窗口累加和 + 二分
    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        int N = chargeTimes.length;
        int L = 1;
        int R = N;
        int res = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (check(chargeTimes, runningCosts, budget, mid)) {
                res = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return res == -1 ? 0 : res;
    }

    // 查看当窗口大小为k时, 能不能通过
    private boolean check(int[] chargeTimes, int[] runningCosts, long budget, int k) {
        long curCost = 0;
        int N = chargeTimes.length;
        int R = 0;
        long sum = 0; // 窗口累加和
        // 单调队列, 队首最大值
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        while (R < N) {
            sum += runningCosts[R];
            // 1. R位置的元素来到, 清除单调队列尾部较小的值
            while (!queue.isEmpty() && chargeTimes[R] >= chargeTimes[queue.peekLast()]) {
                queue.pollLast();
            }
            queue.addLast(R);
            // 2. 清除队列头部过期的
            if (queue.peekFirst() == R - k) {
                queue.pollFirst();
            }
            // 3.调整累加和, 减掉出窗口的
            if (R >= k) {
                sum -= runningCosts[R - k];
            }
            // 4.当>=窗口大小时, 开始收集答案
            if (R >= k - 1) {
                int curMax = chargeTimes[queue.peekFirst()];
                curCost = curMax + (long) k * sum;
                // 5. 有任何一个窗口内元素满足预算, 返回true
                if (curCost <= budget) return true;
            }
            R++;
        }
        return false;
    }

    public static void main(String[] args) {
        // int[] chargeTimes = {3, 6, 1, 3, 4};
        // int[] runningCosts = {2, 1, 3, 4, 5};
        // int budget = 25;
        int[] chargeTimes = {8, 76, 74, 9, 75, 71, 71, 42, 15, 58, 88, 38, 56, 59, 10, 11};
        int[] runningCosts = {1, 92, 41, 63, 22, 37, 37, 8, 68, 97, 39, 59, 45, 50, 29, 37};
        int budget = 412; // 3
        var ans = new Problem_2398_MaxRobots().maximumRobots(chargeTimes, runningCosts, budget);
        System.out.println(ans);
    }
}
