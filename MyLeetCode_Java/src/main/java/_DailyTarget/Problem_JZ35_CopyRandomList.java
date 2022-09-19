package _DailyTarget;

import java.util.HashMap;
import java.util.Map;

public class Problem_JZ35_CopyRandomList {

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    // 方法1: 复制链表放在Map value里
    public Node copyRandomList(Node head) {
        if (head == null) return head;
        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            Node dup = map.get(cur);
            dup.next = map.get(cur.next);
            dup.random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(cur);
    }

    // 方法2: 新复制的节点混入老节点下一个
    public Node copyRandomList2(Node head) {
        if (head == null) return head;
        Node cur = head;
        while (cur != null) {
            Node newNode = new Node(cur.val);
            Node next = cur.next;
            cur.next = newNode;
            newNode.next = next;
            cur = next;
        }

        // 链接random指针
        cur = head;
        while (cur != null) {
            // 一次取出一对, cur, cur.next
            Node dup = cur.next;
            dup.random = cur.random != null ? cur.random.next : null;
            cur = dup.next;
        }

        // 分离
        cur = head;
        Node newHead = cur.next;
        while (cur != null) {
            // 一次取出一对, cur, cur.next
            Node org = cur;
            Node dup = cur.next;
            Node next = dup.next; // cur的下一个节点

            cur.next = next;
            dup.next = next != null ? next.next : null;

            cur = next;
        }
        return newHead;
    }
}
