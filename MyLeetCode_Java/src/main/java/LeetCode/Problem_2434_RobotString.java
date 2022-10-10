package LeetCode;

import java.util.ArrayDeque;

public class Problem_2434_RobotString {

    // 从i位置出发字典序最小的
    public String robotWithString(String s) {
        char[] str = s.toCharArray();
        int N = str.length;
        char[] dp = new char[N];
        dp[N - 1] = str[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            dp[i] = dp[i + 1];
            if (str[i] < dp[i + 1]) {
                dp[i] = str[i];
            }
        }
        ArrayDeque<Character> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < N) {
            // 当前处理i位置, 考察dp[i]
            // 当前栈顶 vs. str[i], 优先用栈顶
            while (!stack.isEmpty() && stack.peekLast() <= dp[i]) {
                sb.append(stack.pollLast());
            }
            if (str[i] == dp[i]) {
                sb.append(str[i++]);
                continue;
            }
            stack.addLast(str[i++]);
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pollLast());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "vzhofnpo"; // "fnohopzv"
        // String s = "ofnpo"; // "fnohopzv"
        var ans = new Problem_2434_RobotString().robotWithString(s);
        System.out.println(ans);
    }
}
