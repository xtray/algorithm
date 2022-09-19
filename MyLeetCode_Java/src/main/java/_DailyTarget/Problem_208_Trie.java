package _DailyTarget;

public class Problem_208_Trie {


    class Trie {

        public class TrieNode {
            public TrieNode[] next;
            public boolean end;

            public TrieNode() {
                next = new TrieNode[26];
            }
        }

        public TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            if (word == null || word.length() == 0) return;
            char[] str = word.toCharArray();
            TrieNode cur = root;
            for (char ch : str) {
                int path = ch - 'a';
                if (cur.next[path] == null) {
                    cur.next[path] = new TrieNode();
                }
                cur = cur.next[path];
            }
            cur.end = true;
        }

        public boolean search(String word) {
            if (word == null || word.length() == 0) return true;
            char[] str = word.toCharArray();
            TrieNode cur = root;
            for (char ch : str) {
                int path = ch - 'a';
                if (cur.next[path] == null) {
                    return false;
                }
                cur = cur.next[path];
            }
            return cur.end;
        }

        public boolean startsWith(String prefix) {
            if (prefix == null || prefix.length() == 0) return true;
            char[] str = prefix.toCharArray();
            TrieNode cur = root;
            for (char ch : str) {
                int path = ch - 'a';
                if (cur.next[path] == null) {
                    return false;
                }
                cur = cur.next[path];
            }
            return true;
        }
    }

}
