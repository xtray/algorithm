package BigComQues;

import java.util.Stack;

/**
 * PENDING
 */

public class Problem_000_ReverseStack {

    public static void reverseStack(Stack<Integer> stack) {

    }

    // 删除堆栈底部的数并返回
    private static int getBottom(Stack<Integer> stack) {
       int res = stack.pop();
       if(stack.isEmpty()) {
           return res;
       } else {
           int last = getBottom(stack);
           stack.push(res);
           return last;
       }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);


        while (!stack.isEmpty()) {
            int bottom = getBottom(stack);
            System.out.println(bottom);
        }
    }
}
