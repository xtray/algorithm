package LeetCode;

import java.util.ArrayDeque;

public class Problem_1248_NumberOfSubArrays {

    // 以每个数结尾的子数组个数
    public int numberOfSubarrays(int[] nums, int k) {
        int N = nums.length;
        int ans = 0;
        int L = 0; // 子数组开始位置
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            if ((nums[i] & 1) == 1) queue.addLast(i);
            if (queue.size() > k) { // 弹出多余的奇数
                L = queue.pollFirst() + 1;
            }
            if (queue.size() == k) {
                // 所有可能的开头范围
                ans += queue.peekFirst() - L + 1;
            }
        }
        return ans;
    }

    // 累加和为k的子数组个数
    public int numberOfSubarrays1(int[] nums, int k) {
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            nums[i] &= 1;
        }

        return 0;

    }

    public static void main(String[] args) {
        // int[] nums = {1, 1, 2, 1, 1};
        // int k = 3; // 2
        int[] nums = {2044, 96397, 50143};
        int k = 1; // 3
        var ans = new Problem_1248_NumberOfSubArrays().numberOfSubarrays(nums, k);
        System.out.println(ans);
    }
}
