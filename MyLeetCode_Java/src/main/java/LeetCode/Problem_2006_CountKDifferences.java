package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class Problem_2006_CountKDifferences {

    public int countKDifference(int[] nums, int k) {
        if(nums == null || nums.length <= 1) {
            return 0;
        }
        int N = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for(int i =0;i<N;i++) {
            int cur = nums[i];
            ans += map.getOrDefault(cur - k, 0);
            ans += map.getOrDefault(cur + k, 0);
            map.put(cur, map.getOrDefault(cur, 0) + 1);
        }
        return ans;
    }
}
