package _DailyTarget;

public class Problem_JZ25_MergeLinkedList {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {

            val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;
        while (l1 != null || l2 != null) {
            if (l1 != null && l2 != null) {
                cur.next = l1.val < l2.val ? l1 : l2;
                if (cur.next == l1) {
                    l1 = l1.next;
                } else {
                    l2 = l2.next;
                }
            } else if (l1 != null) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        return dummyHead.next;
    }
}

