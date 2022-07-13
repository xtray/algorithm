package LeetCode.JZOffer;

import java.util.HashMap;
import java.util.Map;

// 类似数组三连问题, 第二连

public class Problem_JZII010_SubarrayKSum {

    public static int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // key: 前缀和
        // value: 某个前缀和最早出现的位置
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // NOTE: 重要, 0这个前缀和最早出现在-1位置, 次数是1次
        int ans = 0;
        int allSum = 0;
        for (int i = 0; i < nums.length; i++) {
            allSum += nums[i];
            // 找 allSum - k 在map里出现的次数
            ans += map.getOrDefault(allSum - k, 0);
            // 累加当前的累加和次数到map
            map.put(allSum, map.getOrDefault(allSum, 0) + 1);
        }
        return ans;
    }
}
