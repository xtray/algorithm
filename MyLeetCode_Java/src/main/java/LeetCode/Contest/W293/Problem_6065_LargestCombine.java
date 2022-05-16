package LeetCode.Contest.W293;

import java.util.ArrayList;
import java.util.List;

public class Problem_6065_LargestCombine {


    public static class Node {
        public Node[] nexts = new Node[2];
    }

    public static class NumTrie {
        // 头节点
        public Node head = new Node();

        public void add(int newNum) {
            Node cur = head;
            for (int move = 31; move >= 0; move--) {
                int path = ((newNum >> move) & 1);
                cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
                cur = cur.nexts[path];
            }
        }

        // 该结构之前收集了一票数字，并且建好了前缀树
        // num和 谁 ^ 最大的结果（把结果返回）
        public int maxXor(int num) {
            Node cur = head;
            int ans = 0;
            for (int move = 31; move >= 0; move--) {
                // 取出num中第move位的状态，path只有两种值0就1，整数
                int path = (num >> move) & 1;
                // 期待遇到的东西
                int best = move == 31 ? path : (path ^ 1);
                // 实际遇到的东西
                best = cur.nexts[best] != null ? best : (best ^ 1);
                // (path ^ best) 当前位位异或完的结果
                ans |= (path ^ best) << move;
                cur = cur.nexts[best];
            }
            return ans;
        }
    }


    public int largestCombination(int[] candidates) {
        int[] cnt = new int[32];
        int ans = 0;
        for (int num : candidates) {
            for (int i = 0; i < 32; i++) {
                if ((num & (1 << i)) != 0) {
                    cnt[i]++;
                }
                ans = Math.max(ans, cnt[i]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // int[] candidates = {16,17,71,62,12,24,14};
        int[] candidates = {8, 8};
        var ans = new Problem_6065_LargestCombine().largestCombination(candidates);
        System.out.println(ans);
    }
}
