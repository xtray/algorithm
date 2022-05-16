package LeetCode.MianshiJindian;

public class Problem_0206_PalindromeLinkedList {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }

        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode newHead = reverseLinkedList(slow);
        while (head!=null && newHead!=null) {
            if(head.val == newHead.val) {
                head = head.next;
                newHead = newHead.next;
            } else {
                return false;
            }
        }
        return true;
    }

    public static ListNode reverseLinkedList(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;

            pre = head;
            head = next;
        }
        return pre;
    }
}
