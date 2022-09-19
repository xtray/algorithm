package _DailyTarget;

public class Problem_JZ22_LinkedListKthNodeReverse {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {

            val = x;
        }
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null) return head;
        // 快指针先向前走k-1步
        ListNode fast = head;
        int cur = k;
        while (fast != null && cur > 0) {
            fast = fast.next;
            cur--;
        }
        if (cur > 0) { // 节点不够多
            return head;
        }
        ListNode slow = head;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }


    public ListNode getKthFromEnd1(ListNode head, int k) {
        if (head == null) return head;
        head = reverse(head);
        k--;
        ListNode cur = head;
        while (cur != null && k > 0) {
            cur = cur.next;
            k--;
        }
        head = reverse(head);

        if (k > 0) {
            return head;
        }
        return cur;
    }

    private ListNode reverse(ListNode head) {
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

