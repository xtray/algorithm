package _LintCode;

public class Problem_372_DeleteNode {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public void deleteNode(ListNode node) {
        if(node == null) {
            return;
        }
        ListNode next = node.next;

        node.val = next.val;
        node.next = next.next;
    }
}
