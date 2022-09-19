package _DailyTarget;

import java.util.ArrayDeque;

public class Problem_155_MinStack {

    static class MinStack {

        private ArrayDeque<int[]> minStack;

        public MinStack() {
            minStack = new ArrayDeque<>();
        }

        public void push(int val) {
            int minVal = minStack.isEmpty() ? val : Math.min(minStack.peekLast()[1], val);
            minStack.addLast(new int[]{val, minVal});
        }

        public void pop() {
            minStack.pollLast();
        }

        public int top() {
            return minStack.peekLast()[0];
        }

        public int getMin() {
            return minStack.peekLast()[1];
        }
    }

    class MinStack1 {

        private ArrayDeque<Integer> stack;
        private ArrayDeque<Integer> minStack;

        public MinStack1() {
            stack = new ArrayDeque<>();
            minStack = new ArrayDeque<>();
        }

        public void push(int val) {
            stack.addLast(val);
            if (minStack.isEmpty()) {
                minStack.addLast(val);
            } else {
                minStack.addLast(Math.min(minStack.peekLast(), val));
            }
        }

        public void pop() {
            stack.pollLast();
            minStack.pollLast();
        }

        public int top() {
            return stack.peekLast();
        }

        public int getMin() {
            return minStack.peekLast();
        }
    }

    public static void main(String[] args) {
        MinStack sl = new MinStack();
        // sl.push(2);
        // sl.push(0);
        // sl.push(3);
        // sl.push(0);
        // sl.pop();
        // sl.pop();
        // sl.pop();
        // var ans = sl.getMin(); // 2
        // System.out.println(ans);

        // sl.push(Integer.MAX_VALUE - 1);
        // sl.push(Integer.MAX_VALUE - 1);
        // sl.push(Integer.MAX_VALUE);
        // sl.pop();
        // sl.pop();
        // sl.pop();
        sl.push(Integer.MAX_VALUE);
        sl.push(Integer.MIN_VALUE);
        sl.pop();
        var ans = sl.getMin();
        System.out.println(ans);
    }
}
