package _DailyTarget;


// Tag: 单调队列

import java.util.ArrayDeque;
import java.util.Arrays;

public class Problem_239_MaxSlidingWindow {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) {
            return new int[0];
        }
        int N = nums.length;
        // 队头放最大的
        int[] ans = new int[N - k + 1];
        ArrayDeque<Integer> queue = new ArrayDeque<>(); // 放位置
        for (int i = 0; i < N; i++) {
            while (!queue.isEmpty() && nums[i] >= nums[queue.peekLast()]) {
                queue.pollLast(); // 弹出队尾不够大的
            }
            queue.addLast(i);
            if (!queue.isEmpty() && queue.peekFirst() == i - k) {
                queue.pollFirst(); // 移除队头过期的
            }
            if (i >= k - 1) {
                ans[i - k + 1] = nums[queue.peekFirst()];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        var ans = new Problem_239_MaxSlidingWindow().maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(ans));
    }


}
