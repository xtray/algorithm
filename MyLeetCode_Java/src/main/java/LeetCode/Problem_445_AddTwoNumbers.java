package LeetCode;

import java.util.List;

public class Problem_445_AddTwoNumbers {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;
        l1 = reverse(l1);
        l2 = reverse(l2);
        int carray = 0;
        while (l1 != null || l2 != null || carray != 0) {
            int val = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carray;
            carray = val / 10;
            cur.next = new ListNode(val % 10);
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return reverse(dummyHead.next);
    }

    private ListNode reverse(ListNode head) {
        if (head == null) return head;
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
