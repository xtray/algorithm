package _DailyTarget;

import java.util.HashMap;
import java.util.Map;

public class Problem_JZ35_CopyRandomList_2 {

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
        // 链接指针
        cur = head;
        Node dup = map.get(head);
        while (cur != null) {
            dup.next = map.get(cur.next);
            dup.random = map.get(cur.random);
            dup = dup.next;
            cur = cur.next;
        }
        return map.get(head);
    }

    // 方法2: 新复制的节点混入老节点下一个
    public Node copyRandomList2(Node head) {
        if (head == null) return head;
        // 1. 复制链表接在老链表每个Node后面
        Node cur = head;
        while (cur != null) {
            Node next = cur.next; // 老next
            Node newNode = new Node(cur.val);
            cur.next = newNode;
            newNode.next = next;
            cur = next;
        }
        // 2. 链接random指针
        cur = head;
        while (cur != null) {
            // 一次取出两个节点
            // cur, cur.next
            Node newNode = cur.next;
            Node next = newNode.next; // 下一批
            // NOTE: cur.random可能为空
            newNode.random = cur.random == null ? null : cur.random.next;
            cur = next;
        }
        // 3. 链表分离
        cur = head;
        Node newHead = cur.next;
        while (cur != null) {
            // 一次取出两个节点
            // cur, cur.next
            Node newNode = cur.next;
            Node next = newNode.next; // 下一批
            // NOTE: next可能为空
            newNode.next = next == null ? null : next.next;
            cur.next = next;
            cur = next;
        }
        return newHead;
    }
}
