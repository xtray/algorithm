package LeetCode;

import java.util.LinkedList;

public class Problem_150_EvalRPN {

    // Note: 遇到数字压栈, 遇到运算符弹出两个数计算, 算完再压栈回去
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        LinkedList<Integer> stack = new LinkedList<>();
        for (String str : tokens) {
            if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
                compute(stack, str);
            } else {
                stack.addFirst(Integer.valueOf(str));
            }
        }
        return stack.peekFirst();
    }

    private void compute(LinkedList<Integer> stack, String str) {
        int num2 = stack.pollFirst(); // NOTE: 注意弹出两个数的顺序
        int num1 = stack.pollFirst();
        int ans = 0;
        switch (str) {
            case "+":
                ans = num1 + num2;
                break;
            case "-":
                ans = num1 - num2;
                break;
            case "*":
                ans = num1 * num2;
                break;
            case "/":
                ans = num1 / num2;
                break;
        }
        stack.addFirst(ans);
    }

    public static void main(String[] args) {
        String[] expr = {"4","13","5","/","+"};
        var ans = new Problem_150_EvalRPN().evalRPN(expr);
        System.out.println(ans);
    }
}
