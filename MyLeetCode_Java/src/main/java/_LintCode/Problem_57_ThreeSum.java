package _LintCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_57_ThreeSum {

    // a + b + c  =0;
    // a + b = -c
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        int N = nums.length;
        // 枚举第一个数, 求2sum
        for (int i = 0; i < N - 2; i++) {
            int target = -nums[i];
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            int L = i + 1;
            int R = N - 1;
            while (L < R) {
                if (nums[L] + nums[R] < target) {
                    L++;
                } else if (nums[L] + nums[R] > target) {
                    R--;
                } else {
                    // L不等于初始i+1, 其次 L不重复, R必然不重复
                    if (L == i + 1 || nums[L] != nums[L - 1]) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[L]);
                        list.add(nums[R]);
                        ans.add(list);
                    }
                    L++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {-2, -2, 4};

    }

}
