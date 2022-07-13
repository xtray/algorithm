package LeetCode.JZOffer;

public class Problem_JZ25_MergeTwoSortedList {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null ) return l2;
        if(l2 == null ) return l1;
        ListNode newHead = null;
        ListNode curNode = null;
        while (l1 != null && l2 != null) {
            if(l1.val < l2.val) {
                if(newHead == null){
                    newHead = l1;
                    curNode = l1;
                } else {
                    curNode.next = l1;
                    curNode = curNode.next;
                }
                l1 = l1.next;

            } else {
                if(newHead == null){
                    newHead = l2;
                    curNode = l2;
                } else {
                    curNode.next = l2;
                    curNode = curNode.next;
                }
                l2 = l2.next;
            }
        }
        if (l1!=null) {
            curNode.next = l1;
        }
        if (l2!=null) {
            curNode.next = l2;
        }
        return newHead;
    }
}
