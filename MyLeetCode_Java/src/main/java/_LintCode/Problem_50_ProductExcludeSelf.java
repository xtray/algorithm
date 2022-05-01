package _LintCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_50_ProductExcludeSelf {
    public List<Long> productExcludeItself(List<Integer> nums) {
        List<Long> ans = new ArrayList<>();
        if (nums == null || nums.size() == 0) {
            return ans;
        }
        int N = nums.size();
        long product = 1;
        long[] right = new long[N];
        right[N - 1] = Long.valueOf(nums.get(N - 1));
        for (int i = N - 2; i >= 0; i--) {
            right[i] = nums.get(i) * right[i + 1];
        }
        long left = 1;
        for (int i = 0; i < N - 1; i++) {
            ans.add(left * right[i + 1]);
            left *= nums.get(i);
        }
        // 计算i-1的部分
        ans.add(left);
        return ans;
    }
}
