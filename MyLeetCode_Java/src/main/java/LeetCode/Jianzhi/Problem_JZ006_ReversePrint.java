package LeetCode.Jianzhi;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Problem_JZ006_ReversePrint {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // IMP: 用栈的解法
    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[]{};
        }
        LinkedList<Integer> stack = new LinkedList<>();
        while (head != null) {
            stack.addFirst(head.val);
            head = head.next;
        }
        int[] ans = new int[stack.size()];
        int idx = 0;
        while (!stack.isEmpty()) {
            ans[idx++] = stack.pollFirst();
        }
        return ans;
    }

    // 倒着填
    public int[] reversePrint2(ListNode head) {
        if (head == null) {
            return new int[]{};
        }
        int size = 0;
        ListNode cur = head;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        int[] ans = new int[size];
        int idx = size - 1;
        cur = head;
        while (cur != null) {
            ans[idx--] = cur.val;
            cur = cur.next;
        }
        return ans;
    }

    // 链表逆序
    // IMP: 链表逆序, 不要忘了再逆序回来
    public int[] reversePrint3(ListNode head) {
        if (head == null) {
            return new int[]{};
        }
        List<Integer> ans = new ArrayList<>();
        ListNode reversed = reverse(head);
        ListNode node = reversed;
        while (node != null) {
            ans.add(node.val);
            node = node.next;
        }
        reverse(reversed);
        int[] res = new int[ans.size()];
        int idx = 0;
        for (int num : ans) {
            res[idx++] = num;
        }
        return res;
    }

    private ListNode reverse(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

}
