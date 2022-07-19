package LeetCode;


import java.util.ArrayList;
import java.util.List;

public class Problem_745_WordFilter {

    static class WordFilter {

        public class TrieNode {
            public TrieNode[] nexts;
            public List<Integer> list;
            public TrieNode() {
                nexts = new TrieNode[26];
                list = new ArrayList<>();
            }
        }

        private TrieNode frontTree;
        private TrieNode tailTree;

        public void insertFront(TrieNode root, String word, int index) {
            if (word == null) {
                return;
            }
            char[] str = word.toCharArray();
            TrieNode node = root;
            int path = 0;
            for (int i = 0; i < str.length; i++) { // 从左往右遍历字符
                path = str[i] - 'a'; // 由字符，对应成走向哪条路
                if (node.nexts[path] == null) {
                    node.nexts[path] = new TrieNode();
                }
                node = node.nexts[path];
                node.list.add(index);
            }
        }

        public void insertBack(TrieNode root, String word, int index) {
            if (word == null) {
                return;
            }
            char[] str = word.toCharArray();
            TrieNode node = root;
            int path = 0;
            for (int i = str.length -1; i >=0; i--) { // 从左往右遍历字符
                path = str[i] - 'a'; // 由字符，对应成走向哪条路
                if (node.nexts[path] == null) {
                    node.nexts[path] = new TrieNode();
                }
                node = node.nexts[path];
                node.list.add(index);
            }
        }
        // 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
        public List<Integer> frontList(TrieNode root, String pre) {
            if (pre == null) {
                return null;
            }
            char[] chs = pre.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    return null;
                }
                node = node.nexts[index];
            }
            return node.list;
        }

        // 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
        public List<Integer> backList(TrieNode root, String post) {
            if (post == null) {
                return null;
            }
            char[] chs = post.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = chs.length-1; i >=0 ; i--) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    return null;
                }
                node = node.nexts[index];
            }
            return node.list;
        }

        public WordFilter(String[] words) {
            frontTree = new TrieNode();
            tailTree = new TrieNode();
            int idx = 0;
            StringBuilder sb = new StringBuilder();
            for (String w : words) {
                insertFront(frontTree, w, idx);
                insertBack(tailTree, w, idx++);
            }
        }

        public int f(String pref, String suff) {
            List<Integer> preList = frontList(frontTree, pref);
            List<Integer> suffList = backList(tailTree, suff);
            if (preList == null || suffList == null) {
                return -1;
            }
            int N = preList.size();
            int M = suffList.size();
            for (int i = N - 1, j = M - 1; i >= 0 && j >= 0; ) {
                if (preList.get(i) > suffList.get(j)) {
                    i--;
                } else if (preList.get(i) < suffList.get(j)) {
                    j--;
                } else {
                    return preList.get(i);
                }
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        String[] words = {"abbba","abba"};
        String pref = "ab";
        String suffix = "ba";
        WordFilter sl = new WordFilter(words);
        var ans = sl.f(pref, suffix);
        System.out.println(ans);
    }
}
