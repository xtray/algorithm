package LeetCode;

import java.util.List;

/**
 * IMP: 重要基础题!!
 */
public class Problem_83_DeleteDuplicates {

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

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode pre = null;
        ListNode node = head;
        while (node != null) {
            ListNode next = node.next;
            if (pre != null && node.val == pre.val) {
                pre.next = next;
                node = next;
            } else {
                pre = node;
                node = node.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(3);
        var ans = new Problem_83_DeleteDuplicates().deleteDuplicates(head);
        while (ans != null) {
            System.out.print(ans.val + "-->");
            ans = ans.next;
        }
        System.out.println("null");
    }
}
