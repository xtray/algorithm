package _DailyTarget;

import java.util.HashMap;
import java.util.Map;

public class Problem_JZII010_SubarraySumK {

    // 前缀和预处理数组, 以数组每个位置结尾的子数组个数
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int[] sum = new int[N];
        int pre = 0;
        for (int i = 0; i < N; i++) {
            sum[i] = pre + nums[i];
            pre = sum[i];
        }
        int cnt = 0;
        // [value: cnt]: 前缀和value出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // 0的前缀和最早出现在-1位置, 一个数也不选, 次数1次
        for (int i = 0; i < N; i++) {
            int cur = sum[i] - k;
            // NOTE: 以当前位置结尾的子数组个数就是map中 sum[i] - k 这个前缀和的个数, 二者一对一对应
            cnt += map.getOrDefault(cur, 0);
            map.put(sum[i], map.getOrDefault(sum[i], 0) + 1);
        }
        return cnt;
    }
}
