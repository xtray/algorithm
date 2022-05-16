package _BasicProblems;

import java.util.Stack;

public class ReverseStack {

    // 递归逆序一个栈
    public static void reverseStack(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = f(stack);
        reverseStack(stack);
        stack.push(i);
    }

    // 移除栈底的元素并返回
    // 上面的元素盖下来

    // NOTE: 相当于是用另外一个栈来辅助当前栈做逆序
    // 只是这个辅助栈是用系统的递归函数实现, 这个样就好理解多了
    // 递归函数过程中, 递归调用的返回值相当于在另一个栈空间的东西
    private static int f(Stack<Integer> stack) {
        int res = stack.pop();
        if(stack.isEmpty()) {
            return res;
        }
        int last = f(stack); // 相当于另外一栈存着这个last元素
        stack.push(res);
        return last;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        reverseStack(stack);
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }


}
