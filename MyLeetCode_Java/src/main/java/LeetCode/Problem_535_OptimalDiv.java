package LeetCode;

import java.util.Arrays;

public class Problem_535_OptimalDiv {
    public String optimalDivision(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums == null || nums.length == 0 ? "" : String.valueOf(nums[0]);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(nums[0]);
        sb.append("/");
        if(nums.length > 2) {
            sb.append("(");
        }
        for(int i = 1; i< nums.length; i++) {
            sb.append(nums[i]);
            if(i+1<nums.length) {
                sb.append("/");
            }
        }
        if(nums.length > 2) {
            sb.append(")");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1000, 100, 10, 2};
        var ans = new Problem_535_OptimalDiv().optimalDivision(nums);
        System.out.println(ans);
    }
}
