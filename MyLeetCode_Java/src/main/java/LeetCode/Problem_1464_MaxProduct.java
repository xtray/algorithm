package LeetCode;

import java.util.Arrays;

public class Problem_1464_MaxProduct {

    public int maxProduct(int[] nums) {
        Arrays.sort(nums);
        int N = nums.length;
        return (nums[N - 1] - 1) * (nums[N - 2] - 1);
    }


    public int maxProduct1(int[] nums) {
        int max1 = Math.max(nums[0], nums[1]);
        int max2 = max1 == nums[0] ? nums[1] : nums[0];
        int N = nums.length;
        for (int i = 2; i < N; i++) {
            if (nums[i] > max1) {
                max2 = max1;
                max1 = nums[i];
            } else if (nums[i] > max2) {
                max2 = nums[i];
            }
        }
        return (max1 - 1) * (max2 - 1);
    }
}
