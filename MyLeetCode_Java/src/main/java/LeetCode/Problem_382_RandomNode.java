package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_382_RandomNode {

    public class ListNode {
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

    class Solution {

        List<Integer> list = new ArrayList<>();

        public Solution(ListNode head) {
            while (head != null) {
                list.add(head.val);
                head = head.next;
            }
        }

        public int getRandom() {
            int index = (int)(Math.random()*list.size());
            return list.get(index);
        }
    }
}
