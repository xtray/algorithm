package Contest.LC.Unipay;

import java.util.ArrayList;
import java.util.List;

public class Problem_001_IsPalindrome {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // TLE
    public boolean isPalindrome(ListNode head) {

        List<Integer> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }
        // 删除每一个字母判断是不是回文
        for (int i = 0; i < list.size(); i++) {
            if (judge(list, i)) {
                return true;
            }
        }
        return false;
    }

    // 去除掉i位置以后是不是回文
    private boolean judge(List<Integer> list, int i) {
        int N = list.size();
        int L = 0;
        int R = N - 1;
        while (L < R) {
            if (L == i) {
                L++;
            }
            if (R == i) {
                R--;
            }
            if (list.get(L).equals(list.get(R))) {
                L++;
                R--;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome2(ListNode head) {

        List<Integer> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }
        boolean ans = process(list, 0, list.size() - 1, false);
        return ans;
    }

    // L...R
    private boolean process(List<Integer> list, int L, int R, boolean del) {
        if (L == R) {
            return true;
        }
        if (L + 1 == R) {
            if(!del) {
                return true;
            }
            return list.get(L).equals(list.get(R));
        }
        boolean p1 = false;
        boolean p2 = false;
        boolean p3 = false;
        if (list.get(L).equals(list.get(R))) {
            p1 = process(list, L + 1, R - 1, del);
        } else {
            if (!del) {
                p2 = process(list, L + 1, R, true);
                p3 = process(list, L, R - 1, true);
            } else {
                return false;
            }
        }
        return !p1 ? !p2 ? p3 : p2 : p1;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        // list.add(1);
        // list.add(2);
        // list.add(2);
        // list.add(3);
        // list.add(1);
        // list.add(7);
        // list.add(7);
        // list.add(1);
        // list.add(5);
        // list.add(5);
        // list.add(1);
        // list.add(7);
        // list.add(3);
        list.add(6);
        list.add(4);
        list.add(0);
        list.add(6);
        list.add(10);
        list.add(0);
        list.add(4);
        list.add(6);
        // var ans = new Problem_001_IsPalindrome().judge(list, 2);
        var ans = new Problem_001_IsPalindrome().process(list, 0, 7, false);
        System.out.println(ans);
    }

}
