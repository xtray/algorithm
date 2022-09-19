package _DailyTarget;

import java.util.ArrayList;
import java.util.List;

public class Problem_JZ06_ReversePrint {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public int[] reversePrint(ListNode head) {
        if (head == null) return new int[0];
        int n = 0;
        ListNode cur = head;
        while (cur != null) {
            n++;
            cur = cur.next;
        }
        int[] ans = new int[n];
        cur = head;
        while (cur != null) {
            ans[--n] = cur.val;
            cur = cur.next;
        }
        return ans;
    }

    public int[] reversePrint1(ListNode head) {
        if (head == null) return new int[0];
        List<Integer> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }
        int n = list.size();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = list.get(n - 1 - i);
        }
        return ans;
    }
}
