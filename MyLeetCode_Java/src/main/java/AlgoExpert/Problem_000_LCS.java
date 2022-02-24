package AlgoExpert;

import java.util.ArrayList;
import java.util.List;

/**
 * 最长公共子序列的回溯
 * 返回最长公共子序列字符数组
 */

public class Problem_000_LCS {

    public static List<Character> longestCommonSubsequence(String s1, String s2) {

        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return new ArrayList<>();
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
                int p1 = dp[i - 1][j];
                int p2 = dp[i][j - 1];
                int p3 = str1[i] == str2[j] ? (1 + dp[i - 1][j - 1]) : 0;
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }
        // 通过 dp[i][j] 最右下角的位置开始回溯
        List<Character> ans = new ArrayList<>();
        // 长度 dp[N-1][M-1]
        // dp[i][j]
        // 1. dp[i-1][j]
        // 2. dp[i][j-1]
        // 3. [i]==[j], dp[i-1][j-1]+1
        int one = N - 1;
        int two = M - 1;
        while (one != 0 || two != 0) {
            if (one >= 1 && dp[one][two] == dp[one - 1][two]) {
                one--;
                continue;
            }
            if (two >= 1 && dp[one][two] == dp[one][two - 1]) {
                two--;
                continue;
            }
            if (s1.charAt(one) == s2.charAt(two)) {
                ans.add(0, s1.charAt(one));
                if (one == 0 || two == 0) {
                    break;
                }
                // one, two 必然都>=1
                if (dp[one][two] == dp[one - 1][two - 1] + 1) {
                    one--;
                    two--;
                }
            }
        }
        if (one == 0 && two == 0) {
            if (s1.charAt(one) == s2.charAt(two)) {
                ans.add(0, s1.charAt(0));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // String str1 = "ZXVVYZW"; // X Y Z W
        // String str2 = "XKYKZPW"; // X Y Z W
        // String str1 = "a12b";
        // String str2 = "cd12";
        String str1 = "ABCDEFG";
        String str2 = "ABCDEFG";
        var ans = longestCommonSubsequence(str1, str2);
        for (char ch : ans) {
            System.out.print(ch + " ");
        }
        System.out.println();

    }
}
