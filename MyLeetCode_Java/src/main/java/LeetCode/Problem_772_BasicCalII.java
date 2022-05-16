package LeetCode;

import java.util.ArrayDeque;

// IMP: 表达式的计算套路, 非常重要!!
public class Problem_772_BasicCalII {

    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        return process(s.toCharArray(), 0)[0];
    }

    // 请从str[i...]往下算，遇到字符串终止位置或者右括号，就停止
    // 返回两个值:
    // 0) 当前过程负责的这一段的结果是多少
    // 1) 负责的这一段计算到了哪个位置
    private int[] process(char[] str, int i) {
        ArrayDeque<String> queue = new ArrayDeque<>();
        int cur = 0;
        int N = str.length;
        while (i < N && str[i] != ')') {
            if (str[i] >= '0' && str[i] <= '9') {
                cur = cur * 10 + str[i++] - '0';
            } else if (str[i] != '(') { // 遇到的是运算符号
                // 当前数字生成结束
                addNum(queue, cur);
                queue.addLast(String.valueOf(str[i++]));
                cur = 0;
            } else { // 遇到左括号, 调下一个递归过程
                int[] res = process(str, ++i);
                cur = res[0];
                i = res[1] + 1;
                // 下一个位置要么结束, 要么是个运算符号
            }
        }
        addNum(queue, cur); // 最后一个数字进栈
        // 返回当前过程的计算结果, 跟计算到的位置
        return new int[]{getNum(queue), i};
    }

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
            String cur = queue.poll();
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


    public static void main(String[] args) {
        // String s = "6-4/2";
        String s = "2*(5+5*2)/3+(6/2+8)";
        var ans = new Problem_772_BasicCalII().calculate(s);
        System.out.println(ans);
    }
}
