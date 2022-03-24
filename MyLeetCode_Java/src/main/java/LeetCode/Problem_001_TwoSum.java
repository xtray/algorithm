package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class Problem_001_TwoSum {

    public int[] twoSum(int[] nums, int target) {
        if(nums == null || nums.length==0) {
            return new int[]{};
        }
        int N = nums.length;
        // num, index
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i< N; i++) {
            int x = target - nums[i];
            if(map.containsKey(x)) {
                return new int[]{i, map.get(x)};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        int nums[] = {2,7,11,15};
        int target = 9;
        var ans = new Problem_001_TwoSum().twoSum(nums, target);
        System.out.println(ans[0] + " " + ans[1]);
    }
}
