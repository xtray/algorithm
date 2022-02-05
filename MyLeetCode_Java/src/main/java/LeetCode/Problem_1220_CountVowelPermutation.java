package LeetCode;

public class Problem_1220_CountVowelPermutation {


    // dp[i][j]
    // 长度为 i, 以字母 j 结尾的字符串个数
    // j 可以为,a,e,i,o,u
    // dp[i][j] = dp[i][a,e,i,o,u]
    // dp[i][a] = dp[i-1][u,i,e]
    // dp[i][e] = dp[i-1][a,i]
    // dp[i][i] = dp[i-1][o,e]
    // dp[i][o] = dp[i-1][i]
    // dp[i][u] = dp[i-1][o,i]
    public int countVowelPermutation(int n) {
        int mod = (int) 1e9 + 7;
        int[][] dp = new int[n + 1][5];
        // a,e,i,o,u
        // 0,1,2,3,4
        for (int i = 0; i < 5; i++) {
            dp[1][i] = 1;
        }
        for (int i = 2; i <= n; i++) {
            dp[i][0] = ((dp[i - 1][4] + dp[i - 1][2]) % mod + dp[i - 1][1]) % mod;
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % mod;
            dp[i][2] = (dp[i - 1][1] + dp[i - 1][3]) % mod;
            dp[i][3] = dp[i - 1][2];
            dp[i][4] = (dp[i - 1][2] + dp[i - 1][3]) % mod;
        }
        int ans = 0;
        for (int i = 0; i < 5; i++) {
            ans = (ans + dp[n][i]) % mod;
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 144;
        var ans = new Problem_1220_CountVowelPermutation().countVowelPermutation(n);
        System.out.println(ans);
    }
}
