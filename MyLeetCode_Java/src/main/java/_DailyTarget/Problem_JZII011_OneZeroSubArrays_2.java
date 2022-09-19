package _DailyTarget;

import java.util.HashMap;
import java.util.Map;

public class Problem_JZII011_OneZeroSubArrays_2 {

    // 0 看成-1, 本题转化为和为k的最长子数组长度
    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int ans = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < N; i++) {
            sum += nums[i] == 0 ? -1 : 1;
            // 找 sum - 0 最早出现的位置
            if (map.containsKey(sum)) {
                ans = Math.max(ans, i - map.get(sum));
            } else {
                map.put(sum, i); // sum这个前缀和最早出现在i位置
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1};
        var ans = new Problem_JZII011_OneZeroSubArrays_2().findMaxLength(nums);
        System.out.println(ans);
    }
}
