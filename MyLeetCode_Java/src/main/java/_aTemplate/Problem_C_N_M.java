package _aTemplate;

public class Problem_C_N_M {

    // 从一共a个数里，选b个数，方法数是多少
    public static long c(int a, int b) {
        if (b == 0 || a == b) {
            return 1;
        }
        long x = 1;
        long y = 1;
        for (int i = b + 1, j = 1; i <= a; i++, j++) {
            x *= i;
            y *= j;
            long gcd = gcd(x, y);
            x /= gcd;
            y /= gcd;
        }
        return x / y;
    }

    private static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // C(n, m) = C(n - 1, m - 1) + C(n - 1, m)
    public static long c1(int n, int m) {
        if (m == 0 || m == n) {
            return 1;
        }
        return c1(n - 1, m - 1) + c1(n - 1, m);
    }

    // C(n, m) = C(n - 1, m - 1) + C(n - 1, m)
    // C(n, m) = C(n, n-m), 只用求一半即可
    // C(n,0)=1 C(n,n)=1 C(0,0)=1
    public static long c2(int n, int m) {
        if (m == 0 || m == n) {
            return 1;
        }
        long[][] dp = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }
        // dp[1][0], dp[1][1]已求, 从i==2开始
        // j>=1
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i/2; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                dp[i][i - j] = dp[i][j];
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {

        int a = 10;
        int b = 5;
        var ans = c(a, b);
        System.out.println(ans);

        var ans1 = c1(a, b);
        System.out.println(ans1);

        var ans2 = c2(a, b);
        System.out.println(ans2);
    }
}
