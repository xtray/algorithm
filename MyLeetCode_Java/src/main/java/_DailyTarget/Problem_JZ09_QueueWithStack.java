package _DailyTarget;


import java.util.ArrayDeque;

public class Problem_JZ09_QueueWithStack {

    class CQueue {

        ArrayDeque<Integer> stack1;
        ArrayDeque<Integer> stack2;

        public CQueue() {
            stack1 = new ArrayDeque<>();
            stack2 = new ArrayDeque<>();
        }

        public void appendTail(int value) {
            stack1.addLast(value);
        }

        public int deleteHead() {
            if (stack1.isEmpty() && stack2.isEmpty()) return -1;
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.addLast(stack1.pollLast());
                }
            }
            return stack2.pollLast();
        }
    }
}
