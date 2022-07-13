package LeetCode.MianshiJindian;

public class Problem_0801_ThreeStepStair {

    public int waysToStep(int n) {
        if (n < 1) {
            return 0;
        }
        return process(n, 0);
    }

    private int process(int n, int index) {
        int mod = (int) (1e9) + 7;
        if (index == n) {
            return 1;
        }
        if (index > n) { // NOTE: 超出后为0
            return 0;
        }
        long ans = 0;
        ans = process(n, index + 1);
        ans += process(n, index + 2);
        ans += process(n, index + 3);
        return (int) (ans % mod);
    }

    public int waysToStep2(int n) {
        int mod = (int) (1e9) + 7;
        if (n < 1) {
            return 0;
        }
        int[] dp = new int[n + 3];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            long ans = 0;
            ans += dp[i + 1];
            ans += dp[i + 2];
            ans += dp[i + 3];
            dp[i] = (int) (ans % mod);
        }
        return dp[0];
    }

    public int waysToStep3(int n) {
        int mod = (int) (1e9) + 7;
        if (n < 1) {
            return 0;
        }
        int dp1 = 1;
        int dp2 = 0;
        int dp3 = 0;
        for (int i = n - 1; i >= 0; i--) {
            long ans = 0;
            ans += dp1;
            ans += dp2;
            ans += dp3;
            dp3 = dp2;
            dp2 = dp1;
            dp1 = (int) (ans % mod);
        }
        return dp1;
    }

    // PENDING: 解法4, 矩阵快速幂

}
