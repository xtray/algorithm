package LeetCode.Jianzhi;

public class Problem_JZ006_ReversePrint {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public int[] reversePrint(ListNode head) {
        if(head == null) {
            return new int[]{};
        }
        if(head.next == null) {
            return new int[]{head.val};
        }
        // 至少有两个
        ListNode pre = null;
        ListNode cur = head;
        int size = 0;
        while (cur!=null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            size++;
        }
        int[] ans = new int[size];
        cur = pre;
        int idx = 0;
        pre = null;
        while (cur!=null) {
            ans[idx++] = cur.val;
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return ans;
    }

    public int[] reversePrint2(ListNode head) {
        if (head == null) {
            return new int[]{};
        }
        if (head.next == null) {
            return new int[]{head.val};
        }
        int cnt = 0;
        ListNode cur = head;
        while (cur!=null) {
            cnt++;
            cur = cur.next;
        }
        int[] ans = new int[cnt];
        cur = head;
        int idx = cnt - 1;
        while (cur!=null) {
            ans[idx--] = cur.val;
            cur = cur.next;
        }
        return ans;
    }

}
