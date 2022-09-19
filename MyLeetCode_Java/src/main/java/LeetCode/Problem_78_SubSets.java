package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Problem_78_SubSets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        process(nums, 0, new ArrayList<>(), ans);
        return ans;
    }

    public static void process(int[] nums, int index, List<Integer> path, List<List<Integer>> ans) {
        if (index == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        // 可能性1: 不要index
        process(nums, index + 1, path, ans);
        // 可能性2: 要index
        path.add(nums[index]);
        process(nums, index + 1, path, ans);
        path.remove(path.size()-1); // 删除最后一个很快
    }
}
