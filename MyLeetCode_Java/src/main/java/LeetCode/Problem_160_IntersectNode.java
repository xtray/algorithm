package LeetCode;

import java.util.List;

public class Problem_160_IntersectNode {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) {
            return null;
        }
        int step = 0;
        ListNode node = headA;
        while (node.next != null) {
            step++;
            node = node.next;
        }
        node = headB;
        while (node.next != null) {
            step--;
            node = node.next;
        }
        ListNode big = step >0 ? headA : headB;
        ListNode small = big == headA?headB:headA;
        step = Math.abs(step);
        while (step>0) {
            big = big.next;
            step--;
        }
        while (big!=small) {
            big = big.next;
            small = small.next;
        }
        return big;
    }
}
