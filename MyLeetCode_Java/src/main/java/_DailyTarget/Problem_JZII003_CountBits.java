package _DailyTarget;

public class Problem_JZII003_CountBits {

    public int[] countBits(int n) {
        if (n <= 0) {
            return new int[]{0};
        }
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ans[i] = getCnt(i);
        }
        return ans;
    }

    private int getCnt(int num) {
        int cnt = 0;
        while (num != 0) { // NOTE: 如果有负数, 要小心
            if ((num & 1) != 0) {
                cnt++;
            }
            num >>>= 1; // NOTE: 如果有负数, 要小心
        }
        return cnt;
    }

    public int[] countBits1(int n) {
        if (n <= 0) {
            return new int[]{0};
        }
        // 0~n
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - (i & (-i))] + 1;
        }
        return dp;
    }
}
