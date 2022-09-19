package _DailyTarget;

public class Problem_JZ22_LinkedListKthNodeReverse_02 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {

            val = x;
        }
    }

    // NOTE: fast要真实的从head迈出K步
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode fast = head;
        while (k != 0 && fast != null) {
            k--;
            fast = fast.next;
        }
        if (k > 0) { // NOTE: 不够K个节点, 用k > 0, fast == null判断不准确, 比如一个节点, k==1的情况
            return null;
        }
        ListNode slow = head;
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    // 链表逆序的方法
    public ListNode getKthFromEnd1(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        head = reverse(head);
        ListNode cur = head;
        k--; // 从1开始, 先-1
        while (k > 0 && cur != null) {
            k--;
            cur = cur.next;
        }
        head = reverse(head);
        if (k > 0) { // 不够k个节点
            return head;
        }
        return cur;
    }

    private ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
        }
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

