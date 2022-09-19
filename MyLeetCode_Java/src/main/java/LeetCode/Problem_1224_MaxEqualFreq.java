package LeetCode;

import com.sun.source.tree.Tree;

import java.util.*;

public class Problem_1224_MaxEqualFreq {

    // https://leetcode.cn/problems/maximum-equal-frequency/solution/by-ac_oier-fviv/
    public static int maxEqualFreq(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int R = 0;
        int maxCnt = 0; // 最大次数
        // 每个数出现的次数
        // [ num, times]
        Map<Integer, Integer> cntMap = new HashMap<>();
        // 维护每个次数出现的个数
        // [times, cnts]
        Map<Integer, Integer> freqMap = new HashMap<>();
        int ans = 0;
        int times = 0;
        while (R < N) {
            // 既存, 数的times + 1
            if (cntMap.containsKey(nums[R])) {
                times = cntMap.get(nums[R]); // 老次数
                freqMap.put(times, freqMap.get(times) - 1); // 老次数-1
            }
            cntMap.put(nums[R], cntMap.getOrDefault(nums[R], 0) + 1);
            maxCnt = Math.max(maxCnt, cntMap.get(nums[R]));
            times = cntMap.get(nums[R]); // + 1后的新次数
            freqMap.put(times, freqMap.getOrDefault(times, 0) + 1);
            int len = R + 1; // 当前遍历到的总数量
            // 满足收集条件:
            // 情况1: 最大出现次数 maxCnt == 1, 随意删除一个数, 其他都是 1次
            // 情况2: 出现次数只有 maxCnt 跟 maxCnt - 1 两种, 且最大次数只有一次,
            //        删除最大次数的数, , 其他都是 maxCnt -1
            // 情况3: 只有一个数出现1次, 其他都是 maxCnt, 删除一次的, 其他都是 maxCnt
            if (maxCnt == 1 ||
                    (freqMap.get(maxCnt - 1) + 1) * (maxCnt - 1) + 1 == len ||
                    freqMap.get(maxCnt) * maxCnt + 1 == len) {
                ans = R + 1;
            }
            R++;
        }
        return ans;
    }

    public static void main(String[] args) {
        // int[] nums = {10, 2, 8, 9, 3, 8, 1, 5, 2, 3, 7, 6}; // 8
        // int[] nums = {1, 1}; // 2
        int[] nums = {1, 1, 1, 2, 2, 2, 3, 3, 3}; // 7
        // int[] nums = {1, 2}; // 2
        // int[] nums = {1,1,1,2,2,2}; // 5
        // int[] nums = {2, 2, 1, 1, 5, 3, 3, 5}; // 7
        // int[] nums = {1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5}; // 13
        var ans = maxEqualFreq(nums);
        System.out.println(ans);
    }
}
