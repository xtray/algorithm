package LeetCode;

import java.util.LinkedList;

public class Problem_682_CalPoints {

    public int calPoints(String[] ops) {
        if (ops == null || ops.length == 0) {
            return 0;
        }
        int N = ops.length;
        LinkedList<Integer> stack = new LinkedList<>();
        int score = 0;
        for (String op : ops) {
            switch (op) {
                case "C":
                    stack.pollFirst();
                    break;
                case "D":
                    score = stack.peekFirst();
                    score *= 2;
                    stack.addFirst(score);
                    break;
                case "+":
                    int top = stack.pollFirst();
                    score = top + stack.peekFirst();
                    stack.addFirst(top);
                    stack.addFirst(score);
                    break;
                default:
                    score = Integer.parseInt(op);
                    stack.addFirst(score);
            }
        }
        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pollFirst();
        }
        return ans;
    }

    // 数组做栈
    public int calPoints2(String[] ops) {
        if (ops == null || ops.length == 0) {
            return 0;
        }
        int N = ops.length;
        int[] stack = new int[N];
        int size = 0;
        for (String op : ops) {
            switch (op) {
                case "C":
                    size--;
                    break;
                case "D":
                    stack[size] = stack[size - 1] * 2;
                    size++;
                    break;
                case "+":
                    stack[size] = stack[size - 1] + stack[size - 2];
                    size++;
                    break;
                default:
                    stack[size++] = Integer.parseInt(op);
            }
        }
        int ans = 0;
        for (int i = 0; i < size; i++) {
            ans += stack[i];
        }
        return ans;
    }
}
