package _DailyTarget;

public class Problem_JZ52_IntersectNode_2 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
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
        step = Math.abs(step);
        while (step > 0) {
            big = big.next;
            step--;
        }
        while (big != small) {
            big = big.next;
            small = small.next;
        }
        return big;
    }
}
