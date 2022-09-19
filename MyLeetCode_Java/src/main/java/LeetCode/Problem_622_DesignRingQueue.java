package LeetCode;

public class Problem_622_DesignRingQueue {

    // IMP: 用size 解耦head, tail的行为
    static class MyCircularQueue {

        private int cap;
        private int size;
        private int[] data; // 数据存储区
        private int head; // 头部, 等待删除的位置
        private int tail; // 尾部, 等待放数据的位置

        public MyCircularQueue(int k) {
            cap = k;
            size = 0; // 初始大小
            data = new int[cap];
            head = 0;
            tail = 0;
        }

        private int getNextPos(int index) {
            return index == cap - 1 ? 0 : index + 1;
        }

        private int getPrevPos(int index) {
            return index == 0 ? cap - 1 : index - 1;
        }

        public boolean enQueue(int value) {
            if (size == cap) {
                return false;
            }
            size++;
            data[tail] = value;
            tail = getNextPos(tail);
            return true;
        }

        public boolean deQueue() {
            if (size == 0) return false;
            size--;
            head = getNextPos(head);
            // tail = getNextPos(tail);
            return true;
        }

        public int Front() {
            if (size == 0) return -1;
            return data[head];
        }

        public int Rear() {
            if (size == 0) return -1;
            int pos = getPrevPos(tail);
            return data[pos];
        }


        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == cap;
        }
    }

    public static void main(String[] args) {
        MyCircularQueue circularQueue = new MyCircularQueue(3);
        circularQueue.enQueue(1); // 返回 true
        circularQueue.enQueue(2); // 返回 true
        circularQueue.enQueue(3); // 返回 true
        circularQueue.enQueue(4); // 返回 false，队列已满
        circularQueue.Rear(); // 返回 3
        circularQueue.isFull(); // 返回 true
        circularQueue.deQueue(); // 返回 true
        circularQueue.enQueue(4); // 返回 true
        circularQueue.Rear(); // 返回 4
    }


}
