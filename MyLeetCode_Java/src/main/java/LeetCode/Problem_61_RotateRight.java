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

    // NOTE: 类似倒数第K个节点做头, 注意k大于链表长度的时候要取模
    public ListNode rotateRight1(ListNode head, int k) {
        if (head == null || k == 0) return head;
        // 1. 先确定链表长度
        ListNode tail = head;
        int len = 1;
        while (tail.next != null) {
            len++;
            tail = tail.next;
        }
        // 2. 取模
        int offset = k % len;
        if (offset == 0) {
            return head;
        }
        // 3. 找倒数第offset + 1个节点
        ListNode newTail = head;
        int step = len - offset - 1; // 需要跳的步数
        while (newTail.next != null && step > 0) {
            newTail = newTail.next;
            step--; // 因为取过模了, step最后一定为0, newTail一定来到真实尾部
        }
        ListNode newHead = newTail.next;
        newTail.next = null;
        tail.next = head;
        return newHead;
    }
}
