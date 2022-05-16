package LeetCode.MianshiJindian;

import java.util.ArrayDeque;

// IMP: 表达式计算基础题, 重要!!!

public class Problem_1626_BasicCalculator {

    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        ArrayDeque<String> stack = new ArrayDeque<>();
        int curNum = 0;
        for (char ch : str) {
            if (ch == ' ') continue;
            if (ch >= '0' && ch <= '9') {
                curNum = curNum * 10 + ch - '0';
            } else {
                addNum(stack, curNum);
                stack.addLast(String.valueOf(ch));
                curNum = 0;
            }
        }
        addNum(stack, curNum);
        return getNum(stack);
    }

    private int getNum(ArrayDeque<String> queue) {
        int res = 0;
        boolean preAddSigh = true;
        String cur = null;
        int num = 0;
        while (!queue.isEmpty()) {
            cur = queue.pollFirst();
            if (cur.equals("+")) {
                preAddSigh = true;
            } else if (cur.equals("-")) {
                preAddSigh = false;
            } else {
                num = Integer.parseInt(cur);
                res += preAddSigh ? num : -num;
            }
        }
        return res;
    }

    private void addNum(ArrayDeque<String> stack, int num) {
        if (!stack.isEmpty()) {
            String top = stack.pollLast();
            if (top.equals("+") || top.equals("-")) {
                stack.addLast(top);
            } else {
                int cur = Integer.parseInt(stack.pollLast());
                num = top.equals("*") ? cur * num : cur / num;
            }
        }
        stack.addLast(String.valueOf(num));
    }


    // https://leetcode-cn.com/problems/basic-calculator-ii/solution/ji-ben-ji-suan-qi-ii-by-leetcode-solutio-cm28/
    // 官方的解法
    public int calculate2(String s) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        char preSign = '+';
        int num = 0;
        int N = s.length();
        for (int i = 0; i < N; ++i) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            // 是运算符号
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == N - 1) {
                switch (preSign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    default:
                        stack.push(stack.pop() / num);
                }
                preSign = s.charAt(i);
                num = 0;
            }
        }
        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }


    public static void main(String[] args) {
        String s = "3 + 2 * 2";
        var ans = new Problem_1626_BasicCalculator().calculate(s);
        System.out.println(ans);
    }
}
