package _DailyTarget;

public class Problem_JZ24_ReverseLinkedList {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {

            val = x;
        }
    }

    private ListNode reverseList(ListNode head) {
        if (head == null) return head;
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}

