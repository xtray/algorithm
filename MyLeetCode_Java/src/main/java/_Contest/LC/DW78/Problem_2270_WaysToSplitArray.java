package _Contest.LC.DW78;

// https://leetcode.cn/problems/number-of-ways-to-split-array/
// TAG: 前缀和数组
public class Problem_2270_WaysToSplitArray {

    public int waysToSplitArray(int[] nums) {
        int N = nums.length;
        long[] right = new long[N];
        right[N - 1] = nums[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            right[i] = right[i + 1] + nums[i];
        }
        long leftSum = 0;
        long rightSum = 0;
        int ans = 0;
        // 尝试每一个切分点
        for (int i = 0; i < N - 1; i++) {
            leftSum += nums[i];
            rightSum = right[i + 1];
            if (leftSum >= rightSum) {
                ans++;
            }
        }
        return ans;
    }

    // NOTE: 注意学习这种写法!!
    public int waysToSplitArray1(int[] nums) {
        int N = nums.length;
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        long left = 0;
        long right = 0;
        int ans = 0;
        // 枚举每一个左边的元素结束位置
        for (int i = 0; i < N - 1; i++) {
            left += nums[i];
            right = sum - left;
            if (left >= right) {
                ans++;
            }
        }
        return ans;
    }

    // 进一步优化
    // left >= right => left + left >= right + left => left*2 >= sum
    public int waysToSplitArray3(int[] nums) {
        int N = nums.length;
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        long left = 0;
        int ans = 0;
        // 枚举每一个左边的元素结束位置
        for (int i = 0; i < N - 1; i++) {
            left += nums[i];
            // if (left << 1 >= sum) {
            if (left >= sum - left) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // int[] nums = {10,4,-8,7};
        int[] nums = {3, 3};
        var ans = new Problem_2270_WaysToSplitArray().waysToSplitArray(nums);
        System.out.println(ans);
    }
}
