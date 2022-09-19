package LeetCode;

import java.util.PriorityQueue;

public class Problem_23_MergeKSortedLists {

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

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0 || lists[0] == null) {
            return null;
        }
        int N = lists.length;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        for (int i = 0; i < N; i++) {
            if (lists[i] != null) { // NOTE: 注意某个list可能为null
                pq.add(lists[i]);
            }
        }
        ListNode dummyHead = new ListNode(-1);
        ListNode node = dummyHead;
        while (!pq.isEmpty()) {
            ListNode cur = pq.poll();
            node.next = cur;
            if (cur.next != null) {
                pq.add(cur.next);
            }
            node = node.next;
        }
        return dummyHead.next;
    }
}
