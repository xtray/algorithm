package _Contest.LC.DW87;

import java.util.Arrays;

// 题目是OR, 做成了XOR, 注意读题!!

public class Problem_2411_SmallestSubArray {


    // 数值最大1e9, 不到2^30次
    public int[] smallestSubarrays(int[] nums) {
        int[] pos = new int[30];
        // 从右往左遍历
        int N = nums.length;
        int[] ans = new int[N];
        for (int i = N - 1; i >= 0; i--) {
            int num = nums[i];
            // 补全 num 上 0~29位置的1, 找到能补全的最大pos
            int maxPos = i;
            for (int j = 0; j < 30; j++) {
                if ((num >>> j & 1) != 0) { // numj位上有1, 不用pos数组
                    pos[j] = i;
                } else { // j位上没有1, 用右侧最近的1的位置补全
                    maxPos = Math.max(maxPos, pos[j]);
                }
            }
            // 当前num是补全了从i~N-1位置所有可能的1的最大值了, 求长度
            ans[i] = maxPos - i + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, 2, 1, 3};
        var ans = new Problem_2411_SmallestSubArray().smallestSubarrays(nums);
        System.out.println(Arrays.toString(ans));
    }
}
