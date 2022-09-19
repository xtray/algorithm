package _Exercise;

public class Problem_70_ClimbStairs {

    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    public int climbStairs1(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int dp1 = 2;
        int dp2 = 1;
        for (int i = 3; i <= n; i++) {
            int cur = dp1 + dp2;
            dp2 = dp1;
            dp1 = cur;
        }
        return dp1;
    }

    public int climbStairs2(int n) {
        if(n <= 1) {
            return 1;
        }
        int pre1 = 1; // 0
        int pre2 = 1; // 1
        for(int i = 2; i<=n; i++) {
            int cur = pre1 + pre2;
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }
}
