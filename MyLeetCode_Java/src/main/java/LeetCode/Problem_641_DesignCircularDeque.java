package LeetCode;

public class Problem_641_DesignCircularDeque {



    class MyCircularDeque {

        public class Node {
            public int val;
            public Node pre;
            public Node next;

            public Node(int v) {
                val = v;
            }
        }

        private Node head;
        private Node tail;
        private int size;
        private int capacity;

        public MyCircularDeque(int k) {
            capacity = k;
        }

        public boolean insertFront(int value) {
            if (size == capacity) {
                return false;
            }
            Node node = new Node(value);
            if (head == null) {
                head = node;
                tail = node;
            } else {
                node.next = head;
                head.pre = node;
                head = node;
            }
            size++;
            return true;
        }

        public boolean insertLast(int value) {
            if (size == capacity) {
                return false;
            }
            Node node = new Node(value);
            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                node.pre = tail;
                tail = node;
            }
            size++;
            return true;
        }

        public boolean deleteFront() {
            if (size == 0) {
                return false;
            }
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                Node next = head.next;
                head.next = null;
                head = next;
                head.pre = null;
            }
            size--;
            return true;
        }

        public boolean deleteLast() {
            if (size == 0) {
                return false;
            }
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                Node pre = tail.pre;
                tail.pre = null;
                tail = pre;
                pre.next = null;
            }
            size--;
            return true;
        }

        public int getFront() {
            if (size == 0) {
                return -1;
            }
            return head.val;
        }

        public int getRear() {
            if (size == 0) {
                return -1;
            }
            return tail.val;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == capacity;
        }
    }
}
