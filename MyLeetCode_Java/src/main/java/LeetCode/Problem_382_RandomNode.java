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


    // 蓄水池抽样算法
    class Solution1 {

        public ListNode head;


        public Solution1(ListNode head) {
            this.head = head;
        }

        // 从前往后, 每个元素以 1/i的概率被抽中
        public int getRandom() {
            int ans = 0;
            int idx = 0;
            ListNode cur = head;
            while (cur != null) {
                idx++;
                int rand = (int) (Math.random() * idx); // 0~idx-1
                if (rand == 0) {
                    ans = cur.val;
                }
                cur = cur.next;
            }
            return ans;
        }
    }
}
