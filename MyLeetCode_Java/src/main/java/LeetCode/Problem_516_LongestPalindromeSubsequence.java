package LeetCode;

// IMP: 最长回文子序列长度
public class Problem_516_LongestPalindromeSubsequence {

    // 范围尝试模型
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        return process(s.toCharArray(), 0, s.length() - 1);

    }

    //
    // str 从L...R范围上的最长回文子序列长度
    private int process(char[] str, int L, int R) {
        if (L > R) {
            return 0;
        }
        if (L == R) {
            return 1;
        }
        if (L == R - 1) {
            return str[L] == str[R] ? 2 : 1;
        }
        // L...R这一段最长回文子序列的可能性
        // 1. 既不以L开头也不以R结尾
        int p1 = process(str, L + 1, R - 1);
        // 2. 以L开头,不以R结尾
        int p2 = process(str, L, R - 1);
        // 3. 不以L开头, 以R结尾
        int p3 = process(str, L + 1, R);
        // 4. 既以L开头也以R结尾
        int p4 = str[L] == str[R] ? 2 + process(str, L + 1, R - 1) : 0;
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    // 范围尝试模型
    // 改DP
    public int longestPalindromeSubseq2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        dp[N - 1][N - 1] = 1;
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        for (int L = N - 3; L >= 0; L--) {
            for (int R = L + 2; R < N; R++) {
                int p1 = dp[L + 1][R - 1];
                int p2 = dp[L][R - 1];
                int p3 = dp[L + 1][R];
                int p4 = str[L] == str[R] ? dp[L + 1][R - 1] + 2 : 0;
                dp[L][R] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
            }
        }
        return dp[0][N - 1];
    }

    // 优化
    public int longestPalindromeSubseq3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        dp[N - 1][N - 1] = 1;
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        for (int L = N - 3; L >= 0; L--) {
            for (int R = L + 2; R < N; R++) {
                dp[L][R] = Math.max(dp[L][R - 1], dp[L + 1][R]);
                dp[L][R] = Math.max(dp[L][R], str[L] == str[R] ? dp[L + 1][R - 1] + 2 : 0);
            }
        }
        return dp[0][N - 1];
    }

    // 最长公共子序列的解法
    public int longestPalindromeSubseq4(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        String s2 = new StringBuilder(s).reverse().toString();
        return longestCommonSubsequence(s, s2);
    }

    // dp[i][j]
    // str1从0...i 跟 str2 从0...j的最长公共子序列
    public int longestCommonSubsequence(String s1, String s2) {
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length;
        int M = str2.length;
        int[][] dp = new int[N][M];

        dp[0][0] = str1[0] == str2[0] ? 1 : 0;

        for (int j = 1; j < M; j++) {
            dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
        }
        for (int i = 1; i < N; i++) {
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                if (str1[i] == str2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[N - 1][M - 1];
    }
}
