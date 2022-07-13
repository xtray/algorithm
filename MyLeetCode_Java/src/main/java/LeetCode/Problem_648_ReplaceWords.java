package LeetCode;

import java.util.ArrayList;
import java.util.List;

// TAG: 字典树, Trie

public class Problem_648_ReplaceWords {

    public static class TrieNode {
        public TrieNode[] nexts;
        public int pass;
        public boolean end;
        public String val;

        public TrieNode() {
            nexts = new TrieNode[26];
            pass = 0;
            end = false;
            val = null;
        }
    }

    private static void fillWord(TrieNode head, String word) {
        head.pass++;
        char[] chs = word.toCharArray();
        int index = 0;
        TrieNode node = head;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a';
            if (node.nexts[index] == null) {
                node.nexts[index] = new TrieNode();
            }
            node = node.nexts[index];
            node.pass++;
        }
        node.end = true;
        node.val = word;
    }

    public String replaceWords(List<String> dict, String sent) {
        if (dict == null || dict.size() == 0 || sent == null || sent.length() == 0) {
            return null;
        }
        TrieNode head = new TrieNode();
        for (String word : dict) {
            fillWord(head, word);
        }
        StringBuilder sb = new StringBuilder();
        String[] strs = sent.split(" ");
        for (String word : strs) {
            String str = queryWord(head, word);
            sb.append(str).append(" ");
        }
        return sb.substring(0, sb.length() - 1);
    }

    private String queryWord(TrieNode head, String word) {
        char[] str = word.toCharArray();
        TrieNode node = head;
        for (char ch : str) {
            int idx = ch - 'a';
            // rattled --> rat
            // r --> rat
            if (node.nexts[idx] == null) { // 没有这个路
                break;
            }
            node = node.nexts[idx];
            if (node.end) {
                return node.val;
            }
        }
        return word;
    }

    public static void main(String[] args) {
        List<String> dict = new ArrayList<>();
        dict.add("cat");
        dict.add("rat");
        String sent = "the r aa";
        var ans = new Problem_648_ReplaceWords().replaceWords(dict, sent);
        System.out.println(ans);
    }
}
