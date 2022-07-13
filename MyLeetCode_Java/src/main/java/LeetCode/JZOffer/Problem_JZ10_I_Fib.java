package LeetCode.JZOffer;

public class Problem_JZ10_I_Fib {

    private static int mod = (int) 1e9 + 7;

    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        return (fib(n - 1) % mod + fib(n - 2) % mod) % mod;
    }

    public static int fib2(int n) {
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] % mod + dp[i - 2] % mod) % mod;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 4;
        var ans = fib2(4);
        System.out.println(ans);
    }
}
