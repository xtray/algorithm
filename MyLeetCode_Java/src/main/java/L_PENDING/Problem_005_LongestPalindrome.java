package L_PENDING;

/**
 * TODO: 动态规划的方法
 * TODO: 复习Manacher算法
 * TODO: LCS的回溯方法
 * ref: https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zui-chang-hui-wen-zi-chuan-by-leetcode-solution/
 */

public class Problem_005_LongestPalindrome {

    private int[][] dp;
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        // str 和 reverseStr 的最长公共子串
        String s1 = s;
        String s2 = new StringBuilder(s).reverse().toString();
        int N = s1.length();
        int M = s2.length();
        dp = new int[N][M];
        int len = LCA(s, s2);

        // 动态规划中的回溯
        // dp[N-1][M-1]
        // dp[i][j] = dp[i][j-1], dp[i-1][j], dp[i-1][j-1]+1
        StringBuilder sb = new StringBuilder();
        int i = N -1;
        int j = M -1;

        while (len > 0) {
            if(dp[i][j] == dp[i][j-1]) {
                sb.insert(0, s1.charAt(i));
                j--;
            } else if(dp[i][j] == dp[i-1][j]) {
                sb.insert(0,s1.charAt(j));
                i--;
            } else {
                sb.insert(0,s1.charAt(i));
                i--;
                j--;
            }
            len--;
        }
        return sb.toString();
    }

    public int LCA(String s1, String s2) {
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) {
            return 0;
        }
        int N = s1.length();
        int M = s2.length();
        dp = new int[N][M];
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


    private String manacher(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        // "12132" -> "#1#2#1#3#2#"
        char[] str = manacherString(s);
        // 回文半径的大小
        int[] pArr = new int[str.length];
        int C = -1;
        // 讲述中：R代表最右的扩成功的位置。coding：最右的扩成功位置的，再下一个位置
        int R = -1;
        int max = Integer.MIN_VALUE;
        int mid = -1;
        for (int i = 0; i < str.length; i++) {
            // R第一个违规的位置，i>= R
            // i位置扩出来的答案，i位置扩的区域，至少是多大。
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]])
                    pArr[i]++;
                else {
                    break;
                }
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            if(pArr[i] > max) {
                max = pArr[i];
                mid = i;
            }
        }
        mid = (mid - 1) / 2;
        max = max - 1;
        return s.substring((max & 1) == 0 ? mid - (max / 2) + 1 : mid - (max / 2), mid + (max / 2) + 1);
    }

    private char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    public String longestPalindrome2(String s) {
        if(s == null || s.length() ==0) {
            return "";
        }
        return manacher(s);
    }

    public static void main(String[] args) {
        String s = "4123215";
        var ans = new Problem_005_LongestPalindrome().longestPalindrome(s);
        System.out.println(ans);
    }


}
