package _DailyTarget;

import java.util.HashMap;
import java.util.Map;

public class Problem_JZII010_SubarraySumK_2 {

    // 前缀和预处理数组, 以数组每个位置结尾的子数组个数
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        // [前缀和, 次数]
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // NOTE: 一个数都不选时, 0前缀和出现1次
        int cnt = 0;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            // 当前i位置累加和sum, 求子数组和为K, 即是找前缀和为 sum - K的
            sum += nums[i];
            cnt += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return cnt;
    }
}
