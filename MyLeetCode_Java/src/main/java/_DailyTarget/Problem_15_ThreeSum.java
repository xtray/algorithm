package _DailyTarget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_15_ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        Arrays.sort(nums);
        int N = nums.length;
        for (int i = 0; i < N - 2; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            int target = -nums[i];
            int L = i + 1;
            int R = N - 1;
            while (L < R) {
                if (L != i + 1 && nums[L] == nums[L - 1]) {
                    L++;
                    continue;
                }
                if (nums[L] + nums[R] > target) {
                    R--;
                } else if (nums[L] + nums[R] < target) {
                    L++;
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[L]);
                    list.add(nums[R]);
                    ans.add(list);
                    L++;
                }
            }
        }
        return ans;
    }
}
