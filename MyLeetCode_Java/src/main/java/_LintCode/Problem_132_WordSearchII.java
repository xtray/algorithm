package _LintCode;

import java.util.*;

public class Problem_132_WordSearchII {

    public static class Node {
        public int pass;
        public boolean end;
        public String val;
        public Node[] nexts;

        public Node() {
            pass = 0;
            end = false;
            nexts = new Node[26];
        }
    }

    public void insert(Node root, String word) {
        if (word == null) {
            return;
        }
        char[] str = word.toCharArray();
        Node node = root;
        node.pass++;
        int path = 0;
        for (int i = 0; i < str.length; i++) { // 从左往右遍历字符
            path = str[i] - 'a'; // 由字符，对应成走向哪条路
            if (node.nexts[path] == null) {
                node.nexts[path] = new Node();
            }
            node = node.nexts[path];
            node.pass++;
        }
        node.val = word;
        node.end = true;
    }


    public List<String> wordSearchII(char[][] board, List<String> words) {
        List<String> ans = new ArrayList<>();
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return ans;
        }

        Node root = new Node();

        for (String s : words) {
            insert(root, s);
        }

        int N = board.length;
        int M = board[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                process(board, i, j, root, ans);
            }
        }
        return ans;
    }

    private int process(char[][] board, int i, int j, Node cur, List<String> ans) {
        int N = board.length;
        int M = board[0].length;
        if (i < 0 || i >= N || j < 0 || j >= M || board[i][j] == 0) {
            return 0;
        }
        char ch = board[i][j];
        int path = ch - 'a';
        if (cur.nexts[path] == null || cur.nexts[path].pass == 0) {
            return 0;
        }
        cur = cur.nexts[path];
        int fix = 0; //从 i,j 出发一共搞定的答案数目
        if (cur.end) { // 是单词结尾, 可以收集
            fix++;
            ans.add(cur.val);
            cur.end = false;
        }
        board[i][j] = 0;//防止走回头路

        fix += process(board, i - 1, j, cur, ans);
        fix += process(board, i + 1, j, cur, ans);
        fix += process(board, i, j - 1, cur, ans);
        fix += process(board, i, j + 1, cur, ans);

        board[i][j] = ch; // DFS 恢复
        cur.pass -= fix;
        return fix;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'d', 'o', 'a', 'f'},
                {'a', 'g', 'a', 'i'},
                {'d', 'c', 'a', 'n'}
        };
        String[] words = {"dog", "dad", "dgdg", "can", "again"};
        List<String> dict = new ArrayList<>(Arrays.asList(words));
        var ans = new Problem_132_WordSearchII().wordSearchII(board, dict);
        System.out.println(ans);

    }


}
