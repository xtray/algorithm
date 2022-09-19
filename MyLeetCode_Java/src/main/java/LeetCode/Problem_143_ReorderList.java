package LeetCode;

public class Problem_143_ReorderList {

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

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode fast = head;
        ListNode slow = head;
        // 奇数个返回中点, 偶数个位上中点
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode l1 = head;
        // 后半部分逆序
        ListNode l2 = reverse(slow.next);
        slow.next = null;
        ListNode cur = head;
        while (l1 != null || l2 != null) {
            if(l1 != null && l2 != null) {
                ListNode next1 = l1.next;
                ListNode next2 = l2.next;
                cur.next = l1;
                cur = cur.next;
                cur.next = l2;
                cur = cur.next;
                l1 = next1;
                l2 = next2;
            } else if(l1 != null) {
                ListNode next1 = l1.next;
                cur.next = l1;
                cur = cur.next;
                l1 = next1;
            } else {
                ListNode next2 = l2.next;
                cur.next = l2;
                cur = cur.next;
                l2 = next2;
            }
        }
    }

    private ListNode reverse(ListNode head) {
        if (head == null) {
            return head;
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

    public static void printListNode(ListNode head) {
        while (head!=null) {
            System.out.print(head.val + (head.next == null ? "":"->"));
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        // head.next = new ListNode(2);
        // head.next.next = new ListNode(3);
        // head.next.next.next = new ListNode(4);
        // head.next.next.next.next = new ListNode(5);
        new Problem_143_ReorderList().reorderList(head);
        printListNode(head);
    }
}
