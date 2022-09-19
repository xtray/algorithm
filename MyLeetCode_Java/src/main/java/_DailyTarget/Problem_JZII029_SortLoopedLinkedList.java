package _DailyTarget;

public class Problem_JZII029_SortLoopedLinkedList {

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

    // https://leetcode.cn/problems/4ueAj6/solution/pai-xu-de-xun-huan-lian-biao-by-leetcode-f566/
    public Node insert(Node head, int val) {
        Node node = new Node(val);

        if (head == null) {
            node.next = node;
            return node;
        }

        if (head.next == head) { // 1个节点
            Node next = head.next;
            head.next = node;
            head.next.next = next;
            return head;
        }

        Node cur = head;
        while (cur.next != head) { // 不转一圈
            // 情况1: 正常的二者之间
            if (cur.val <= val && cur.next.val >= val) {
                break;
            }
            // 情况2: 首尾相接处, 极大, 或者极小
            if (cur.val > cur.next.val) {
                if (val > cur.val || val < cur.next.val) {
                    break;
                }
            }
            cur = cur.next;
        }
        // 情况3: 都是一个值
        Node next = cur.next;
        cur.next = node;
        node.next = next;
        return head;
    }

}
