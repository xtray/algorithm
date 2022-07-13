package LeetCode;


// IMP: 分治!!

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Problem_2035_MinArrayDiff {

    // TLE
    public int minimumDifference(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        process(nums, 0, 0, 0, 0, 0, nums.length >> 1);
        return gap;
    }

    private int gap = Integer.MAX_VALUE;

    void process(int[] nums, int index, int sum1, int sum2, int cnt1, int cnt2, int size) {
        if (index == nums.length) {
            if (cnt1 == size && cnt2 == size) {
                gap = Math.min(gap, Math.abs(sum1 - sum2));
            }
            return;
        }
        // 当前来到index位置的数
        // 可能性1: 进A数组
        if (cnt1 != size) {
            process(nums, index + 1, sum1 + nums[index], sum2, cnt1 + 1, cnt2, size);
        }
        // 可能性2: 进B数组
        if (cnt2 != size) {
            process(nums, index + 1, sum1, sum2 + nums[index], cnt1, cnt2 + 1, size);
        }
    }

    public int minimumDifference2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int half = N >> 1;
        Map<Integer, TreeSet<Integer>> leftMap = new HashMap<>();
        process2(nums, 0, half, 0, 0, leftMap);
        Map<Integer, TreeSet<Integer>> rightMap = new HashMap<>();
        process2(nums, half, N, 0, 0, rightMap);
        int sum = Arrays.stream(nums).sum();
        int ans = Integer.MAX_VALUE;
        for (int leftCnt : leftMap.keySet()) {
            for (int leftSum : leftMap.get(leftCnt)) {
                Integer rightSum = rightMap.get(half - leftCnt).floor((sum >> 1) - leftSum);
                if (rightSum != null) {
                    int pickSum = leftSum + rightSum;
                    int restSum = sum - pickSum;
                    // NOTE: restSum - pickSum >=0 , 不用取绝对值
                    // 因为选的pickSum <= sum的一半, 必然restSum要更大一点
                    ans = Math.min(ans, restSum - pickSum);
                }
            }
        }
        return ans;
    }

    // 当前来到index位置, 数组结束位置end
    // 已经挑选的个数pick, 挑选的数字累加和sum
    // leftMap : key: 个数, value: pick个数字凑出来的所有累加和
    private void process2(int[] nums, int index, int end, int pick, int sum,
                          Map<Integer, TreeSet<Integer>> map) {
        if (index == end) {
            map.computeIfAbsent(pick, key -> new TreeSet<>()).add(sum);
            return;
        }
        // 没到最后一个数字, 还有数可以挑
        // 可能性: 要, 不要
        process2(nums, index + 1, end, pick, sum, map);
        process2(nums, index + 1, end, pick + 1, sum + nums[index], map);
    }

    public static void main(String[] args) {
        int[] nums = {-36, 36};
        var ans = new Problem_2035_MinArrayDiff().minimumDifference2(nums);
        System.out.println(ans);
    }

}
