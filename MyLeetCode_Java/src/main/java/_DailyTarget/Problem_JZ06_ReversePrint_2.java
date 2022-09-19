package _DailyTarget;

public class Problem_JZ06_ReversePrint_2 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        int[] ans = new int[len];
        cur = head;
        while (cur != null) {
            ans[--len] = cur.val;
            cur = cur.next;
        }
        return ans;
    }
}
