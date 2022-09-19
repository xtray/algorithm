package _DailyTarget;

import java.util.ArrayDeque;

public class Problem_JZ59_I_MaxSlidingWindow {


    // 最大值的窗口, 头部放最大值
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int N = nums.length;
        int[] ans = new int[N - k + 1];
        // 最大值的单调队列, 里面放的是下标
        ArrayDeque<Integer> qmax = new ArrayDeque<>();
        int idx = 0;
        for (int i = 0; i < N; i++) {
            while (!qmax.isEmpty() && nums[qmax.peekLast()] <= nums[i]) {
                qmax.pollLast();
            }
            qmax.add(i);
            if (qmax.peekFirst() == i - k) {
                qmax.pollFirst();
            }
            if (i >= k - 1) {
                ans[idx++] = nums[qmax.peekFirst()];
            }
        }
        return ans;
    }
}
