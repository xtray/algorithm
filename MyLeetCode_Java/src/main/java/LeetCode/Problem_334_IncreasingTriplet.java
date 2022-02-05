package LeetCode;

public class Problem_334_IncreasingTriplet {

    // 动态规划:
    // dp[i]: 子序列必须以 i 位置的数结尾, 最长递增子序列的长度是多长
    // 复杂度 O(N^2)
    // TLE
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        int N = nums.length;
        int[] dp = new int[N];
        dp[0] = 1;
        int maxLen = 1;
        for (int i = 1; i < N; i++) {
            // 可能性 1: 不往左扩
            int p1 = 1;
            // 可能性 2: 往左扩
            int p2 = 0;
            // 谁比你小, 谁的 dp 值最大+1
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    p2 = Math.max(p2, dp[j] + 1);
                }
            }
            dp[i] = Math.max(p1, p2);
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen >= 3;
    }

    // 使用 最长递增子序列 ends 数组
    // ends[i]: 目前遍历到的位置所有长度为 i+1 的子序列中的最小结尾是 ends[i]的值
    // 复杂度 O(n*logn)
    public boolean increasingTriplet1(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        int N = nums.length;
        int[] ends = new int[N];
        ends[0] = nums[0];
        int right = 0; // ends 数组的右边界
        int l = 0;
        int r = 0;
        int m = 0;
        int max = 1; // ends 数组的最大长度
        for (int i = 1; i < N; i++) {
            l = 0;
            r = right;
            // 在 ends 数组找大于等于 nums[i]最左的位置
            int ans = -1;
            while (l <= r) {
                m = (l + r) / 2;
                if (ends[m] < nums[i]) {
                    l = m + 1;// 如果 nums[i]大于 ends 里所有的数, l会扩充
                } else { // ends[m] >= nums[i]
                    ans = m;
                    r = m - 1;
                }
            }
            // System.out.println(ans);
            right = Math.max(right, l); // 扩充边界
            ends[l] = nums[i];
            max = Math.max(max, l + 1); // 长度是 i+1
        }
        return max >= 3;
    }

    // ends 数组其实只需要维持长度 2 即可
    // 使用长度为 2 的定长ends 数组, 复杂度降为 O(N), 空间复杂度降为 O(1)
    public boolean increasingTriplet2(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        int N = nums.length;
        int[] ends = new int[2];
        ends[0] = nums[0];
        ends[1] = Integer.MAX_VALUE;
        for (int i = 1; i < N; i++) {
            if (nums[i] > ends[1]) { // 长度>=3 个的开始
                return true;
            }
            // 满足替换长度为 2 的最小结尾的条件
            if (ends[1] >= nums[i] && ends[0] < nums[i]) {
                ends[1] = nums[i];
            }
            // 满足替换长度为 1 的最小结尾的条件
            if (ends[0] >= nums[i]) {
                ends[0] = nums[i];
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {20, 100, 10, 12, 5, 13};
        var ans = new Problem_334_IncreasingTriplet().increasingTriplet1(nums);
        System.out.println(ans);
    }
}
