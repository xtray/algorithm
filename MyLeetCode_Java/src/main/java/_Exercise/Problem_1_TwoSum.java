package _Exercise;

import java.util.HashMap;
import java.util.Map;

public class Problem_1_TwoSum {

    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int N = nums.length;
        int[] ans = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            if (map.containsKey(target - nums[i])) {
                ans[0] = i;
                ans[1] = map.get(target - nums[i]);
                return ans;
            } else {
                map.put(nums[i], i);
            }
        }
        return ans;
    }
}
