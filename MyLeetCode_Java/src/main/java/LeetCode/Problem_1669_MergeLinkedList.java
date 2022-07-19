package LeetCode;

public class Problem_1669_MergeLinkedList {

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

    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {

        ListNode nodeAPre = getKNode(list1, a - 1);
        ListNode next = null;
        ListNode tail2 = list2;
        while (tail2.next != null) {
            tail2 = tail2.next;
        }
        if (b == a) {
            next = nodeAPre.next.next;
        } else {
            ListNode nodeB = getKNode(list1, b);
            next = nodeB.next;
        }
        nodeAPre.next = list2;
        tail2.next = next;
        return list1;
    }

    // 找到链表中下标k的节点
    private ListNode getKNode(ListNode list, int k) {
        ListNode node = list;
        while (k > 0) {
            node = node.next;
            k--;
        }
        return node;
    }
}
