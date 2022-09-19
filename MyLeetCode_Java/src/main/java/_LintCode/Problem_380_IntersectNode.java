package _LintCode;

public class Problem_380_IntersectNode {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        int step = 0;
        ListNode cur = headA;
        while (cur != null) {
            step++;
            cur = cur.next;
        }
        cur = headB;
        while (cur != null) {
            step--;
            cur = cur.next;
        }
        ListNode big = step > 0 ? headA : headB;
        ListNode small = big == headA ? headB : headA;
        step = step < 0 ? -step : step;
        while (step != 0) {
            step--;
            big = big.next;
        }
        while (big != small) {
            big = big.next;
            small = small.next;
        }
        return big;
    }
}
