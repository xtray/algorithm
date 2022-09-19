package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_1403_MinSubSeq {

    public List<Integer> minSubsequence(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        Arrays.sort(nums);
        int curSum = 0;
        // curSum, sum - curSum
        // curSum > sum - curSum
        // sum - 2*curSum < 0
        int idx = nums.length - 1;
        List<Integer> ans = new ArrayList<>();
        while (sum - 2 * curSum >= 0) {
            ans.add(nums[idx]);
            curSum += nums[idx--];
        }
        return ans;
    }
}
