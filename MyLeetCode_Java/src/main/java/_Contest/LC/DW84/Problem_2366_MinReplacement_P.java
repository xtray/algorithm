package _Contest.LC.DW84;

// https://leetcode.cn/problems/minimum-replacements-to-sort-the-array/

// PENDING

public class Problem_2366_MinReplacement_P {

    public long minimumReplacement(int[] nums) {
        long cnt = 0;
        int N = nums.length;
        int pre = nums[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            int cur = nums[i];
            // 1. 如果当前数大
            //    如果当前数一半> pre, 去掉一个pre, 直到当前数 一半<pre

            while (cur > pre) {
                if (cur >> 1 > pre) {
                    cur -= pre;
                    cnt++;
                } else {
                    pre = cur >> 1;
                    cnt++;
                    continue;
                }


                // cnt += (nums[i] + pre - 1) / pre - 1;
                // pre = nums[i] % pre == 0 ? pre : nums[i] % pre;
            }


        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] nums = {12, 9, 7, 6, 17, 19, 21}; // 6
        var ans = new Problem_2366_MinReplacement_P().minimumReplacement(nums);
        System.out.println(ans);
    }
}
