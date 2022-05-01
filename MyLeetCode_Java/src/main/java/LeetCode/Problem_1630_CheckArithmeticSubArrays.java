package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_1630_CheckArithmeticSubArrays {

    // 暴力, 每一个区间排序判断是不是等差数列
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> ans = new ArrayList<>();
        if (nums == null || nums.length == 0 || l.length != r.length) {
            return ans;
        }
        int M = l.length;
        for (int i = 0; i < M; i++) {
            if (check(nums, l[i], r[i])) {
                ans.add(true);
            } else {
                ans.add(false);
            }
        }
        return ans;
    }

    // 检查数组L...R是不是等差数列
    private boolean check(int[] nums, int L, int R) {
        // NOTE: 注意copyOfRange: from---to, to是到不了的位置
        // 同时注意, 需要拷贝一份数组, 不破坏原数组的位置关系
        int[] arr = Arrays.copyOfRange(nums, L, R + 1);
        Arrays.sort(arr);
        if (arr.length < 2) {
            return false;
        } else if (arr.length == 2) {
            return true;
        }
        // 3个及以上
        int gap = arr[1] - arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] != gap) {
                return false;
            }
        }
        return true;
    }
}
