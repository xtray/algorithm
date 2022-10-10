package LeetCode;

public class Problem_707_DesignLinkedList {

    static class MyLinkedList {

        private class Node {
            public Node pre;
            public Node next;
            public int val;

            public Node(int v) {
                val = v;
            }
        }

        private Node head;
        private Node tail;
        private int size;

        public MyLinkedList() {

        }

        private Node getNodeByIndex(int index) {
            Node node = head;
            int cnt = 0;
            while (node != null) {
                if (index == cnt) {
                    break;
                }
                cnt++;
                node = node.next;
            }
            return node;
        }

        public int get(int index) {
            if (index < 0 || index >= size) return -1;
            Node node = getNodeByIndex(index);
            return node == null ? -1 : node.val;
        }


        public void addAtHead(int val) {
            Node node = new Node(val);
            size++;
            if (head == null) {
                head = node;
                tail = node;
                return;
            }
            node.next = head;
            head.pre = node;
            head = node;
        }

        public void addAtTail(int val) {
            size++;
            Node node = new Node(val);
            if (head == null) {
                head = node;
                tail = node;
                return;
            }
            tail.next = node;
            node.pre = tail;
            tail = node;
        }

        public void addAtIndex(int index, int val) {
            if (index > size) return;
            if (index <= 0) {
                addAtHead(val);
                return;
            }
            if (index == size) {
                addAtTail(val);
                return;
            }
            size++;
            // 在中间插入
            Node insertNode = new Node(val);
            Node node = getNodeByIndex(index); // node一定不是null
            // 插在node之前
            Node pre = node.pre;
            pre.next = insertNode;
            insertNode.pre = pre;
            insertNode.next = node;
            node.pre = insertNode;
        }

        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size) return;
            if (index == 0) { // 删除头部
                removeHead();
                return;
            }
            if (index == size - 1) {
                removeTail();
                return;
            }
            size--;
            // 删除中间
            Node node = getNodeByIndex(index); // node一定不是null
            Node pre = node.pre;
            Node next = node.next;
            node.pre = null;
            node.next = null;
            pre.next = next;
            next.pre = pre;
        }

        private void removeTail() {
            if (head == tail) {
                head = null;
                tail = null;
                size = 0;
                return;
            }
            size--;
            Node pre = tail.pre;
            pre.next = null;
            tail.pre = null;
            tail = pre;
        }

        private void removeHead() {
            if (head == tail) {
                head = null;
                tail = null;
                size = 0;
                return;
            }
            size--;
            Node next = head.next;
            next.pre = null;
            head.next = null;
            head = next;
        }
    }

    public static void main(String[] args) {
        MyLinkedList sl = new MyLinkedList();
        sl.addAtHead(0);
        sl.addAtIndex(1, 4);
        sl.addAtTail(8);
        sl.addAtHead(5);
        sl.addAtIndex(4, 3);
        sl.addAtTail(0);
        sl.addAtTail(5);
        sl.addAtIndex(6, 3);
        sl.deleteAtIndex(7);
        sl.deleteAtIndex(5);
        sl.addAtTail(4);
        System.out.println();
    }
}
