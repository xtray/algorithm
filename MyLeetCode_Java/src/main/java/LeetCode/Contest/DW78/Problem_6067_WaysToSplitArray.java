package LeetCode.Contest.DW78;

public class Problem_6067_WaysToSplitArray {

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

    public static void main(String[] args) {
        // int[] nums = {10,4,-8,7};
        int[] nums = {3, 3};
        var ans =  new Problem_6067_WaysToSplitArray().waysToSplitArray(nums);
        System.out.println(ans);
    }
}
