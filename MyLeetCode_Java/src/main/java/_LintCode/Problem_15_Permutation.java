package _LintCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_15_Permutation {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            ans.add(new ArrayList<>());
            return ans;
        }
        process(nums, 0, ans);
        return ans;
    }

    // 当前处理到i位置, i~N-1位置每一个都尝试来到i位置
    private void process(int[] nums, int i, List<List<Integer>> ans) {
        if (i == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            ans.add(list);
            return;
        }
        for (int start = i; start < nums.length; start++) {
            swap(nums, i, start);
            process(nums, i + 1, ans);
            swap(nums, i, start);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
