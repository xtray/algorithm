package LeetCode;

import java.util.ArrayList;
import java.util.List;

// NOTE: 多看!!
// ref: 20220304: 最长连续树木

public class Problem_1004_LongestOnes {

    public int longestOnes(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int N = nums.length;
        List<Integer> list = new ArrayList<>(); // 存储所有0的位置
        for (int i = 0; i < N; i++) {
            if (nums[i] == 0) {
                list.add(i);
            }
        }
        int ans = 0;
        int start = 0; // 第一个1的位置, 如果0位置是0, 也会补上的
        for (int i = 0, j = k; j < list.size(); i++, j++) {
            // i 处理到的洞的编号
            ans = Math.max(ans, list.get(j) - start); // 补齐j个(0~j-1)空洞, j+1个洞前一个位置减开始
            start = list.get(i) + 1; // 第一个洞的下一个位置
        }
        ans = Math.max(ans, N - start);
        return ans;
    }


    /**
     * 把 最多可以把 K 个 0 变成 1，求仅包含 1 的最长子数组的长度
     * 转换为
     * 找出一个最长的子数组，该子数组内最多允许有 K 个 0 。
     * TAG: 滑动窗口
     */
    public int longestOnes1(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int N = nums.length;
        int ans = 0;
        int L = 0, R = 0;
        int zeros = 0;
        while (R < N) {
            if (nums[R] == 0) {
                zeros++;
            }
            while (zeros > k) {
                if (nums[L] == 0) {
                    zeros--;
                }
                L++;
            }
            // zeros == k
            ans = Math.max(ans, R - L + 1);
            R++;
        }
        return ans;
    }
}
