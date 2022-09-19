package _DailyTarget;

import java.util.HashMap;
import java.util.Map;

public class Problem_JZII011_OneZeroSubArrays {

    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int[] sum = new int[N];
        int pre = 0;
        for (int i = 0; i < N; i++) {
            sum[i] = pre + (nums[i] == 0 ? -1 : 1);
            pre = sum[i];
        }

        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // 0的前缀和最早出现在-1位置, 一个数也不选
        for (int i = 0; i < N; i++) {
            // int cur = sum[i] - 0;
            int cur = sum[i];
            if (map.containsKey(cur)) {
                ans = Math.max(ans, i - map.get(cur));
            } else {
                map.put(cur, i); // cur这个前缀和最早出现在i位置, 有数据时不更新
            }

        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1};
        var ans = new Problem_JZII011_OneZeroSubArrays().findMaxLength(nums);
        System.out.println(ans);
    }
}
