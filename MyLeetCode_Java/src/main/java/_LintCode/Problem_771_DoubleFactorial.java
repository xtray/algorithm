package _LintCode;

public class Problem_771_DoubleFactorial {

    public long doubleFactorial(int n) {
        if (n <= 0) {
            return -1;
        }
        if (n <= 2) {
            return n;
        }
        return n * doubleFactorial(n - 2);
    }

    public long doubleFactorial2(int n) {
        if (n <= 0) {
            return -1;
        }
        if (n <= 2) {
            return n;
        }
        long[] dp = new long[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = i * dp[i - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 1;
        var ans = new Problem_771_DoubleFactorial().doubleFactorial2(n);
        System.out.println(ans);
    }
}
