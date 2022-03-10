package LeetCode.DW73;

import java.util.HashMap;
import java.util.Map;

public class Problem_6024_MostFreqNumber {

    public int mostFrequent(int[] nums, int key) {

        Map<Integer, Integer> map = new HashMap<>();
        int maxCnt = 0;
        int maxVal = 0;
        for (int i = 0; i + 1 < nums.length; i++) {
            if (nums[i] == key) {
                map.put(nums[i + 1], map.getOrDefault(nums[i + 1], 0) + 1);
                int cnt = map.get(nums[i + 1]);
                if (cnt > maxCnt) {
                    maxCnt = cnt;
                    maxVal = nums[i + 1];
                }
            }
        }
        return maxVal;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 100, 200, 1, 100};
        int key = 1;
        var ans = new Problem_6024_MostFreqNumber().mostFrequent(nums, key);
        System.out.println(ans);
    }
}
