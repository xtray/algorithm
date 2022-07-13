package LeetCode.JZOffer;

public class Problem_JZII003_CountBits {

    public int[] countBits(int n) {
        if (n < 0) {
            return new int[0];
        }
        int[] ans = new int[n + 1];
        ans[0] = 0;
        if (n == 0) {
            return ans;
        }
        for (int i = 1; i <= n; i++) {
            ans[i] = getOneCnt(i);
        }
        return ans;
    }

    private int getOneCnt(int num) {
        int cnt = 0;
        while (num > 0) {
            if ((num & 1) != 0) {
                cnt++;
            }
            num >>>= 1;
        }
        return cnt;
    }

    // NOTE: 动态规划的做法
    // 左右侧的1
    public int[] countBits1(int n) {
        if (n < 0) {
            return new int[0];
        }
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - (i & (-i))] + 1;
        }
        return dp;
    }
}
