package LeetCode.JZOffer;

import java.util.PriorityQueue;

public class Problem_JZII078_MergeKLists {
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

    public static class NodeInfo {
        public int val;
        public ListNode node;

        public NodeInfo(int _val, ListNode _node) {
            val = _val;
            node = _node;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        int N = lists.length;
        // [val, fromID, pos]
        PriorityQueue<NodeInfo> pq = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        for (int i = 0; i < N; i++) {
            if(lists[i] != null) {
                pq.add(new NodeInfo(lists[i].val, lists[i]));
            }
        }
        ListNode dummyHead = new ListNode(-1);
        ListNode curNode = dummyHead;
        while (!pq.isEmpty()) {
            NodeInfo nodeInfo = pq.poll();
            ListNode cur = nodeInfo.node;
            curNode.next = cur;
            if(cur.next != null) {
                pq.add(new NodeInfo(cur.next.val, cur.next));
            }
            curNode = cur;
        }
        return dummyHead.next;
    }
}
