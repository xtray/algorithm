package LeetCode.Jianzhi;

public class Problem_JZ10_II_FrogJumps {

    public int numWays(int n) {
        if(n <= 1) {
            return 1;
        }
        int mod = (int) 1e9 + 7;
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i<=n; i++) {
            dp[i] = (dp[i-1] + dp[i-2])% mod;
        }
        return dp[n];
    }

    public int numWays2(int n) {
        if(n <= 1) {
            return 1;
        }
        int mod = (int) 1e9 + 7;
        int pre1 = 1; // 0
        int pre2 = 1; // 1
        for(int i = 2; i<=n; i++) {
            int cur = (pre1 + pre2)% mod;
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }
}
