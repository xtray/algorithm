package LeetCode;

public class Problem_2370_LongestIdealSubsequence {

    public int longestIdealString(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N]; // dp[i]: 必须以i位置结尾的最长理想子序列长度
        int[] map = new int[26]; // map: 目前位置 a~z 的最长理想子序列长度

        dp[0] = 1;
        dp[1] = Math.abs(str[1] - str[0]) <= k ? 2 : 1;

        map[str[0] - 'a'] = 1;
        map[str[1] - 'a'] = dp[1];

        int ans = dp[1]; // 0,1的结果中最大的

        for (int i = 2; i < N; i++) {
            // 可能性: 从i位置往前找, 当前i位置字符左右差k的
            // 从 0~i-1里找, a~z里找
            int cur = 0;
            for (int j = 0; j < 26; j++) {
                if (Math.abs(str[i] - 'a' - j) <= k) {
                    cur = Math.max(cur, map[j]);
                }
            }
            dp[i] = Math.max(dp[i], cur + 1);
            // 更新 map i位置的结果
            map[str[i] - 'a'] = Math.max(map[str[i] - 'a'], dp[i]);
            ans = Math.max(ans, map[str[i] - 'a']);
        }
        return ans; // 需要找a~z里最长的
    }

    // TODO: 进一步优化
    // https://leetcode.cn/problems/longest-ideal-subsequence/solution/by-everythingcanbemodeled-jqzh/

    public static void main(String[] args) {
        String s = "a"; //1
        int k = 1;

        // String s = "az"; //1
        // int k = 2;

        // String s = "dll"; //2
        // int k = 0;

        // String s = "znrkjnk"; //6
        // int k = 8;

        // String s = "lkpkxcigcs"; //7
        // int k = 6;
        // String s = "pvjcci"; //2
        // int k = 4;
        // String s = "acfgbd"; //4
        // int k = 2;
        // String s = "abcd"; // 4
        // int k = 3;
        var ans = new Problem_2370_LongestIdealSubsequence().longestIdealString(s, k);
        System.out.println(ans);
    }
}
