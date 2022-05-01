package LeetCode;

import java.util.LinkedList;

public class Problem_155_MinStack {

    class MinStack {

        LinkedList<Integer> stack;
        LinkedList<Integer> minStack;

        public MinStack() {
            stack = new LinkedList<>();
            minStack = new LinkedList<>();
        }

        public void push(int val) {
            stack.addFirst(val);
            int min = minStack.isEmpty()? val : Math.min(minStack.peekFirst(), val);
            minStack.addFirst(min);
        }

        public void pop() {
            stack.pollFirst();
            minStack.pollFirst();
        }

        public int top() {
            return stack.peekFirst();
        }

        public int getMin() {
            return minStack.peekFirst();
        }
    }
}
