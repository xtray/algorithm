package _Misc;

import java.util.Arrays;

public class HarmonySubArray {

    int getMaxHarmonySubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int L = 0, R = 0, ans = 0;
        int N = nums.length;
        while (R < N) {
            if (nums[R] - nums[L] <= 1) {
                if (nums[R] - nums[L] == 1) {
                    ans = Math.max(ans, R - L + 1);
                }
                R++;
            } else { // > 1
                L++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // int[] nums = new int[]{1,3,2,2,5,2,3,7};
        // int[] nums = new int[]{1, 1, 1};
        int[] nums = new int[]{1, 1, 1, 2, 3, 3, 3, 3, 3, 4, 4, 7};
        var ans = new HarmonySubArray().getMaxHarmonySubArray(nums);
        System.out.println(ans);
    }
}
