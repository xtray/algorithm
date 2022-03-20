package LeetCode;

import java.util.Arrays;

public class Problem_976_LargestPerimerter {

    /**
     *
     * 我们假设三角形的边长 a,b,c满足a≤b≤c，那么这三条边组成面积不为零的三角形的充分必要条件为 a+b>c。
     *
     * 基于此，我们可以选择枚举三角形的最长边 c，而从贪心的角度考虑，我们一定是选「小于 c 的最大的两个数」
     * 作为边长 a 和 b，此时最有可能满足 a+b>c，使得三条边能够组成一个三角形，
     * 且此时的三角形的周长是最大的。
     */
    public int largestPerimeter(int[] nums) {
        if(nums == null || nums.length <3) {
            return 0;
        }
        Arrays.sort(nums);
        for(int i = nums.length -1; i >=2; i--) {
            int largest = nums[i];
            if(nums[i-2] + nums[i-1] > largest) {
                return nums[i-2] + nums[i-1] + largest;
            }
        }
        return 0;
    }
}
