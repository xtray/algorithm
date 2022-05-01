package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem_1_TwoSum {

    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        int N = nums.length;
        // key: num, value: index
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int x = target - nums[i];
            if (map.containsKey(x)) {
                return new int[]{i, map.get(x)};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    // IMP: twosum拓展, 使用双指针的解法, 重要!!
    // nums[start...end]范围上, 有多少个不同二元组, 相加和==target, 全返回
    // {-1, 5} k= 4
    // {1, 3}
    public static List<List<Integer>> twoSum(int[] nums, int start, int end, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        int L = start;
        int R = end;
        while (L < R) {
            int sum = nums[L] + nums[R];
            if (sum > target) {
                R--;
            } else if (sum < target) {
                L++;
            } else { // 相等
                // NOTE: 去重操作!!, 当前来到的 L 他跟左侧的数不一样的时候，我收集这个答案
                if (L == 0 || nums[L] != nums[L - 1]) {
                    List<Integer> curAns = new ArrayList<>();
                    curAns.add(nums[L]);
                    curAns.add(nums[R]);
                    ans.add(curAns);
                }
                L++;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        var ans = new Problem_1_TwoSum().twoSum(nums, target);
        System.out.println(ans[0] + " " + ans[1]);
    }
}
