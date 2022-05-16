package LeetCode;


// ref: EditCost

public class Problem_72_MinDistance {

    public int minDistance(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length + 1;
        int M = str2.length + 1;
        // dp[i][j]: str1取前i个字符, 编辑成跟str2取前j个字符的最小代价
        int[][] dp = new int[N][M];
        // dp[0][0] = 0
        // 第0行
        for (int j = 1; j < M; j++) {
            dp[0][j] = j;
        }
        // 第0列
        for (int i = 1; i < N; i++) {
            dp[i][0] = i;
        }
        // 普遍位置
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                int p1 = Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1);
                int p2 = str1[i - 1] == str2[j - 1] ? dp[i - 1][j - 1] : (dp[i - 1][j - 1] + 1);
                dp[i][j] = Math.min(p1, p2);
            }
        }
        return dp[N - 1][M - 1];
    }

}