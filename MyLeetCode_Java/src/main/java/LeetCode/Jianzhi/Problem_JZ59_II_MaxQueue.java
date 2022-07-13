package LeetCode.Jianzhi;

import java.util.ArrayDeque;

public class Problem_JZ59_II_MaxQueue {

    static class MaxQueue {

        private ArrayDeque<Integer> queue;
        private ArrayDeque<Integer> maxQueue;

        public MaxQueue() {
            queue = new ArrayDeque<>();
            maxQueue = new ArrayDeque<>();
        }

        public int max_value() {
            if(maxQueue.isEmpty()) {
                return -1;
            }
            return maxQueue.peekFirst();
        }

        public void push_back(int value) {
            if(!queue.isEmpty()) {
                ArrayDeque<Integer> tmp = new ArrayDeque<>();
                while (!maxQueue.isEmpty()) {
                    tmp.addLast(Math.max(maxQueue.pollFirst(), value));
                }
                maxQueue = tmp;
            }
            maxQueue.addLast(value);
            queue.addLast(value);
        }

        public int pop_front() {
            if(queue.isEmpty()) {
                return -1;
            }
            maxQueue.pollFirst();
            return queue.pollFirst();
        }
    }

    public static void main(String[] args) {
        MaxQueue maxQueue = new MaxQueue();
        maxQueue.push_back(1);
        maxQueue.push_back(2);
        System.out.println(maxQueue.max_value());
        System.out.println(maxQueue.pop_front());
        System.out.println(maxQueue.max_value());
    }
}
