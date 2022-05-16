package LeetCode;

// IMP: 滑动窗口模板

public class Problem_713_SubArrayProductLessThanK {

    // ref: https://leetcode-cn.com/problems/subarray-product-less-than-k/solution/jian-dan-yi-dong-xiang-xi-zhu-jie-shuang-jvy3/

    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int L = 0;
        int R = 0; // 盯着的数
        long product = 1;
        int ans = 0;
        // [L, R]
        while (R < N) {
            product *= nums[R];
            while (L <= R && product >= k) {
                product /= nums[L];
                L++;
            }
            // L > R时: R - L + 1 == 0
            ans += R - L + 1; // 每次L开头的子数组个数
            R++;
        }
        return ans;
    }


    public static void main(String[] args) {
        // int[] nums = {10, 5, 2, 6};
        // int k = 100; // 8
        int[] nums = {1, 2, 3};
        int k = 0;
        var ans = numSubarrayProductLessThanK(nums, k);
        System.out.println(ans);
    }
}
