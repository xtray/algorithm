package _DailyTarget;

import java.util.*;

public class Problem_560_SubarraySum {
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        // [前缀和, 次数]
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // 一个数都不选时, 0前缀和出现1次
        int cnt = 0;
        int sum = 0;
        for (int num : nums) {
            // 当前i位置累加和sum, 求子数组和为K, 即是找前缀和为 sum - K的
            sum += num;
            cnt += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return cnt;
    }
}
