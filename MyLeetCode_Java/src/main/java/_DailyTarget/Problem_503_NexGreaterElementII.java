package _DailyTarget;

import java.util.ArrayDeque;
import java.util.Arrays;

public class Problem_503_NexGreaterElementII {

    public int[] nextGreaterElements(int[] nums) {
        int N = nums.length;
        int[] ans = new int[N];
        Arrays.fill(ans, -1);
        // 栈顶最大 --> 小,  当前大弹出
        ArrayDeque<Integer> stack = new ArrayDeque<>(); // 存下标
        for (int i = 0; i < 2 * N - 1; i++) { // NOTE: 最后一个数忽略, 不用考察了
            // int index = i % N;
            while (!stack.isEmpty() && nums[i % N] > nums[stack.peekLast()]) {
                int top = stack.pollLast();
                if (ans[top] == -1) {
                    ans[top] = nums[i % N];
                }
            }
            stack.addLast(i % N);
        }
        return ans;
    }

    public int[] nextGreaterElements2(int[] nums) {
        int N = nums.length;
        int[] ans = new int[N];
        Arrays.fill(ans, -1);
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < N * 2 - 1; i++) {
            while (!stack.isEmpty() && nums[i % N] > nums[stack.peekLast()]) {
                ans[stack.pollLast()] = nums[i % N];
            }
            stack.addLast(i % N);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 3};
        var ans = new Problem_503_NexGreaterElementII().nextGreaterElements(nums);
        System.out.println(Arrays.toString(ans));


    }

}
