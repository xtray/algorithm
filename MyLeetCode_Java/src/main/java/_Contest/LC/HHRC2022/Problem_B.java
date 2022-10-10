package _Contest.LC.HHRC2022;

// https://leetcode.cn/contest/hhrc2022/problems/0Wx4Pc/

// ref: https://leetcode.cn/problems/longest-well-performing-interval/

import java.util.HashMap;
import java.util.*;

public class Problem_B {

    public int longestESR(int[] sales) {
        int N = sales.length;
        for (int i = 0; i < N; i++) {
            if (sales[i] > 8) {
                sales[i] = 1;
            } else {
                sales[i] = -1;
            }
        }
        // 累加和>=1的最大子数组长度
        // [10, 2,  1,  4, 3,  9,  6,  9,  9]
        // [1, -1,- 1, -1, -1, 1, -1,  1,  1]
        // [1,  0, -1, -2, -3, -2, -3, -2, -1]
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // NOTE: 重要, 0 这个累加和最早出现在-1位置;
        int ans = 0;
        int sum = 0;
        // 考虑以每一个位置结尾的子数组
        for (int i = 0; i < N; i++) {
            sum += sales[i];
            if (sum > 0) {
                ans = Math.max(ans, i+1);
            }
            var pre = map.get(sum - 1);
            if (pre != null) {
                ans = Math.max(ans, i - pre);
            }
            if (!map.containsKey(sum)) { // 如果我当前这个前缀和在map里没有我才更新!!
                map.put(sum, i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // int[] sales = {10, 2, 1, 4, 3, 9, 6, 9, 9};
        // int[] sales = {5, 6, 7};
        int[] sales = {9, 9, 9};
        var ans = new Problem_B().longestESR(sales);
        System.out.println(ans);
    }


}
