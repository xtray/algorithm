package LeetCode;

public class Problem_1290_GetDecimalValue {

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

    public int getDecimalValue(ListNode head) {
        if (head == null) {
            return 0;
        }
        ListNode newHead = reverse(head);
        ListNode node = newHead;
        int ans = 0;
        int base = 1;
        while (node != null) {
            if (node.val == 1) {
                ans += base;
            }
            base <<= 1;
            node = node.next;
        }
        reverse(newHead);
        return ans;
    }

    private ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
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

    // NOTE: 标准每次*2的做法!
    // 注意左移操作优先级低, 要加括号
    public int getDecimalValue2(ListNode head) {
        if (head == null) {
            return 0;
        }
        int ans = 0;
        while (head != null) {
            ans = (ans << 1) + head.val;
            head = head.next;
        }
        return ans;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(0);
        head.next.next = new ListNode(1);
        var ans = new Problem_1290_GetDecimalValue().getDecimalValue(head);
        System.out.println(ans);
    }
}
