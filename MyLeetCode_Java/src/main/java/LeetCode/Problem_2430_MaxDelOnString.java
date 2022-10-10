package LeetCode;

public class Problem_2430_MaxDelOnString {


    // dp[i]: 从i位置到结束的最大操作次数
    // dp[i]: 从i位置开始枚举前缀到j i...j 跟后面 j+1...2*j-i 是否相等
    // TLE
    public int deleteString(String s) {
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N + 1];
        dp[N] = 0;
        for (int i = N - 1; i >= 0; i--) {
            int len = N - i;
            dp[i] = 1;
            for (int j = 1; j <= len / 2; j++) {
                if (s.substring(i, i + j).equals(s.substring(i + j, i + 2 * j))) {
                    dp[i] = Math.max(dp[i], 1 + dp[i + j]);
                }
            }
        }
        return dp[0];
    }

    /**
     * 枚举长度 len，如果字符串 s 的从 i 开始的连续的两段长度为 len 的字符串相等，
     * 那么我们需要删除前 len 个字符后继续删除，因此 dp[i]=max⁡(dp[i],dp[i+len]+1)
     */
    public int deleteString1(String s) {
        char[] str = s.toCharArray();
        int N = str.length;
        // lcp[i][j] 表示 s[i:] 和 s[j:] 的最长公共前缀的长度
        int[][] lcp = new int[N + 1][N + 1];
        for (int i = N - 1; i >= 0; i--) {
            // for (int j = N - 1; j > i; j--) {
            for (int j = N - 1; j >= 0; j--) {
                if (str[i] == str[j]) {
                    lcp[i][j] = lcp[i + 1][j + 1] + 1;
                } // 不相等是0
            }
        }
        int[] dp = new int[N + 1];
        dp[N] = 0;
        for (int i = N - 1; i >= 0; i--) {
            int len = N - i;
            dp[i] = 1;
            // for (int j = 1; j <= len / 2; j++) {
            for (int j = 1; i + 2 * j <= N; j++) {
                int maxLcp = lcp[i][i + j];
                if (maxLcp >= j) {
                    dp[i] = Math.max(dp[i], 1 + dp[i + j]);
                }
            }
        }
        return dp[0];
    }


    public static void main(String[] args) {
        String s = "abcabcdabc";
        var ans = new Problem_2430_MaxDelOnString().deleteString1(s);
        System.out.println(ans);
    }
}
