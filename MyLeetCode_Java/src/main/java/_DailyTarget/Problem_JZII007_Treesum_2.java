package _DailyTarget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_JZII007_Treesum_2 {

    // a + b + c = 0
    // -a = b + c
    // 枚举a
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        int N = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < N; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            int L = i + 1;
            int R = N - 1;

            while (L < R) {
                int target = -nums[i];
                if (L != i + 1 && nums[L] == nums[L - 1]) {
                    L++;
                    continue;
                }
                if (nums[L] + nums[R] < target) {
                    L++;
                } else if (nums[L] + nums[R] > target) {
                    R--;
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[L]);
                    list.add(nums[R]);
                    ans.add(list);
                    L++;
                    R--;
                }
            }
        }
        return ans;
    }
}
