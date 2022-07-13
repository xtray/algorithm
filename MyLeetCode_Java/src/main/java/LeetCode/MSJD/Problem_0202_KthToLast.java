package LeetCode.MSJD;

public class Problem_0202_KthToLast {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public int kthToLast(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;
        while (k>0) {
            fast = fast.next;
            k--;
        }
        while (fast!=null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow.val;
    }
}
