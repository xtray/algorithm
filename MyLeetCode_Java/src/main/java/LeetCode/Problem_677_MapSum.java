package LeetCode;

import AlgoExpert.Problem_212_WordSearchII;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Problem_677_MapSum {

    static class MapSum {

        public class TrieNode {
            public TrieNode nexts[];
            public int pass;
            public boolean end;
            public int val;

            public TrieNode() {
                nexts = new TrieNode[26];
                pass = 0;
                end = false;
                val = 0;
            }
        }

        private TrieNode head;

        public MapSum() {
            head = new TrieNode();
        }

        public void insert(String key, int val) {
            if (key == null || key.length() == 0) {
                return;
            }

            char[] str = key.toCharArray();
            head.pass++;
            TrieNode node = head;
            for (int i = 0; i < str.length; i++) {
                int index = str[i] - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new TrieNode();
                }
                node = node.nexts[index];
                node.pass++;
            }
            node.end = true;
            node.val = val;
        }

        public int sum(String prefix) {
            if (prefix == null || prefix.length() == 0) {
                return 0;
            }
            char[] str = prefix.toCharArray();
            TrieNode node = head;
            for (int i = 0; i < str.length; i++) {
                int index = str[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            int res = node.end?node.val:0;
            // BFS
            Queue<TrieNode> queue = new LinkedList<>();
            queue.add(node);
            while (!queue.isEmpty()) {
                int size = queue.size();
                while (size-- > 0) {
                    TrieNode cur = queue.poll();
                    for (int i = 0; i < 26; i++) {
                        if (cur.nexts[i] != null) {
                            queue.add(cur.nexts[i]);
                            if (cur.nexts[i].end) {
                                res += cur.nexts[i].val;
                            }
                        }
                    }
                }
            }
            return res;
        }
    }

    // 最优解
    // 仿照 pass 值的设计, 引入 sum 值, 过节点 sum +=val
    static class MapSum2 {

        public class TrieNode {
            public TrieNode nexts[];
            public int pass;
            public boolean end;
            public int sum;

            public TrieNode() {
                nexts = new TrieNode[26];
                pass = 0;
                end = false;
                sum = 0;
            }
        }

        private TrieNode head;

        public MapSum2() {
            head = new TrieNode();
        }

        public void insert(String key, int val) {
            if (key == null || key.length() == 0) {
                return;
            }

            char[] str = key.toCharArray();
            head.pass++;
            TrieNode node = head;
            for (int i = 0; i < str.length; i++) {
                int index = str[i] - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new TrieNode();
                }
                node = node.nexts[index];
                node.sum += val;
            }
            node.end = true;
        }

        public int sum(String prefix) {
            if (prefix == null || prefix.length() == 0) {
                return 0;
            }
            char[] str = prefix.toCharArray();
            TrieNode node = head;
            int sum = 0;
            for (int i = 0; i < str.length; i++) {
                int index = str[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.sum;
        }
    }

    public static void main(String[] args) {
        MapSum2 ms = new MapSum2();
        ms.insert("aa", 3);
        int ans = ms.sum("a");
        System.out.println(ans);
        ms.insert("ab", 2);
        ans = ms.sum("a");
        System.out.println(ans);
    }
}
