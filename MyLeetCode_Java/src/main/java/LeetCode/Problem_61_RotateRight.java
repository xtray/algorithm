package LeetCode;

public class Problem_61_RotateRight {

    public class ListNode {
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

    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || k == 0) return head;

        ListNode tail = head;
        int listLen = 1;
        while (tail.next!=null) {
            listLen++;
            tail = tail.next;
        }
        int offset = k%listLen;
        if (offset == 0) {
            return head;
        }
        int tailPos = listLen - offset;
        ListNode newTail = head;
        for(int i = 1; i < tailPos;i++) {
            newTail = newTail.next;
        }
        ListNode newHead = newTail.next;
        newTail.next = null;
        tail.next = head;
        return newHead;
    }
}
