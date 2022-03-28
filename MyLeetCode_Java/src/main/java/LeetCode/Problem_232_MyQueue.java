package LeetCode;

import java.util.LinkedList;

public class Problem_232_MyQueue {

    class MyQueue {

        private LinkedList<Integer> stack1;
        private LinkedList<Integer> stack2;

        public MyQueue() {
            stack1 = new LinkedList<>();
            stack2 = new LinkedList<>();
        }

        public void push(int x) {
            stack1.addFirst(x);
        }

        public int pop() {
            if(stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.addFirst(stack1.pollFirst());
                }
            }
            return stack2.pollFirst();
        }

        public int peek() {
            if(stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.addFirst(stack1.pollFirst());
                }
            }
            return stack2.peekFirst();
        }

        public boolean empty() {
            return stack1.isEmpty()&&stack2.isEmpty();

        }
    }
}
