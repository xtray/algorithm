package LeetCode;

public class Problem_10_RegExpMatch {


    public boolean isMatch(String s, String p) {
        // NOTE: s, p的长度可以为0
        // 特例1: s: "", exp: ".*"
        // 特例2: s: "", exp: ""

        // if (s == null || s.length() == 0 || p == null || p.length() == 0) {
        //     return false;
        // }
        if (s == null || p == null) {
            return false;
        }
        char[] str = s.toCharArray();
        char[] exp = p.toCharArray();
        return isValid(str, exp) && process(str, exp, 0, 0);
    }

    // str从si位置出发 跟 exp 从 ei位置出发能不能匹配上
    // 当前考虑的是ei位置上的字符, 用ei+1的字符是不是*来做可能性划分
    private boolean process(char[] str, char[] exp, int si, int ei) {
        if (ei == exp.length) {
            return si == str.length; // ei表达式没了, si也要到结束才是true
        }
        // ei还有字符
        // 看ei+1位置的字符是不是*来做可能性划分
        // 可能性1: ei+1位置不是*
        if (ei + 1 == exp.length || exp[ei + 1] != '*') {
            return si != str.length && (str[si] == exp[ei] || exp[ei] == '.') && process(str, exp, si + 1, ei + 1);
        }
        // 可能性2: ei+1位置是*
        while (si != str.length && (str[si] == exp[ei] || exp[ei] == '.')) {
            if (process(str, exp, si, ei + 2)) {
                return true;
            }
            si++;
        }

        return process(str, exp, si, ei + 2); // si, ei不相等, ei+1的*让ei位置变0
    }

    private boolean isValid(char[] str, char[] exp) {
        for (char ch : str) {
            if (ch == '*' || ch == '.') {
                return false;
            }
        }
        for (int i = 0; i < exp.length; i++) {
            if (exp[i] == '*' && (i == 0 || exp[i - 1] == '*')) {
                return false;
            }
        }
        return true;
    }

    // 傻缓存
    public boolean isMatch2(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        char[] str = s.toCharArray();
        char[] exp = p.toCharArray();
        int[][] dp = new int[str.length + 1][exp.length + 1];
        return isValid(str, exp) && process2(str, exp, 0, 0, dp);
    }

    private boolean process2(char[] str, char[] exp, int si, int ei, int[][] dp) {
        if (dp[si][ei] != 0) {
            return dp[si][ei] == 1;
        }
        boolean ans = false;
        if (ei == exp.length) {
            ans = si == str.length;
            dp[si][ei] = ans ? 1 : -1;
            return ans; // ei表达式没了, si也要到结束才是true
        }
        // ei还有字符
        // 看ei+1位置的字符是不是*来做可能性划分
        // 可能性1: ei+1位置不是*
        if (ei + 1 == exp.length || exp[ei + 1] != '*') {
            ans = si != str.length && (str[si] == exp[ei] || exp[ei] == '.') && process2(str, exp, si + 1, ei + 1, dp);
            dp[si][ei] = ans ? 1 : -1;
            return ans;
        }
        // 可能性2: ei+1位置是*
        while (si != str.length && (str[si] == exp[ei] || exp[ei] == '.')) {
            ans = process2(str, exp, si, ei + 2, dp);
            if (ans) {
                dp[si][ei] = 1;
                return true;
            }
            si++;
        }
        ans = process2(str, exp, si, ei + 2, dp); // si, ei不相等, ei+1的*让ei位置变0
        dp[si][ei] = ans ? 1 : -1;
        return ans;
    }


    // 斜率优化
    public boolean isMatch3(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        char[] str = s.toCharArray();
        char[] exp = p.toCharArray();
        int[][] dp = new int[str.length + 1][exp.length + 1];
        return isValid(str, exp) && process3(str, exp, 0, 0, dp);
    }

    private boolean process3(char[] str, char[] exp, int si, int ei, int[][] dp) {
        if (dp[si][ei] != 0) {
            return dp[si][ei] == 1;
        }
        boolean ans = false;
        if (ei == exp.length) {
            ans = si == str.length;
            dp[si][ei] = ans ? 1 : -1;
            return ans; // ei表达式没了, si也要到结束才是true
        }
        // ei还有字符
        // 看ei+1位置的字符是不是*来做可能性划分
        // 可能性1: ei+1位置不是*
        if (ei + 1 == exp.length || exp[ei + 1] != '*') {
            ans = si != str.length && (str[si] == exp[ei] || exp[ei] == '.') && process3(str, exp, si + 1, ei + 1, dp);
            dp[si][ei] = ans ? 1 : -1;
            return ans;
        }
        // 可能性2: ei+1位置是*
        if (si == str.length) {
            ans = process3(str, exp, si, ei + 2, dp);
        } else { // si没到终止位置
            if (str[si] != exp[ei] && exp[ei] != '.') {
                ans = process3(str, exp, si, ei + 2, dp);
            } else { // si 跟 ei可以配上
                ans = process3(str, exp, si, ei + 2, dp) || process3(str, exp, si + 1, ei, dp);
            }
        }
        dp[si][ei] = ans ? 1 : -1;
        return ans;
    }

    // 斜率优化 + 动态规划
    public boolean isMatch4(String str, String pattern) {
        if (str == null || pattern == null) {
            return false;
        }
        char[] s = str.toCharArray();
        char[] p = pattern.toCharArray();
        int N = s.length;
        int M = p.length;
        boolean[][] dp = new boolean[N + 1][M + 1];
        dp[N][M] = true;
        for (int j = M - 1; j >= 0; j--) {
            dp[N][j] = (j + 1 < M && p[j + 1] == '*') && dp[N][j + 2];
        }
        // dp[0..N-2][M-1]都等于false，只有dp[N-1][M-1]需要讨论
        if (N > 0 && M > 0) {
            dp[N - 1][M - 1] = (s[N - 1] == p[M - 1] || p[M - 1] == '.');
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int j = M - 2; j >= 0; j--) {
                if (p[j + 1] != '*') {
                    dp[i][j] = ((s[i] == p[j]) || (p[j] == '.')) && dp[i + 1][j + 1];
                } else {
                    if ((s[i] == p[j] || p[j] == '.') && dp[i + 1][j]) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i][j + 2];
                    }
                }
            }
        }
        return dp[0][0];
    }

}
