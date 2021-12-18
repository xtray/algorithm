package LeetCode;

public class Problem_375_GetMoneyAmount {

    public int getMoneyAmount(int n) {
        if (n == 1) {
            return 0;
        }
        return process(1, n);
    }

    // L...R 范围上猜数字, 最大费用
    public int process(int L, int R) {
        if (L == R) {
            return 0;
        }
        int res = Integer.MAX_VALUE;
        // 尝试所有为第一个猜的数
        for (int i = L; i <= R; i++) {
            // 可能性 1: 对了
            int p1 = 0;
            // 可能性 2: 大了
            int p2 = i + process(L, i - 1);
            // 可能性 3: 小了
            int p3 = i + process(i + 1, R);
            res = Math.min(res, Math.max(p2, p3));
        }
        return res;
    }

    public int getMoneyAmount2(int n) {
        if (n == 1) {
            return 0;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(1, n, dp);
    }

    public int process2(int L, int R, int[][] dp) {
        if (L >= R) {
            return  0;
        }
        if (dp[L][R] != -1) {
            return dp[L][R];
        }
        int res = Integer.MAX_VALUE;
        // 尝试所有为第一个猜的数
        for (int i = L; i <= R; i++) {
            // 可能性 1: 对了
            int p1 = 0;
            // 可能性 2: 大了
            int p2 = i + process2(L, i - 1, dp);
            // 可能性 3: 小了
            int p3 = i + process2(i + 1, R, dp);
            res = Math.min(res, Math.max(p2, p3));
        }
        dp[L][R] = res;
        return res;
    }

    public static void main(String[] args) {
        Problem_375_GetMoneyAmount sl = new Problem_375_GetMoneyAmount();
        int ans = sl.getMoneyAmount2(10);
        System.out.println(ans);
    }

}
