package LeetCode;

public class Problem_161_OneEdit {

    public boolean isOneEditDistance1(String s, String t) {
        return minDistance(s, t) == 1;
    }

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

    // IMP: 双指针的做法, 回看!!
    public boolean isOneEditDistance(String a, String b) {
        int N = a.length();
        int M = b.length();
        if (Math.abs(N - M) > 1) {
            return false;
        }
        // char[] str1 = N <= M ? a.toCharArray() : b.toCharArray();
        // char[] str2 = N <= M ? b.toCharArray() : a.toCharArray();
        // N = str1.length;
        // M = str2.length;
        // NOTE: 学习一下这种做法
        if (N > M) {
            return isOneEditDistance(b, a);
        }
        if (N == 0 && M == 1) {
            return true;
        }
        char[] str1 = a.toCharArray();
        char[] str2 = b.toCharArray();
        int i = 0;
        int j = 0;
        int cnt = 0;
        while (i < N && j < M && cnt <= 1) {
            if (str1[i] == str2[j]) {
                i++;
                j++;
            } else {
                // 不相等
                // 1. str1, str2长度相等时, 只能通过替换, i++, j++, cnt++
                // 2. str1, str2长度不等时, str1当前i位置插入str2j位置字符, j++, cnt++
                if (N == M) {
                    i++;
                }
                j++;
                cnt++;
            }
        }
        // 如果cnt == 0, 可能a,b相等, 或者a,b差一个字符, 要去掉相等的情况
        return cnt == 0 ? N != M : cnt == 1;
    }

    public static void main(String[] args) {
        String s1 = "a";
        String s2 = "ac";
        var ans = new Problem_161_OneEdit().isOneEditDistance(s1, s2);
        System.out.println(ans);
    }


}
