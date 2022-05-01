package L_INPRG;

import java.util.LinkedList;

// IMP: 表达式的计算套路, 非常重要!!
public class Problem_772_BasicCalII {

    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        return process(s.toCharArray(), 0)[0];
    }

    // 请从str[i...]往下算，遇到字符串终止位置或者右括号，就停止
    // 返回两个值，长度为2的数组
    // 0) 负责的这一段的结果是多少
    // 1) 负责的这一段计算到了哪个位置
    private int[] process(char[] str, int i) {
        LinkedList<String> queue = new LinkedList<>();
        int cur = 0;
        int[] bracket = null;
        // 从i出发开始撸串
        while (i < str.length && str[i] != ')') {
            if (str[i] >= '0' && str[i] <= '9') {
                cur = cur * 10 + str[i++] - '0';
            } else if (str[i] != '(') { // 遇到的是运算符号
                addNum(queue, cur);
                queue.addLast(String.valueOf(str[i++]));
                cur = 0;
            } else { // 遇到左括号了
                bracket = process(str, i + 1);
                cur = bracket[0];
                i = bracket[1] + 1;
            }
        }
        addNum(queue, cur);
        return new int[]{getNum(queue), i};
    }

    private int getNum(LinkedList<String> queue) {
        int res = 0;
        boolean add = true;
        String cur = null;
        int num = 0;
        while (!queue.isEmpty()) {
            cur = queue.pollFirst();
            if (cur.equals("+")) {
                add = true;
            } else if (cur.equals("-")) {
                add = false;
            } else {
                num = Integer.parseInt(cur);
                res += add ? num : -num;
            }
        }
        return res;
    }

    // 添加cur 到 queue, 如果顶部运算符是 * / 则计算
    private void addNum(LinkedList<String> queue, int num) {
        if (!queue.isEmpty()) {
            int cur = 0;
            String top = queue.pollLast();
            if (top.equals("+") || top.equals("-")) {
                queue.addLast(top);
            } else { // * /
                cur = Integer.valueOf(queue.pollLast());
                num = top.equals("*") ? cur * num : cur / num;
            }
        }
        queue.addLast(String.valueOf(num));
    }

    public static void main(String[] args) {
        String s = "1+1";
        var ans = new Problem_772_BasicCalII().calculate(s);
        System.out.println(ans);
    }
}
