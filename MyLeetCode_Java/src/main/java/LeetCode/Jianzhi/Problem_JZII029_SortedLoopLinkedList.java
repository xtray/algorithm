package LeetCode.Jianzhi;

public class Problem_JZII029_SortedLoopLinkedList {
    static class Node {
        public int val;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }

    // 插入的元素有多个相等的, 才会有多个插入位置
    public Node insert(Node head, int val) {
        Node node = new Node(val);
        if (head == null) {
            node.next = node;
            return node;
        }
        if (head.next == head) { // 1个节点
            head.next = node;
            node.next = head;
            return head;
        }

        Node cur = head;
        while (cur.next != head) {
            // 1. 相等值
            // 2. 单边相等, 单边不等
            // 2. 都不等
            if (val >= cur.val && val <= cur.next.val) {
                break;
            }
            // 3,3,5 插入 1
            if (cur.val > cur.next.val) { // 顺序的跃变点
                if (val > cur.val || val < cur.next.val) {
                    break;
                }
            }
            cur = cur.next;
        }
        Node next = cur.next;
        cur.next = node;
        node.next = next;
        return head;
    }

}
