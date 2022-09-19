package _Contest.LC.DW86;

import java.util.*;

public class Problem_2395_EqualSumSubArray {

    public boolean findSubarrays(int[] nums) {
        int N = nums.length;
        Map<Integer, Integer> cntMap = new HashMap<>();
        for (int i = 1; i < N; i++) {
            int curSum = nums[i] + nums[i - 1];
            cntMap.put(curSum, cntMap.getOrDefault(curSum, 0) + 1);
        }
        for (int val : cntMap.values()) {
            if (val >= 2) return true;
        }
        return false;
    }
}
