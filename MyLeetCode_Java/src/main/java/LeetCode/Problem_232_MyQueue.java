package LeetCode;

import java.util.ArrayDeque;
import java.util.LinkedList;

public class Problem_232_MyQueue {

    class MyQueue1 {

        private LinkedList<Integer> stack1;
        private LinkedList<Integer> stack2;

        public MyQueue1() {
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

    class MyQueue {

        private  ArrayDeque<Integer> stack1;
        private  ArrayDeque<Integer> stack2;

        public MyQueue() {
            stack1 = new ArrayDeque<>();
            stack2 = new ArrayDeque<>();
        }

        public void push(int x) {
            stack1.push(x);
        }

        public int pop() {
            if(stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }

        public int peek() {
            if(stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.peek();
        }

        public boolean empty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }
    }
}
