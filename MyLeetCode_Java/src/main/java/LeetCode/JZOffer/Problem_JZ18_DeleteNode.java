package LeetCode.JZOffer;

public class Problem_JZ18_DeleteNode {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteNode(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        ListNode node = head;
        while (node != null) {
            ListNode next = node.next;
            if (node.val == val) {
                pre.next = next;
                return head;
            }
            pre = node;
            node = next;
        }
        return dummyHead.next;
    }


    public ListNode deleteNode2(ListNode head, int val) {
        if(head == null) {
            return head;
        }
        // 删除头结点
        if(head.val == val) {
            return head.next;
        }
        ListNode pre = head;
        ListNode node = head;
        while (node != null) {
            ListNode next = node.next;
            if(node.val == val) {
                pre.next = next;
                return head;
            }
            pre = node;
            node = next;
        }
        return head;
    }
}
