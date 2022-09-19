package _Contest.LC.W309;

import java.util.Arrays;

public class Problem_2400_NumberOfWays {

    public int numberOfWays(int startPos, int endPos, int k) {
        int N = 1000;
        int mod = (int) 1e9 + 7;
        if (startPos < 1 || startPos > N || endPos < 1 || endPos > N || k < 1) {
            return 0;
        }
        int[][] dp = new int[N + 1][k + 1];
        dp[endPos][0] = 1;
        for (int rest = 1; rest <= k; rest++) {
            dp[1][rest] = dp[2][rest - 1];
            for (int cur = 2; cur < N; cur++) {
                dp[cur][rest] = (dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1]) % mod;
            }
            dp[N][rest] = dp[N - 1][rest - 1];
        }
        return dp[startPos][k];
    }

    public static int ways1(int startPos, int endPos, int k) {
        int N = 1000 + k;
        int[][] dp = new int[N * 2 + 1][k + 1];
        for (int i = 0; i <= N * 2; i++) {
            Arrays.fill(dp[i], -1);
        }
        return process(startPos, k, endPos, N, dp);
    }

    // cur: 当前位置
    // rest: 剩余步数
    public static int process(int cur, int rest, int endPos, int N, int[][] dp) {
        int mod = (int) 1e9 + 7;
        if (rest == 0) { // 如果已经不需要走了，走完了！
            return cur == endPos ? 1 : 0;
        }
        if (dp[cur + N][rest] != -1) {
            return dp[cur + N][rest];
        }
        long ans = 0;
        if (cur == -N) { // 走到负数尽头
            ans = process(-N + 1, rest - 1, endPos, N, dp);
        } else if (cur == N) { // 走到正数数尽头
            ans = process(N - 1, rest - 1, endPos, N, dp);
        } else {
            // 中间位置, 可以往左或者往右
            ans = (process(cur - 1, rest - 1, endPos, N, dp) + process(cur + 1, rest - 1, endPos, N, dp)) % mod;
        }
        dp[cur + N][rest] = (int) ans;
        return (int) ans;
    }


    public static void main(String[] args) {
        int start = 989;
        int end = 1000;
        int step = 99;
        var ans = ways1(start, end, step);
        System.out.println(ans);
    }


}
