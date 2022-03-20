package LeetCode.BINARY.EASY;

import java.util.HashMap;
import java.util.Map;

public class Problem_001_TwoSum {

    public int[] twoSum(int[] nums, int target) {
        int N = nums.length;
        // key:
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int rest = target - nums[i];
            if (map.containsKey(rest)) {
                return new int[]{i, map.get(rest)};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }
}
