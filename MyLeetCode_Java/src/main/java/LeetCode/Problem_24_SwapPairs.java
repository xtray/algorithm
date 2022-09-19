package LeetCode;

public class Problem_24_SwapPairs {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null; // 上一组的尾部
        ListNode cur = head;
        ListNode newHead = head.next;
        while (cur != null && cur.next != null) { // 一次取出两个点
            ListNode n1 = cur;
            ListNode n2 = cur.next;
            ListNode next = cur.next.next; // 下一组开头
            // 1.组内逆序
            n2.next = n1;
            n1.next = null;
            // 2.跟上一组链接
            if (pre != null) {
                pre.next = n2;
            }
            // 3.为下一组做准备
            pre = n1;
            cur = next;
        }
        if (cur != null) { // NOTE: 连接上最后一组的单独一个
            pre.next = cur;
        }
        return newHead;
    }

    public ListNode swapPairs1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null; // 上一组的尾部
        ListNode cur = head;
        ListNode newHead = head.next;
        while (cur != null && cur.next != null) { // 一次取出两个点
            ListNode n1 = cur;
            ListNode n2 = cur.next;
            ListNode next = cur.next.next; // 下一组开头
            // 1.组内逆序
            n2.next = n1;
            n1.next = null;
            // 2.跟上一组链接
            if (pre != null) {
                pre.next = n2;
            }
            // 3.为下一组做准备
            pre = n1;
            pre.next = next; // 跟下一组链接, 如果够两个会被改写掉
            cur = next;
        }
        return newHead;
    }

    public ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode pre = dummyHead; // 上一组最后一个
        while (pre.next != null && pre.next.next != null) {
            // 0.一次取出两个节点
            ListNode n1 = pre.next;
            ListNode n2 = pre.next.next;
            ListNode next = pre.next.next.next; // 下一组的开头
            // 组内链接
            n2.next = n1;
            n1.next = null;
            // 2.跟上一组链接
            pre.next = n2;
            // 3.准备下一组
            pre = n1; // pre等于上一组的tail
            n1.next = next; // n1.next 相当于cur
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        // head.next.next.next = new ListNode(4);
        var ans = new Problem_24_SwapPairs().swapPairs(head);
        printList(ans);
    }

    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "-->");
            head = head.next;
        }
        System.out.println();
    }


}
