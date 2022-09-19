package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_1703_MinMoves {

    // https://leetcode.com/problems/minimum-adjacent-swaps-for-k-consecutive-ones/discuss/987607/O(n)-explanation-with-picture
    // https://leetcode.com/problems/minimum-adjacent-swaps-for-k-consecutive-ones/discuss/2260564/sliding-window-and-prefix-sum
    // 每个窗口内最小的移动步数是移动到中位数位置
    // 比如窗口: 2     3    7     9    10
    //                     ^
    // 先移动成  7     7    7     7     7
    //       (7-2)  (7-3)      (9-7)  (10-7)
    // 总Cost: 右侧(9+10)  - 左侧(2+3), 用前缀和优化
    // 但是多走了, 最终应该以7为中心
    //          5     6    7     8     9
    // 多走了:   2     1    0     1     2
    // 以7为中心的, 半径为 (k-1)/2   (1+2..+r)*2 = (1+r)*r
    // 如果是K是偶数
    // 0 2 4 6 7 9 -> 4 4 4 4 4 4
    // cost: 右侧(6+7+9) - 左侧(0+2) - 4 (补一个, 9 - 4)
    // 0 2 4 6 7 9 -> 2 3 4 5 6 7
    // 半径: (k-2)/2 = 2
    // 总cost: 右侧(6+7+9) - 左侧(0+2) - 中位数(4) - (1+r)*r - (r+1)
    public int minMoves(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return Integer.MAX_VALUE;
        }
        int N = nums.length;
        List<Integer> onesIdx = new ArrayList<>();
        // 1.收集所有1的坐标
        for (int i = 0; i < N; i++) {
            if (nums[i] == 1) {
                onesIdx.add(i);
            }
        }
        if (k == 1) {
            return 0;
        }
        N = onesIdx.size();
        Integer[] ones = onesIdx.toArray(new Integer[N]);
        // 2. 计算前缀和
        int[] sum = new int[N];
        int pre = 0;
        for (int i = 0; i < N; i++) {
            sum[i] = pre + ones[i];
            pre = sum[i];
        }
        // 3. 固定K长度窗口
        int L = 0;
        int R = k - 1;
        boolean odd = (k & 1) != 0;
        int radius = odd ? (k - 1) / 2 : (k - 2) / 2;
        // int radius = (k + 1) / 2  -1;
        // k是偶数时, 多减了一块 半径+1
        int save = radius * (radius + 1) + (odd ? 0 : (radius + 1));
        int ans = Integer.MAX_VALUE;
        while (R < N) {
            int mid = L + ((R - L) >> 1);
            int right = sum[R] - sum[mid];
            // 看有没有左边 L...mid-1,
            // mid-1存在需要 sum[mid-1] - sum[L-1], 所以要检查L是不是0
            int left = mid == 0 ? 0 : (sum[mid - 1] - (L == 0 ? 0 : sum[L - 1]));
            // 当k是偶数时候, sum求和多算的中间值要减掉
            int cost = right - left - (odd ? 0 : ones[mid]);
            ans = Math.min(ans, cost - save);
            L++;
            R++;
        }
        return ans;
    }


    public int minMoves1(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return Integer.MAX_VALUE;
        }
        int N = nums.length;
        List<Integer> onesIdx = new ArrayList<>();
        // 1.收集所有1的坐标
        for (int i = 0; i < N; i++) {
            if (nums[i] == 1) {
                onesIdx.add(i);
            }
        }
        if (k == 1) {
            return 0;
        }
        N = onesIdx.size();
        Integer[] ones = onesIdx.toArray(new Integer[N]);
        // 2. 计算前缀和
        // sum[i+1] 位置存到 数组i位置的前缀和, 方便后面写代码
        int[] sum = new int[N + 1];
        for (int i = 0; i < N; i++) {
            sum[i + 1] = sum[i] + ones[i];
        }
        // 3. 固定K长度窗口
        int L = 0;
        int R = k - 1;
        boolean odd = (k & 1) != 0;
        int radius = odd ? (k - 1) / 2 : (k - 2) / 2;
        int save = radius * (radius + 1) + (odd ? 0 : (radius + 1));
        int ans = Integer.MAX_VALUE;
        while (R < N) {
            int mid = L + ((R - L) >> 1);
            int right = sum[R + 1] - sum[mid + 1];
            // 看有没有左边 L...mid-1
            int left = mid == 0 ? 0 : (sum[mid] - sum[L]);
            int cost = right - left - (odd ? 0 : ones[mid]);
            ans = Math.min(ans, cost - save);
            L++;
            R++;
        }
        return ans;
    }

    public static void main(String[] args) {
        // int[] nums = {1, 0, 0, 1, 0, 1};
        // int k = 2; // 1
        // int[] nums = {0, 1, 1, 0, 0, 1, 0, 0, 0};
        // int k = 3; // 2
        int[] nums = {0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0};
        int k = 7; // 4
        var ans = new Problem_1703_MinMoves().minMoves(nums, k);
        System.out.println(ans);
    }
}
