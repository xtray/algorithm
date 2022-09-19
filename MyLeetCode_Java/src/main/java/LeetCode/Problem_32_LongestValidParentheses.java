package LeetCode;

public class Problem_32_LongestValidParentheses {

    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = s.length();
        // 子串必须以i位置结尾的情况下, 往左最远能扩出去的有效区域长度
        int[] dp = new int[N];
        // dp[0] = 0
        int pre = 0;
        int ans = 0;
        for (int i = 1; i < N; i++) {
            if (str[i] == ')') {
                pre = i - 1 - dp[i - 1]; // 与str[i] 配对的 潜在左括号位置
                if (pre >= 0 && str[pre] == '(') { // 之前是左括号, 并且不越界
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            // 右括号 ) 才讨论, 左括号 ( 就是0, 不用特殊处理
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "(()";
        var ans = new Problem_32_LongestValidParentheses().longestValidParentheses(s);
        System.out.println(ans);
    }
}
