package LeetCode;

/**
 * IMP: 重要基础题!!
 */
public class Problem_203_RemoveElements {

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

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        // 先删除头
        while (head != null && head.val == val) {
            head = head.next;
        }
        ListNode pre = head;
        ListNode cur = head != null ? head.next : null;
        while (cur != null) {
            ListNode next = cur.next;
            if (cur.val == val) {
                pre.next = next;
                cur = next;
            } else {
                pre = cur;
                cur = next;
            }

        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next = new ListNode(6);
        int val = 6;
        var ans = new Problem_203_RemoveElements().removeElements(head, val);
        while (ans != null) {
            System.out.print(ans.val + "-->");
            ans = ans.next;
        }
        System.out.println("null");
    }
}
