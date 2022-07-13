package LeetCode;

import java.util.Arrays;

public class Problem_2326_SpiralMatrix {
    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        ListNode(int v, ListNode n) {
            this.val = v;
            this.next = n;
        }
    }

    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(ans[i], -1);
        }
        if (head == null) {
            return ans;
        }
        // (a,b)   (c,d)
        int a = 0;
        int b = 0;
        int c = m - 1;
        int d = n - 1;
        ListNode[] cur = new ListNode[1];
        cur[0] = head;
        while (a <= c && b <= d && cur[0] != null) {
            process(a++, b++, c--, d--, ans, cur);
        }
        return ans;
    }

    // (a,b) -> (c,d)
    private void process(int a, int b, int c, int d, int[][] ans, ListNode[] cur) {
        ListNode head = cur[0];
        if (a == c) { // 同行了
            for (int i = b; i <= d; i++) {
                ans[a][i] = head == null ? -1 : head.val;
                if (head == null) {
                    cur[0] = head;
                    return;
                } else {
                    head = head.next;
                }
            }
        } else if (b == d) { // 同列了
            for (int i = a; i <= c; i++) {
                ans[i][b] = head == null ? -1 : head.val;
                if (head == null) {
                    cur[0] = head;
                    return;
                } else {
                    head = head.next;
                }
            }
        } else {
            int curC = b;
            int curR = a;
            while (curC != d) {
                ans[a][curC] = head == null ? -1 : head.val;
                if (head == null) {
                    cur[0] = head;
                    return;
                } else {
                    head = head.next;
                }
                curC++;
            }
            while (curR != c) {
                ans[curR][d] = head == null ? -1 : head.val;
                if (head == null) {
                    cur[0] = head;
                    return;
                } else {
                    head = head.next;
                }
                curR++;
            }
            while (curC != b) {
                ans[c][curC] = head == null ? -1 : head.val;
                if (head == null) {
                    cur[0] = head;
                    return;
                } else {
                    head = head.next;
                }
                curC--;
            }
            while (curR != a) {
                ans[curR][b] = head == null ? -1 : head.val;
                if (head == null) {
                    cur[0] = head;
                    return;
                } else {
                    head = head.next;
                }
                curR--;
            }
            cur[0] = head;
        }
    }

    public static void main(String[] args) {
        int m = 9, n = 6;
        int[] nums = {995, 348, 36, 516, 333, 627, 248, 422, 13, 225, 764, 311, 405, 695, 698, 83, 145, 783, 478};
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        for (int num : nums) {
            cur.next = new ListNode(num);
            cur = cur.next;
        }
        var ans = new Problem_2326_SpiralMatrix().spiralMatrix(m, n, head.next);
        System.out.println(Arrays.deepToString(ans));
    }

}
