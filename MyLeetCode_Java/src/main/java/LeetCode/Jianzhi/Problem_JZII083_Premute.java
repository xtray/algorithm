package LeetCode.Jianzhi;

import java.util.ArrayList;
import java.util.List;

public class Problem_JZII083_Premute {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        process(nums, 0, ans);
        return ans;
    }

    // 对数组原地调整, 当前处理到index位置, 收集所有可能的答案
    private void process(int[] nums, int index, List<List<Integer>> ans) {
        if (index == nums.length) {
            List<Integer> res = new ArrayList<>();
            for (int num : nums) {
                res.add(num);
            }
            ans.add(res);
            return;
        }
        // 尝试从index位置开始到结束每一个位置来到index位置
        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);
            process(nums, index + 1, ans);
            swap(nums, index, i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
