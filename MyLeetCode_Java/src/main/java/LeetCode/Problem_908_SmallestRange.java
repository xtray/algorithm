package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_908_SmallestRange {

    // 最大线段重合问题
    public int smallestRangeI(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        List<int[]> list = new ArrayList<>();
        int minVal = nums[0];
        int maxVal = nums[0];
        for (int num : nums) {
            list.add(new int[]{num - k, 1});
            list.add(new int[]{num + k, -1});
            minVal = Math.min(minVal, num);
            maxVal = Math.max(maxVal, num);
        }
        list.sort((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        int cnt = 0;
        int val = 0;
        for (int[] p : list) {
            val += p[1];
            cnt = Math.max(cnt, val);
        }
        return cnt == N ? 0 : maxVal - minVal - 2 * k;
    }

    // ref: https://leetcode-cn.com/problems/smallest-range-i/solution/by-ac_oier-7fh0/
    // 因此如果原来的最大差值为d=max−min，若d<=2*k 时，答案为0，否则答案为kd−2∗k。
    public int smallestRangeI2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int minVal = nums[0];
        int maxVal = nums[0];
        for (int num : nums) {
            minVal = Math.min(minVal, num);
            maxVal = Math.max(maxVal, num);
        }
        return Math.max(0, maxVal - minVal - 2 * k);
    }


}
