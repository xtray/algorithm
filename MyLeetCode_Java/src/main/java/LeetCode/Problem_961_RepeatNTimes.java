package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem_961_RepeatNTimes {

    public int repeatedNTimes(int[] nums) {
        int N = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int k = 3; // 找大于N/3的数, 最多两个
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else { // 不是候选
                if (map.size() == k - 1) { // 候选表满了
                    // 每个候选付出一点血量, 血量0的要删掉
                    allCandidateMinusOne(map);
                } else {
                    map.put(num, 1);
                }
            }
        }
        // 所有可能的候选
        int ans = 0;
        int cnt = 0;
        for (int key : map.keySet()) {
            if (map.get(key) > cnt) {
                cnt = map.get(key);
                ans = key;
            }
        }
        return ans;
    }

    private static void allCandidateMinusOne(Map<Integer, Integer> map) {
        List<Integer> removeList = new ArrayList<>();
        for (int key : map.keySet()) {
            map.put(key, map.get(key) - 1);
            if (map.get(key) == 0) {
                removeList.add(key);
            }
        }
        for (int key : removeList) {
            map.remove(key);
        }
    }

    // 数组中仅有一个元素出现多次，其余元素均出现一次, 出现次数大于1即是所求
    public int repeatedNTimes2(int[] nums) {
        int N = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                return num;
            } else {
                map.put(num, 1);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4, 9, 8, 7, 3, 2, 2, 2, 2, 1, 2, 2};
        var ans = new Problem_961_RepeatNTimes().repeatedNTimes(nums);
        System.out.println(ans);
    }
}
