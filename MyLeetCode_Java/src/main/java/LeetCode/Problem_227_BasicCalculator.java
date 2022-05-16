package LeetCode;

import java.util.ArrayDeque;

// IMP: 表达式计算基础题, 重要!!!

public class Problem_227_BasicCalculator {

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
            } else { // 运算符号
                addNum(stack, curNum); // 结算之前收集的数字
                stack.addLast(String.valueOf(ch));
                curNum = 0;
            }
        }
        addNum(stack, curNum);
        return getNum(stack);
    }

    // 按队列处理:
    // 只有+ - 运算符的计算
    // 需要有两个数字, 当遇到第二个数字时, 看前一个运算符决定是加是减
    // 如果是一个负数, 预置前一个数字为0
    private int getNum(ArrayDeque<String> queue) {
        if (queue.isEmpty()) {
            return 0;
        }
        int ans = 0;
        boolean add = true;
        while (!queue.isEmpty()) {
            String cur = queue.pollFirst();
            if (cur.equals("+")) {
                add = true;
            } else if (cur.equals("-")) {
                add = false;
            } else {
                int curNum = Integer.parseInt(cur);
                ans = add ? ans + curNum : ans - curNum;
            }
        }
        return ans;
    }

    // 按栈处理:
    // 把当前数字加入堆栈, 如果栈顶是*/-就计算再压入
    private void addNum(ArrayDeque<String> queue, int num) {
        if(!queue.isEmpty()) {
            String top = queue.pollLast();
            if (top.equals("+") || top.equals("-")) {
                queue.add(top);
            } else {
                int firstNum = Integer.parseInt(queue.pollLast());
                num = top.equals("*") ? firstNum * num : firstNum / num;
            }
        }
        queue.addLast(String.valueOf(num));
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
        var ans = new Problem_227_BasicCalculator().calculate(s);
        System.out.println(ans);
    }
}
