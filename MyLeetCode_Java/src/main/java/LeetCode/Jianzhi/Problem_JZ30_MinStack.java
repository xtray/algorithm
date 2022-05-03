package LeetCode.Jianzhi;

import java.util.ArrayDeque;
import java.util.Deque;

public class Problem_JZ30_MinStack {

    /** initialize your data structure here. */
    private Deque<Integer> stack;
    private Deque<Integer> minStack;
    public Problem_JZ30_MinStack() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }

    public void push(int x) {
        stack.add(x);
        if(minStack.isEmpty()) {
            minStack.addLast(x);
        } else {
            minStack.addLast(Math.min(x, minStack.peekLast()));
        }
    }

    public void pop() {
        stack.pollLast();
        minStack.pollLast();
    }

    public int top() {
        return stack.peekLast();
    }

    public int min() {
        return minStack.peekLast();
    }

    public static void main(String[] args) {
        Problem_JZ30_MinStack sl = new Problem_JZ30_MinStack();
        sl.push(-2);
        sl.push(-0);
        sl.push(-1);
        System.out.println(sl.min());
        System.out.println(sl.top());
        sl.pop();
        System.out.println(sl.min());
    }
}
