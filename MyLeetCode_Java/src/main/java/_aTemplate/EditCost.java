package _aTemplate;

/**
 * 给定两个字符串str1和str2，再给定三个整数ic、dc和rc，分别代表插入、删 除和替换一个字符的代价，
 * 返回将str1编辑成str2的最小代价。
 * 【举例】
 * str1="abc"，str2="adc"，ic=5，dc=3，rc=2
 * 从"abc"编辑成"adc"，把'b'替换成'd'是代价最小的，所以返回2
 * str1="abc"，str2="adc"，ic=5，dc=3，rc=100
 * 从"abc"编辑成"adc"，先删除'b'，然后插入'd'是代价最小的，所以返回8
 * str1="abc"，str2="abc"，ic=5，dc=3，rc=2 不用编辑了，本来就是一样的字符串，所以返回0
 */

public class EditCost {

    public static int minCost1(String s1, String s2, int ic, int dc, int rc) {
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
            dp[0][j] = j * ic;
        }
        // 第0列
        for (int i = 1; i < N; i++) {
            dp[i][0] = i * dc;
        }
        // 普遍位置
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                int p1 = Math.min(dp[i - 1][j] + dc, dp[i][j - 1] + ic);
                int p2 = str1[i - 1] == str2[j - 1] ? dp[i - 1][j - 1] : (dp[i - 1][j - 1] + rc);
                dp[i][j] = Math.min(p1, p2);
            }
        }
        return dp[N - 1][M - 1];
    }
}
