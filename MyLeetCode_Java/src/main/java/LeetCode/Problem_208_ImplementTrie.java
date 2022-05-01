package LeetCode;

// IMP: 重要基础题
public class Problem_208_ImplementTrie {

    public static class TrieNode {
        private int pass;
        private int end;
        private TrieNode[] nexts;

        public TrieNode() {
            nexts = new TrieNode[26];
        }
    }

    private TrieNode root;

    public Problem_208_ImplementTrie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }
        char[] str = word.toCharArray();
        TrieNode node = root;
        node.pass++;
        for (char ch : str) {
            int path = ch - 'a';
            if (node.nexts[path] == null) {
                node.nexts[path] = new TrieNode();
            }
            node = node.nexts[path];
            node.pass++;
        }
        node.end++;
    }

    public boolean search(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        char[] str = word.toCharArray();
        TrieNode node = root;
        node.pass++;
        for (char ch : str) {
            int path = ch - 'a';
            if (node.nexts[path] == null) {
                return false;
            }
            node = node.nexts[path];
        }
        return node.end > 0;
    }

    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.length() == 0) {
            return false;
        }
        char[] str = prefix.toCharArray();
        TrieNode node = root;
        node.pass++;
        for (char ch : str) {
            int path = ch - 'a';
            if (node.nexts[path] == null) {
                return false;
            }
            node = node.nexts[path];
        }
        return node.pass > 0;
    }
}

