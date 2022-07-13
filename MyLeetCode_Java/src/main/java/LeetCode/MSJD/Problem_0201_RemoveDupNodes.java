package LeetCode.MSJD;

import java.util.HashSet;
import java.util.Set;

public class Problem_0201_RemoveDupNodes {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) {
            return null;
        }
        Set<Integer> set = new HashSet<>();
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            if (cur.val == pre.val || set.contains(cur.val)) {
                pre.next = next;
                cur = next;
            } else {
                set.add(cur.val);
                pre = cur;
                cur = next;
            }
        }
        return dummyHead.next;
    }

    public ListNode removeDuplicateNodes2(ListNode head) {
        if (head == null) {
            return null;
        }
        Set<Integer> set = new HashSet<>();
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode cur = head;
        ListNode pre = dummyHead;
        while (cur != null) {
            ListNode next = cur.next;
            if (set.contains(cur.val)) {
                pre.next = next; // pre还是pre, 不用变
                cur = next;
            } else {
                set.add(cur.val);
                pre = cur;
                cur = next;
            }
        }
        return dummyHead.next;
    }

    // NOTE: 学习一下
    // 第一重循环从链表的头节点开始，枚举一个保留的节点
    // 第二重循环从枚举的保留节点开始，到链表的末尾结束，将所有与保留节点相同的节点全部移除
    public ListNode removeDuplicateNodes3(ListNode head) {
        ListNode node = head;
        while (node != null) {
            ListNode cur = node;
            while (cur.next != null) {
                if (cur.next.val == node.val) {
                    cur.next = cur.next.next;
                } else {
                    cur = cur.next;
                }
            }
            node = node.next;
        }
        return head;
    }

}
