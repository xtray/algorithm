package _DailyTarget;

import java.util.ArrayDeque;
import java.util.Arrays;

public class Problem_JZ59_I_MaxSlidingWindow_2 {


    // TAG: 单调队列, 头部放最大值下标, 当前新入的值比尾部大, 尾部丢弃
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int N = nums.length;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < k - 1; i++) {
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
        }
        int[] ans = new int[N - k + 1];
        int idx = 0;
        for (int i = k - 1; i < nums.length; i++) {
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
            if (queue.peekFirst() == i - k) {
                queue.pollFirst();
            }
            ans[idx++] = nums[queue.peekFirst()];
        }
        return ans;
    }


    public int[] maxSlidingWindow2(int[] nums, int k) {
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

    public static void main(String[] args) {
        // int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        // int k = 3;
        int[] nums = {7, 2, 4};
        int k = 2;
        var ans = new Problem_JZ59_I_MaxSlidingWindow_2().maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(ans));
    }
}
