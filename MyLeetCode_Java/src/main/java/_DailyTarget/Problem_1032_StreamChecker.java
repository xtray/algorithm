package _DailyTarget;

public class Problem_1032_StreamChecker {

    static class StreamChecker {

        public static class TrieNode {
            public TrieNode[] next;
            public boolean end;

            public TrieNode() {
                next = new TrieNode[26];
            }
        }

        // 倒着插入
        public void insertReverse(String word) {
            if (word == null || word.length() == 0) return;
            // char[] str = word.toCharArray();
            TrieNode cur = root;
            int N = word.length();
            for (int i = N - 1; i >= 0; i--) {
                // int path = str[i] - 'a';
                int path = word.charAt(i) - 'a';
                if (cur.next[path] == null) {
                    cur.next[path] = new TrieNode();
                }
                cur = cur.next[path];
            }
            cur.end = true;
        }

        // NOTE: toCharArray卡常数了, 用charAt或者固定长度字符串数组
        public boolean search(String word) {
            if (word == null || word.length() == 0) return true;
            // char[] str = word.toCharArray();
            TrieNode cur = root;
            int N = word.length();
            int min = Math.max(0, N - 200);
            for (int i = N - 1; i >= min; i--) {
                // int path = str[i] - 'a';
                int path = word.charAt(i) - 'a';
                if (cur.next[path] == null) {
                    return false;
                }
                cur = cur.next[path];
                if (cur.end) return true;
            }
            return cur.end;
        }

        public TrieNode root;
        public StringBuilder sb;

        public StreamChecker(String[] words) {
            root = new TrieNode();
            sb = new StringBuilder();
            for (String word : words) {
                insertReverse(word);
            }
        }

        public boolean query(char letter) {
            sb.append(letter);
            return search(sb.toString());
        }
    }
}
