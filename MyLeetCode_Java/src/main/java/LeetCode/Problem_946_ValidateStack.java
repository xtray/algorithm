package LeetCode;

import java.util.ArrayDeque;

public class Problem_946_ValidateStack {

    // 模拟, 栈顶如果跟pop当前数字一致就弹出
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int popIdx = 0;
        for (int i = 0; i < pushed.length; i++) {
            stack.addLast(pushed[i]);
            while (!stack.isEmpty() && stack.peekLast() == popped[popIdx]) {
                stack.pollLast();
                popIdx++;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        int[] pushed = {1, 2, 3, 4, 5};
        int[] popped = {4, 5, 3, 2, 1};
        var ans = validateStackSequences(pushed, popped);
        System.out.println(ans);
    }
}
