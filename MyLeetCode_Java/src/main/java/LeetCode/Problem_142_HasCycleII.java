package LeetCode;

public class Problem_142_HasCycleII {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // NOTE: 区别于链表有环的代码, 需要链表真正走的步数为1步, 两步
    // 看证明: https://leetcode.cn/problems/linked-list-cycle-ii/solution/javashu-xue-zheng-ming-by-jian-shi-1o4a/
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        // [1,2], 如果只有两个节点 要加上 head.next.next == null
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    public ListNode detectCycle1(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        ListNode circle = new ListNode(2);
        head.next = circle;
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next.next = circle;
        var ans = new Problem_142_HasCycleII().detectCycle(head);
        System.out.println(ans);

        var ans1 = new Problem_142_HasCycleII().detectCycle1(head);
        System.out.println(ans1);
    }
}
