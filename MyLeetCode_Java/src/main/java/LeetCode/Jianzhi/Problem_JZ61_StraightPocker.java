package LeetCode.Jianzhi;

import java.util.Arrays;

public class Problem_JZ61_StraightPocker {

    public static boolean isStraight(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        Arrays.sort(nums);
        int N = nums.length;
        int zeroCnt = 0;
        for (int i = 0; i < N; i++) {
            if (nums[i] == 0) {
                zeroCnt++;
                continue;
            }
            if (i != 0 && nums[i] == nums[i - 1]) {
                return false;
            }
        }
        if (zeroCnt == N) {
            return true;
        }
        // 1. 本身能连起来
        // 2. 最大-最小 中间的gap == zeroCnt
        boolean p1 = nums[N - 1] - nums[zeroCnt] + 1 == N - 1 - zeroCnt + 1;
        boolean p2 = nums[N - 1] - nums[zeroCnt] + 1 - (N - 1 - zeroCnt + 1) <= zeroCnt;
        return p1 || p2;
    }

    public static void main(String[] args) {
        // int[] nums = {0, 0, 1, 2, 5};
        int[] nums = {11,0,9,0,0};
        // int[] nums = {11, 8, 12, 8, 10};
        var ans = isStraight(nums);
        System.out.println(ans);
    }

}
