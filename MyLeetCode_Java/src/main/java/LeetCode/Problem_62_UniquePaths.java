package LeetCode;

// IMP: 重要基础题

public class Problem_62_UniquePaths {

    // dp[i][j]: 从(0,0)点到(i,j)点的路径条数
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        // 0 行都是1
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        // 0 列都是1
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    // 空间压缩的办法
    public int uniquePaths2(int m, int n) {
        int[] dp = new int[n];
        // 0 行都是1
        for (int j = 0; j < n; j++) {
            dp[j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j-1];
            }
        }
        return dp[n - 1];
    }

    // 数学的方法
    // 机器人一共要走 M + N -2 步, 里头选N-1步向下
    public int uniquePaths3(int m, int n) {
        return (int) c(m + n - 2, n - 1);
    }

    // ref: https://leetcode-cn.com/problems/unique-paths/solution/bu-tong-lu-jing-by-leetcode-solution-hzjf/
    // 从一共a个数里，选b个数，方法数是多少
    public static long c(int a, int b) {
        if (a == b) {
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
}
