package _Exercise;

import java.util.ArrayList;
import java.util.List;

public class Problem_212_WordSearchII {

    public static class TrieNode {
        public int pass; // 当前节点通过次数
        public boolean end; // 当前节点是否是单词结尾
        public String val; // 存储单词
        public TrieNode[] nexts; // 下级节点

        public TrieNode() {
            nexts = new TrieNode[26];
        }
    }


    private void insert(TrieNode trie, String s) {
        if (s == null || s.length() == 0) {
            return;
        }
        trie.pass++;
        TrieNode cur = trie;
        for (char ch : s.toCharArray()) {
            int path = ch - 'a';
            if (cur.nexts[path] == null) {
                cur.nexts[path] = new TrieNode();
            }
            cur = cur.nexts[path];
            cur.pass++;
        }
        cur.end = true;
        cur.val = s;
    }


    private int process(char[][] board, int i, int j, TrieNode cur, List<String> ans) {
        int N = board.length;
        int M = board[0].length;
        if (i < 0 || i >= N || j < 0 || j >= M || board[i][j] == 0) {
            return 0;
        }
        char ch = board[i][j];
        if (cur.nexts[ch - 'a'] == null || cur.nexts[ch - 'a'].pass == 0) {
            return 0;
        }
        cur = cur.nexts[ch - 'a'];
        int fix = 0;
        if (cur.end) {
            ans.add(cur.val);
            cur.end = false;
            fix++;
        }
        board[i][j] = 0;
        fix += process(board, i + 1, j, cur, ans);
        fix += process(board, i - 1, j, cur, ans);
        fix += process(board, i, j + 1, cur, ans);
        fix += process(board, i, j - 1, cur, ans);
        board[i][j] = ch;
        cur.pass -= fix;
        return fix;
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new ArrayList<>();
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0 ||
                words == null || words.length == 0) {
            return ans;
        }
        // 根据words构建字典树
        TrieNode trie = new TrieNode();
        for (String s : words) {
            insert(trie, s);
        }

        int N = board.length;
        int M = board[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                process(board, i, j, trie, ans);
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'a'},
                {'a'}
        };
        String[] words = {"aa"};
        var ans = new Problem_212_WordSearchII().findWords(board, words);
        System.out.println(ans);

    }
}
