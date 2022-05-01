package LeetCode;

public class Problem_96_NumTrees {

    //	k(0) = 1, k(1) = 1
    //
    //	k(n) = k(0) * k(n - 1) + k(1) * k(n - 2) + ... + k(n - 2) * k(1) + k(n - 1) * k(0)
    //	或者
    //	k(n) = c(2n, n) / (n + 1)
    //	或者
    //	k(n) = c(2n, n) - c(2n, n-1)
    public int numTrees(int n) {
        if (n <= 1) {
            return 1;
        }
        // C_2n_n - C_2n_{n-1}
        return (int) (getCnum(n) / (n + 1));
    }

    // C_2N_N
    private long getCnum(int N) {
        long a = 1;
        long b = 1;
        for (int i = 1, j = N + 1; i <= N; i++, j++) {
            a *= i; // 1-->N
            b *= j; // N+1 --> 2N
            long gcd = gcd(a, b);
            a /= gcd;
            b /= gcd;
        }
        return b / a;
    }

    private static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }


    // 动态规划
    public static long num1(int N) {
        if (N < 0) {
            return 0;
        }
        if (N < 2) {
            return 1;
        }
        long[] dp = new long[N + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            for (int leftSize = 0; leftSize < i; leftSize++) {
                dp[i] += dp[leftSize] * dp[i - 1 - leftSize];
            }
        }
        return (int)dp[N];
    }

    public int numTrees3(int n) {
        // 提示：我们在这里需要用 long 类型防止计算过程中的溢出
        long C = 1;
        for (int i = 0; i < n; ++i) {
            C = C * 2 * (2L * i + 1) / (i + 2);
        }
        return (int) C;
    }


}
