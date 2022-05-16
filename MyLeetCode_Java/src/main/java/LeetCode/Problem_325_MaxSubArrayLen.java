package LeetCode;

import java.util.HashMap;
import java.util.Map;

// IMP: 数组三连问题, 第二连, 非常非常重要!!

public class Problem_325_MaxSubArrayLen {

    public static int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        // key: 前缀累加和, value: key这个前缀和和最早出现的位置(0~value)
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // NOTE: 重要, 0 这个累加和最早出现在-1位置;
        int len = 0;
        int sum = 0;
        // 考虑以每一个位置结尾的子数组
        for (int i = 0; i < N; i++) {
            sum += nums[i];
            Integer pre = map.get(sum - k);
            if (pre != null) {
                len = Math.max(len, i - pre);
            }
            if (!map.containsKey(sum)) { // 如果我当前这个前缀和在map里没有我才更新!!
                map.put(sum, i);
            }
        }
        return len;
    }

    public static int maxSubArrayLen2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        // key: 前缀累加和, value: key这个前缀和和最早出现的位置(0~value)
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // NOTE: 重要, 0 这个累加和最早出现在-1位置;
        int len = 0;
        int sum = 0;
        // 考虑以每一个位置结尾的子数组
        for (int i = 0; i < N; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                int pre = map.get(sum - k);
                len = Math.max(len, i - pre);
            }
            if (!map.containsKey(sum)) { // 如果我当前这个前缀和在map里没有我才更新!!
                map.put(sum, i);
            }
        }
        return len;
    }


    public static void main(String[] args) {
        int[] nums = {1, -1, 5, -2, 3};
        int k = 3;
        var ans = maxSubArrayLen(nums, k);
        System.out.println(ans);
    }
}
