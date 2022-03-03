package Company;

public class Problem_000_ReverseLinkList {

    /**
     * 逆序倒数第 K 个单链表
     * 一个单向链表，从倒数第n位结点开始翻转后面的链表，翻转后接入到前面，最后返回链表。
     * 如list:1->2->3->4->5,n:3.那么返回1->2->5->4->3.
     */

    public static class ListNode {
        public int value;
        public ListNode next;

        public ListNode(int v) {
            value = v;
            next = null;
        }
    }

    public static ListNode reverseKSteps(ListNode head, int k) {
        if (head == null || head.next == null || k <= 0) {
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        int cnt = 1; // 需要少走一个, 定位到前一个位置
        while (cnt <= k && fast != null) {
            fast = fast.next;
            cnt++;
        }
        if (cnt <= k) { // 链表长度不够 k, fast == null 了
            return head;
        }
        // cnt > k
        if(fast == null) {
            return reverse(head);
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // fast.next == null 的时候, slow 位于倒数第 k + 1 个
        ListNode pre = slow;
        slow = slow.next;
        pre.next = reverse(slow);
        return head;
    }

    // 单链表翻转
    private static ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode node = head;
        while (node != null) {
            ListNode next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        int k = 3;
        head = reverseKSteps(head, k);
        while (head != null) {
            System.out.print(head.value + "-->");
            head = head.next;
        }
        System.out.println("null");
    }


}
