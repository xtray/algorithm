package _Simulation.D0812;

public class Problem_A_Fib {

    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        int dp2 = 0;
        int dp1 = 1;
        for (int i = 2; i <= n; i++) {
            int cur = dp1 + dp2;
            dp2 = dp1;
            dp1 = cur;
        }
        return dp1;
    }
}
