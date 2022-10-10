package LeetCode;

public class Problem_856_ParenthesesScore {


    public int scoreOfParentheses(String s) {
        // [ 结果, 计算到的位置]
        return compute(s.toCharArray(), 0)[0] >> 1;
    }

    // s[i.....] 遇到 ')' 或者 终止位置  停！
    // 返回值：int[]  [ 分数结果, 计算到的位置]
    public static int[] compute(char[] s, int i) {
        if (s[i] == ')') {
            return new int[]{1, i};
        }
        int ans = 0;
        while (i < s.length && s[i] != ')') {
            int[] info = compute(s, i + 1);
            ans += 2 * info[0];
            i = info[1] + 1;
        }
        return new int[]{ans, i};
    }

    public static void main(String[] args) {
        // String s = "()()";
        String s = "(())()";
        var ans = new Problem_856_ParenthesesScore().scoreOfParentheses(s);
        System.out.println(ans);
    }
}
