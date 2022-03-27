package LeetCode.Jianzhi;

import java.util.HashMap;
import java.util.Map;

public class Problem_JZ35_CopyRandomList {
    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    // 使用Map的做法
    public Node copyRandomList(Node head) {
        if(head == null) {
            return head;
        }
        Map<Node, Node> nodeMap = new HashMap<>();
        Node cur = head;
        while (cur!=null) {
            nodeMap.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        //连接指针
        cur = head;
        while (cur!=null) {
            Node copyNode = nodeMap.get(cur);
            copyNode.next = nodeMap.get(cur.next);
            copyNode.random = nodeMap.get(cur.random);
            cur = cur.next;
        }
        return nodeMap.get(head);
    }

    // IMP: 多看多练习几遍
    // 用当前节点的下一个保存复制节点
    public Node copyRandomList2(Node head) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        while (cur!=null) {
            Node dup = new Node(cur.val);
            Node next = cur.next;
            cur.next = dup;
            dup.next = next;
            cur = next;
        }
        cur = head;
        // 修复random指针
        while (cur!=null) {
            // 每次取出一对
            Node org = cur;
            Node dup = cur.next;
            Node next = dup.next;
            dup.random = org.random != null ? org.random.next : null;
            cur = next;
        }
        // 链表分离
        cur = head;
        Node newHead = head.next;
        while (cur!=null) {
            // 每次取出一对
            Node org = cur;
            Node dup = cur.next;
            Node next = dup.next;
            cur.next = next;
            dup.next = next != null? next.next : null;
            cur = next;
        }
        return newHead;
    }
}
