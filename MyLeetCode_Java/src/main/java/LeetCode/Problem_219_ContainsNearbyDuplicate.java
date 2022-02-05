package LeetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Problem_219_ContainsNearbyDuplicate {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (i - map.get(nums[i])  <= k) {
                    return true;
                }
                map.put(nums[i], i);
            } else {
                map.put(nums[i], i);
            }
        }
        return false;
    }

    // 是否存在长度不超过的k+1 窗口，窗口内有相同元素。
    public boolean containsNearbyDuplicate1(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if(i>k) {
                set.remove(nums[i-k-1]);
            }
            if(set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1, 2, 3};
        int k = 2;
        var ans = new Problem_219_ContainsNearbyDuplicate().containsNearbyDuplicate(nums, k);
        System.out.println(ans);
    }
}
