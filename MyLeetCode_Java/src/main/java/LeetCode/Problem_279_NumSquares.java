package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_279_NumSquares {

    public int numSquares(int n) {
        // 收集1~n之间的完全平方数
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            list.add(i * i);
        }
        return process(list, 0, n);
    }

    private int process(List<Integer> list, int i, int rest) {
        if (i == list.size()) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        // 1.不要i位置的数
        int p1 = process(list, i + 1, rest);
        // 2.要i位置的数
        int p2 = Integer.MAX_VALUE;
        if (rest >= list.get(i)) {
            int next = process(list, i, rest - list.get(i));
            if (next != Integer.MAX_VALUE) {
                p2 = 1 + next;
            }
        }
        return Math.min(p1, p2);
    }

    public int numSquares2(int n) {
        // 收集1~n之间的完全平方数
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            list.add(i * i);
        }
        int size = list.size();
        int[][] dp = new int[size + 1][n + 1];
        for (int i = 0; i <= size; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[size][0] = 0;
        for (int i = size - 1; i >= 0; i--) {
            for (int j = 0; j <= n; j++) {
                // 1.不要i位置的数
                int p1 = dp[i + 1][j];
                // 2.要i位置的数
                int p2 = Integer.MAX_VALUE;
                if (j >= list.get(i)) {
                    int next = dp[i][j - list.get(i)];
                    if (next != Integer.MAX_VALUE) {
                        p2 = 1 + next;
                    }
                }
                dp[i][j] = Math.min(p1, p2);
            }
        }
        return dp[0][n];
    }


    // 动态规划中的空间压缩技巧
    // dp[i][j]只依赖自己同行已经算过的跟下一行已经算出来的
    public int numSquares3(int n) {
        // 收集1~n之间的完全平方数
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            list.add(i * i);
        }
        int size = list.size();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = size - 1; i >= 0; i--) {
            for (int j = 0; j <= n; j++) {
                // 1.不要i位置的数
                int p1 = dp[j];
                // 2.要i位置的数
                int p2 = Integer.MAX_VALUE;
                if (j >= list.get(i)) {
                    int next = dp[j - list.get(i)];
                    if (next != Integer.MAX_VALUE) {
                        p2 = 1 + next;
                    }
                }
                dp[j] = Math.min(p1, p2);
            }
        }
        return dp[n];
    }

    // 四平方和定理
    // 数学解
    // 规律一：个数不超过4
    // 规律二：出现1个的时候，显而易见
    // 规律三：任何数 % 8 == 7，一定是4个
    // 规律四：任何数消去4的因子之后，剩下rest，rest % 8 == 7，一定是4个
    public static int numSquares1(int n) {
        while (n % 4 == 0) {
            n /= 4;
        }
        if (n % 8 == 7) {
            return 4;
        }
        // 看是不是两个
        for (int a = 0; a * a <= n; ++a) {
            // a * a +  b * b = n
            int b = (int) Math.sqrt(n - a * a);
            if (a * a + b * b == n) {
                return (a > 0 && b > 0) ? 2 : 1;
            }
        }
        return 3;
    }
}
