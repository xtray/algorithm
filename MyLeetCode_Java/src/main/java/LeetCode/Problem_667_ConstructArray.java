package LeetCode;

import java.util.Arrays;

public class Problem_667_ConstructArray {

    // https://leetcode.cn/problems/beautiful-arrangement-ii/solution/667-you-mei-de-pai-lie-ii-by-wa-pian-d-x35l/

    // 凑差值为k, k-1, k-2....1....1
    public int[] constructArray(int n, int k) {
        int[] nums = new int[n];
        // 第一个数选1
        nums[0] = 1;
        // k+1 - 1
        for (int i = 1, gap = k; i <= k; i++, gap--) {
            if ((i & 1) == 1) {
                nums[i] = nums[i - 1] + gap;
            } else {
                nums[i] = nums[i - 1] - gap; // gap比之前小1了已经
            }
            // gap--
        }
        int idx = k + 1;
        while (idx < n) {
            nums[idx] = idx++ + 1;
        }
        return nums;
    }

    public static void main(String[] args) {
        int n = 3;
        int k = 1;
        var ans = new Problem_667_ConstructArray().constructArray(n, k);
        System.out.println(Arrays.toString(ans));
    }

}
