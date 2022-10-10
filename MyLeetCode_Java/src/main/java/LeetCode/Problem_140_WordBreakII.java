package LeetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem_140_WordBreakII {

    private static class TrieNode {
        public TrieNode[] nexts;
        public boolean end;
        public String val;

        public TrieNode() {
            nexts = new TrieNode[26];
        }
    }

    private void add(String s, TrieNode root) {
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
        node.val = s;
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        TrieNode root = new TrieNode();
        for (String word : wordDict) {
            add(word, root);
        }
        boolean[] dp = getdp(s, root);
        List<String> ans = new ArrayList<>();
        List<String> path = new ArrayList<>();

        process(s.toCharArray(), 0, root, dp, path, ans);

        return ans;
    }

    // 当前处理到i位置
    private void process(char[] str, int i, TrieNode root, boolean[] dp, List<String> path, List<String> ans) {
        int N = str.length;
        if (i == N) {
            if (!path.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (int k = 0; k < path.size(); k++) {
                    sb.append(path.get(k));
                    if (k != path.size() - 1) {
                        sb.append(" ");
                    }
                }
                ans.add(sb.toString());
            }
            return;
        }
        TrieNode cur = root;
        for (int end = i; end < N; end++) {
            int road = str[end] - 'a';
            if (cur.nexts[road] == null) {
                break;
            }
            cur = cur.nexts[road];
            if (cur.end && dp[end + 1]) {
                // i...end
                path.add(cur.val);
                process(str, end + 1, root, dp, path, ans);
                path.remove(path.size() - 1);
            }
        }
    }

    private boolean[] getdp(String s, TrieNode root) {
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
        return dp;
    }


    // public List<String> wordBreak(String s, Set<String> wordDict) {
    //     for (String word : wordDict) {
    //         add(word);
    //     }
    //     List<String> ans = new ArrayList<>();
    //     process(s.toCharArray(), 0, root, new ArrayList<>(), ans);
    //     return ans;
    // }

    // TLE
    // private void process0(char[] str, int start, TrieNode cur, List<String> path, List<String> ans) {
    //     if (start == str.length) {
    //         if (!path.isEmpty()) {
    //             StringBuilder sb = new StringBuilder();
    //             int N = path.size();
    //             for (int k = 0; k < N; k++) {
    //                 sb.append(path.get(k));
    //                 if (k != N - 1) {
    //                     sb.append(" ");
    //                 }
    //             }
    //             ans.add(sb.toString());
    //         }
    //         return;
    //     }
    //     TrieNode node = cur;
    //     for (int i = start; i < str.length; i++) {
    //         int index = str[i] - 'a';
    //         if (node.nexts[index] == null) {
    //             return;
    //         }
    //         node = node.nexts[index];
    //         if (node.end) {
    //             path.add(String.valueOf(str, start, i - start + 1));
    //             process(str, i + 1, root, path, ans);
    //             path.remove(path.size() - 1);
    //         }
    //     }
    // }

    public static void main(String[] args) {
        String s = "catsanddog";
        String[] dict = {"cat","cats","and","sand","dog"};
        var ans = new Problem_140_WordBreakII().wordBreak(s, List.of(dict));
        System.out.println(ans);
    }
}
