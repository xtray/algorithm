package _DailyTarget;

public class Problem_1032_StreamChecker_2 {

    class StreamChecker {

        public class TrieNode {
            public TrieNode[] nexts;
            public boolean end;

            public TrieNode() {
                nexts = new TrieNode[26];
            }
        }

        private TrieNode root = new TrieNode();
        private StringBuilder sb = new StringBuilder();

        public StreamChecker(String[] words) {
            for (String w : words) {
                addNode(w);
            }
        }

        private void addNode(String s) {
            TrieNode cur = root;
            int N = s.length();
            for (int i = N - 1; i >= 0; i--) {
                int idx = s.charAt(i) - 'a';
                if (cur.nexts[idx] == null) {
                    cur.nexts[idx] = new TrieNode();
                }
                cur = cur.nexts[idx];
            }
            cur.end = true;
        }

        public boolean query(char letter) {
            sb.append(letter);
            TrieNode cur = root;
            int N = sb.length();
            int min = Math.max(0, N - 200);
            for (int i = N - 1; i >= min; i--) {
                int idx = sb.charAt(i) - 'a';
                if (cur.nexts[idx] == null) {
                    return false;
                }
                cur = cur.nexts[idx];
                if (cur.end) {
                    return true;
                }
            }
            return cur.end;
        }
    }
}
