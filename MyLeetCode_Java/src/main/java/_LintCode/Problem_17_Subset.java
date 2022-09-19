package _LintCode;

import java.util.*;

public class Problem_17_Subset {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        process(nums, 0, new ArrayList<>(), ans);
        return ans;
    }

    private void process(int[] nums, int i, List<Integer> path, List<List<Integer>> ans) {
        if(i == nums.length) {
            List<Integer> list = new ArrayList<>(path);
            ans.add(list);
            return;
        }
        process(nums, i+1, path, ans);
        path.add(nums[i]);
        process(nums, i+1, path, ans);
        path.remove(Integer.valueOf(nums[i]));
    }
}
