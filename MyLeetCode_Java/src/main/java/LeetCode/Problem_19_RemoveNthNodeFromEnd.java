package LeetCode;

public class Problem_19_RemoveNthNodeFromEnd {

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

    public static ListNode removeNthFromEnd(ListNode head, int k) {
        if (head == null || k == 0) return head;

        int count = 1;
        ListNode first = head;
        ListNode second = head;
        while (count <= k && second != null) { // 有可能不足 k 个
            second = second.next;
            count++;
        }
        if (count <= k) return head; //总共不足 k 个

        // count > K
        if (second == null) { // 刚好 k 个, 即删除头部
            return head.next;
        }
        // first 跟 second 一起跳, 当 second 到达 末尾, 他俩间隔为k
        // 此时 first 位于倒数第 k 的前一个
        while (second.next != null) {
            second = second.next;
            first = first.next;
        }
        first.next = first.next.next;
        return head;
    }

    public static ListNode removeNthFromEnd2(ListNode head, int k) {
        if (head == null || k == 0) return head;
        ListNode cur = head; // second
        ListNode pre = null; // first
        while (cur != null) {
            k--; // 上来先减一个
            if (k == -1) { // 等同于第一个指针已经走了 k步
                pre = head;
            }
            if (k < -1) {
                pre = pre.next;
            }
            cur = cur.next;
        }
        if (k > 0) { // 不足 k 个
            return head;
        }
        // k == 0, pre 还没有走任何一步, cur==null
        if (pre == null) { // 删除的是 head
            return head.next;
        }
        pre.next = pre.next.next;
        return head;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode cur = node1;
        while (cur!=null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();

        int k = 5;
        ListNode res = removeNthFromEnd2(node1, k);
        while (res!=null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
        System.out.println();
    }

}
