package _DailyTarget;

public class Problem_JZ52_IntersectNode {

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
        ListNode node = headA;
        while (node != null) {
            node = node.next;
            step++;
        }
        node = headB;
        while (node != null) {
            node = node.next;
            step--;
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
