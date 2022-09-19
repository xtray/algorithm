package _Exercise;

import java.util.ArrayDeque;

public class Problem_224_Calculator {

    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        return process(s.toCharArray(), 0)[0];
    }

    // 从i位置开始计算
    private int[] process(char[] str, int i) {
        ArrayDeque<String> queue = new ArrayDeque<>();
        int cur = 0;
        // 遇到结束位置, 或者左括号停止
        while (i < str.length && str[i] != ')') {
            if (str[i] == ' ') {
                i++;
                continue;
            }
            if (str[i] >= '0' && str[i] <= '9') {
                cur = cur * 10 + str[i++] - '0';
            } else if (str[i] != '(') { // 运算符号
                addNum(queue, cur);
                queue.addLast(String.valueOf(str[i++]));
                cur = 0;
            } else { // 遇到左括号, 调递归
                int[] res = process(str, i + 1);
                cur = res[0];
                i = res[1] + 1;
            }
        }
        addNum(queue, cur);
        return new int[]{getNum(queue), i};

    }

    private int getNum(ArrayDeque<String> queue) {
        int res = 0;
        boolean add = true;
        while (!queue.isEmpty()) {
            String cur = queue.pollFirst();
            if (cur.equals("+")) {
                add = true;
            } else if (cur.equals("-")) {
                add = false;
            } else {
                int num = Integer.parseInt(cur);
                res += add ? num : -num;
            }
        }
        return res;
    }

    // 将num加入queue, 如果栈顶是 */ 就计算后在压入
    private void addNum(ArrayDeque<String> queue, int num) {
        if (!queue.isEmpty() && (queue.peek().equals("*") || queue.peek().equals("/"))) {
            String op = queue.pollLast();
            String top = queue.pollLast();
            int cur = Integer.parseInt(top);
            num = op.equals("*") ? cur * num : cur / num;
        }
        queue.addLast(String.valueOf(num));
    }

    public static void main(String[] args) {
        String s = "1+1";
        var ans = new Problem_224_Calculator().calculate(s);
        System.out.println(ans);

    }
}
