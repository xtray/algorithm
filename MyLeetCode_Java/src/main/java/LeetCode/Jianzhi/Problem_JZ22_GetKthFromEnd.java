package LeetCode.Jianzhi;

public class Problem_JZ22_GetKthFromEnd {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;
        while (k > 0 && fast != null) {
            fast = fast.next;
            k--;
        }
        // 没有倒数第K个
        if(fast == null && k != 0) {
            return null;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
