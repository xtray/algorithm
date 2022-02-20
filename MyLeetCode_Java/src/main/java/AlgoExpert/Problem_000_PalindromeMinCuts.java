package AlgoExpert;

/**
 * Given a non-empty string, write a function that returns the minimum number of cuts
 * needed to perform on the string such that each remaining substring is a palindrome.
 */

public class Problem_000_PalindromeMinCuts {

    public static int palindromePartitioningMinCuts(String s) {
        if (s == null || s.length() <=1) {
            return 0;
        }

        char[] str = s.toCharArray();
        int N = str.length;
        boolean[][] isP = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            isP[i][i] = true;
        }
        for (int i = 0; i < N - 1; i++) {
            isP[i][i + 1] = str[i] == str[i + 1];
        }
        for (int row = N - 3; row >= 0; row--) {
            for (int col = row + 2; col < N; col++) {
                isP[row][col] = str[row] == str[col] && isP[row + 1][col - 1];
            }
        }
        int[] dp = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[N] = 0;
        for (int i = N - 1; i >= 0; i--) {
            for (int end = i; end < N; end++) {
                // i..end
                if (isP[i][end]) {
                    dp[i] = Math.min(dp[i], 1 + dp[end + 1]);
                }
            }
        }
        return dp[0] -1;
    }

    public static void main(String[] args) {
        String str = "a";
        var ans = palindromePartitioningMinCuts(str);
        System.out.println(ans);
    }
}
