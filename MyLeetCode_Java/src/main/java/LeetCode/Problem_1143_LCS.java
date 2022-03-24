package LeetCode;

public class Problem_1143_LCS {

    public int longestCommonSubsequence(String s1, String s2) {
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) {
            return 0;
        }
        int N = s1.length();
        int M = s2.length();
        return process(s1.toCharArray(), N - 1, s2.toCharArray(), M - 1);
    }

    // TLE
    // str1 从0~i 跟 str2 都从 0~j的最长公共子序列长度
    // p1: 不要i位置 要j
    // p2: 不要j位置 要i
    // P3: 不要i也不要j
    // p4: 既要i也要j
    private int process(char[] str1, int i, char[] str2, int j) {
        if (i == 0 && j == 0) {
            return str1[i] == str2[j] ? 1 : 0;
        }
        if (i == 0) {
            return str1[i] == str2[j] ? 1 : process(str1, 0, str2, j - 1);
        }
        if (j == 0) {
            return str1[i] == str2[j] ? 1 : process(str1, i - 1, str2, 0);
        }
        // i!=0 && j!=0
        int p1 = process(str1, i - 1, str2, j);
        int p2 = process(str1, i, str2, j - 1);
        int p4 = str1[i] == str2[j] ? process(str1, i - 1, str2, j - 1) + 1 : 0;
        return Math.max(p1, Math.max(p2, p4));
    }

    // dp[i][j]
    // str1 从0~i 跟 str2 都从 0~j的最长公共子序列长度
    // dp[i][j] = dp[i][j-1]
    public int longestCommonSubsequence2(String s1, String s2) {
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) {
            return 0;
        }
        int N = s1.length();
        int M = s2.length();
        int[][] dp = new int[N][M];
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
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

