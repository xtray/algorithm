package _DailyTarget;

public class Problem_JZ10_I_Fib {

    private static final int mod = (int) 1e9 + 7;

    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        int dp2 = 0; // 倒数第2个, 0
        int dp1 = 1; // 倒数第1个, 1
        for (int i = 2; i <= n; i++) {
            int cur = (dp1 + dp2) % mod;
            dp2 = dp1;
            dp1 = cur;
        }
        return dp1;
    }
}
