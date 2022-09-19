package _LintCode;

public class Problem_450_ReverseKGroup {


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }
        ListNode preGroupTail = null; // 上一组的尾部
        ListNode cur = head; // 当前组头部
        ListNode newHead = head; // 整体头部
        int groupCnt = 0; // 分组数
        while (cur != null) {
            groupCnt++;
            // 1. 获取k个节点的 head, tail
            // head: cur
            ListNode tail = getKthNode(cur, k);
            if (tail == null) { // 节点不够k个, 不翻转
                if (preGroupTail != null) {
                    preGroupTail.next = cur;
                }
                break;
            }
            if (groupCnt == 1) {
                // 第一组的尾部为全局的头部
                newHead = tail;
            }
            // 下一组的头部
            ListNode nextGroupHead = tail.next;
            // 2. 从head~tail逆序
            reverse(cur, tail);
            // 3. 前后链接
            if (preGroupTail != null) {
                preGroupTail.next = tail;
            }
            preGroupTail = cur;
            // 4.为下一组做准备
            cur = nextGroupHead;
        }
        return newHead;
    }

    private void reverse(ListNode head, ListNode tail) {
        ListNode pre = null;
        ListNode end = tail.next; // NOTE: 要保存end, 否则下面会改写掉
        while (head != end) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
    }

    private ListNode getKthNode(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        k--; // head本身算一个
        while (head != null && k > 0) {
            k--;
            head = head.next;
        }
        if (k > 0) { // 节点不够k个
            return null;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        int k = 6;
        var ans = new Problem_450_ReverseKGroup().reverseKGroup(head, k);
        print(ans);

    }

    private static void print(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "-->");
            head = head.next;
        }
        System.out.println();
    }

}
