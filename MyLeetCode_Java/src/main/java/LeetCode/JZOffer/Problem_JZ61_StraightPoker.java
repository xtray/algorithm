package LeetCode.JZOffer;

import java.util.Arrays;

public class Problem_JZ61_StraightPoker {

    public static boolean isStraight(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        Arrays.sort(nums);
        int N = nums.length;
        int zeroCnt = 0;
        int idx = 0;
        while (nums[idx] == 0) {
            idx++;
            zeroCnt++;
        }
        if (zeroCnt == N) { // 数组都是0
            return true;
        }
        for (int i = idx; i < N; i++) { // 看非零的数有没有重复的
            if (i != 0 && nums[i] == nums[i - 1]) {
                return false;
            }
        }
        int gap = nums[N - 1] - nums[zeroCnt] + 1; // 最大 - 最小总共的格子数
        int block = N - 1 - idx + 1; // 非零数一共的个数
        // 能够连续的条件:
        // 1. 非零的数本身能连起来 个数==Gap
        // 2. 最大~最小总共的格子数 - 非零数个数  <= zeroCnt
        boolean p1 = gap == block;
        boolean p2 = gap - block <= zeroCnt;
        return p1 || p2;
    }

    public static void main(String[] args) {
        // int[] nums = {0, 0, 1, 2, 5};
        int[] nums = {11, 0, 9, 0, 0};
        // int[] nums = {11, 8, 12, 8, 10};
        var ans = isStraight(nums);
        System.out.println(ans);
    }

}
