package LeetCode;

import java.util.*;

public class Problem_139_WordBreak {

    private static class TrieNode {
        public TrieNode[] nexts;
        public boolean end;

        public TrieNode() {
            nexts = new TrieNode[26];
        }
    }

    private TrieNode root = new TrieNode();

    private void add(String s) {
        if (s == null || s.length() == 0) return;
        TrieNode node = root;
        for (char ch : s.toCharArray()) {
            int index = ch - 'a';
            if (node.nexts[index] == null) {
                node.nexts[index] = new TrieNode();
            }
            node = node.nexts[index];
        }
        node.end = true;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return true;
        for (String word : wordDict) {
            add(word);
        }
        char[] str = s.toCharArray();
        int N = str.length;
        // dp[i]: i...N字符串能不能被分解
        boolean[] dp = new boolean[N + 1];
        dp[N] = true;
        for (int i = N - 1; i >= 0; i--) {
            // 求dp[i]
            // i...j能不能在trie里, 然后调用dp[j+1]
            TrieNode cur = root;
            for (int j = i; j < N; j++) {
                int index = str[j] - 'a';
                if (cur.nexts[index] == null) {
                    break;
                }
                cur = cur.nexts[index];
                if (cur.end) {
                    dp[i] |= dp[j + 1];
                }
                if (dp[i]) {
                    break;
                }
            }
        }
        return dp[0];
    }
}
