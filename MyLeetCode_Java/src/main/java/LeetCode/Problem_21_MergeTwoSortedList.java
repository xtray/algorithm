package LeetCode;

public class Problem_21_MergeTwoSortedList {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode newHead = null;
        ListNode curNode = null;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                if (newHead == null) {
                    newHead = l1;
                    curNode = l1;
                } else {
                    curNode.next = l1;
                    curNode = curNode.next;
                }
                l1 = l1.next;
            } else {
                if (newHead == null) {
                    newHead = l2;
                    curNode = l2;
                } else {
                    curNode.next = l2;
                    curNode = curNode.next;
                }
                l2 = l2.next;
            }
        }
        curNode.next = l1 != null ? l1 : l2;
        return newHead;
    }

    // IMP: 使用dummyHead 简化代码
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode dummyHead = new ListNode(-1);
        ListNode curNode = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curNode.next = l1;
                l1 = l1.next;
            } else {
                curNode.next = l2;
                l2 = l2.next;
            }
            curNode = curNode.next;
        }
        curNode.next = l1 != null ? l1 : l2;
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        var ans = new Problem_21_MergeTwoSortedList().mergeTwoLists(l1, l2);
        while (ans!=null) {
            System.out.print(ans.val + "-->");
            ans = ans.next;
        }
        System.out.println("null");
    }
}
