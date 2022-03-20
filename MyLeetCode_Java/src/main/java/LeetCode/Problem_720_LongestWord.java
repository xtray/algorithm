package LeetCode;

public class Problem_720_LongestWord {

    public static class TrieNode {
        public String val;
        public TrieNode[] next;
        public boolean end;

        public TrieNode() {
            next = new TrieNode[26];
        }
    }

    private int maxLen = 0;
    private String res = "";

    public String longestWord(String[] words) {

        TrieNode head = new TrieNode();
        for (String w : words) {
            char[] str = w.toCharArray();
            TrieNode cur = head;
            for (char ch : str) {
                int idx = ch - 'a';
                if (cur.next[idx] == null) {
                    cur.next[idx] = new TrieNode();
                }
                cur = cur.next[idx];
            }
            cur.end = true;
            cur.val = w;
        }
        process(head);
        return res;
    }

    // 当前来到node节点, 收集所有最长的放在ans
    private void process(TrieNode node) {
        for (TrieNode next : node.next) {
            if (next != null && next.end) {
                if (next.val.length() > maxLen) {
                    maxLen = next.val.length();
                    res = next.val;
                } else if (next.val.length() == maxLen) {
                    res = res.compareTo(next.val) < 0 ? res : next.val;
                }
                process(next);
            }
        }
    }

    public static void main(String[] args) {
        String[] words = {"a", "banana", "app", "appl", "ap", "apply", "apple"};
        // String[] words = {"wo", "wor", "worl", "world"};
        var ans = new Problem_720_LongestWord().longestWord(words);
        System.out.println(ans);
    }
}
