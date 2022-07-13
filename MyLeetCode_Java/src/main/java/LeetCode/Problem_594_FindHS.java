package LeetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem_594_FindHS {

    // 哈希做法: 符合条件的和谐子序列长度为相邻两数（差值为1) 的出现次数之和。
    public int findLHS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 以每个数做最小值,找所有与他+1 的数量累加
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) { // 统计词频
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        int maxLen = 0;
        for (int num : count.keySet()) {
            if (count.containsKey(num + 1)) { // 没有比当前大的就是 0
                int cur = count.get(num) + count.get(num + 1);
                maxLen = Math.max(maxLen, cur);
            }
        }
        return maxLen;
    }


    // NOTE: 不定长滑动窗口解法
    public int findLHS2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int L = 0, R = 0, ans = 0;
        int N = nums.length;
        while (R < N) {
            if (nums[R] - nums[L] <= 1) {
                if (nums[R] - nums[L] == 1) {
                    ans = Math.max(ans, R - L + 1);
                }
                R++;
            } else { // > 1
                L++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Problem_594_FindHS sl = new Problem_594_FindHS();
//        int[] nums = new int[]{1, 1, 1, 1};
//        int[] nums = new int[]{1,3,2,2,5,2,3,7};
        int[] nums = new int[]{1,2,2,1};
        int ans = sl.findLHS(nums);
        System.out.println(ans);
        int ans1 = sl.findLHS2(nums);
        System.out.println(ans1);
    }
}
