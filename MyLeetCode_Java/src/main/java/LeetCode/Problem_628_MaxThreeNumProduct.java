package LeetCode;

import java.util.Arrays;

public class Problem_628_MaxThreeNumProduct {

    // 向左侧移动增加非负数个数
    // 1.全是负数
    // 2. 只有一个非负数
    // 3. 只有两个非负数
    // 4. 有>=3个非负数
    public int maximumProduct(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int N = nums.length;
        Arrays.sort(nums);
        return Math.max(nums[0] * nums[1] * nums[N - 1], nums[N - 1] * nums[N - 2] * nums[N - 3]);
    }

    // TLE
    public int maximumProduct1(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int N = nums.length;
        Arrays.sort(nums);
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    ans = Math.max(ans, nums[i] * nums[j] * nums[k]);
                }
            }
        }
        return ans;
    }

    public int maximumProduct2(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        // 由上面分析, 只需要最大的三个数、最小的两个数
        int max1, max2, max3, min1, min2;
        max1 = max2 = max3 = Integer.MIN_VALUE;
        min1 = min2 = Integer.MAX_VALUE;
        for (int x : nums) {
            if (x > max1) {
                max3 = max2;
                max2 = max1;
                max1 = x;
            } else if (x > max2) {
                max3 = max2;
                max2 = x;
            } else if (x > max3) {
                max3 = x;
            }
            if (x < min1) {
                min2 = min1;
                min1 = x;
            } else if (x < min2) {
                min2 = x;
            }
        }
        return Math.max(max1 * max2 * max3, max1 * min1 * min2);
    }

}
