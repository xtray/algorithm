package _Contest.LC.W311;

import java.util.Arrays;

public class Problem_2416_PrefixScores {

    public static class TrieNode {
        public int pass;
        public int end;
        public TrieNode[] nexts;

        public TrieNode() {
            pass = 0;
            end = 0;
            nexts = new TrieNode[26];
        }
    }

    public static class TrieTree {
        private TrieNode root;

        public TrieTree() {
            root = new TrieNode();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] str = word.toCharArray();
            TrieNode node = root;
            node.pass++;
            int path = 0;
            for (int i = 0; i < str.length; i++) { // 从左往右遍历字符
                path = str[i] - 'a'; // 由字符，对应成走向哪条路
                if (node.nexts[path] == null) {
                    node.nexts[path] = new TrieNode();
                }
                node = node.nexts[path];
                node.pass++;
            }
            node.end++;
        }

        public void delete(String word) {
            if (search(word) != 0) {
                char[] chs = word.toCharArray();
                TrieNode node = root;
                node.pass--;
                int path = 0;
                for (int i = 0; i < chs.length; i++) {
                    path = chs[i] - 'a';
                    if (--node.nexts[path].pass == 0) {
                        node.nexts[path] = null;
                        return;
                    }
                    node = node.nexts[path];
                }
                node.end--;
            }
        }

        // word这个单词之前加入过几次
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chs = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end;
        }

        public int getScore(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chs = pre.toCharArray();
            TrieNode node = root;
            int index = 0;
            int score = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
                score += node.pass;
            }
            return score;
        }
    }

    public int[] sumPrefixScores(String[] words) {

        TrieTree trie = new TrieTree();
        for (String w : words) trie.insert(w);
        int N = words.length;
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            ans[i] = trie.getScore(words[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] words = {"abc", "ab", "bc", "b"};
        var ans = new Problem_2416_PrefixScores().sumPrefixScores(words);
        System.out.println(Arrays.toString(ans));
    }
}
