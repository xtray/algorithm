package AlgoExpert;

import java.util.*;

// 本题和 Leetcode 不一样, 可以斜着走
// 字符存在大小写, 还有符号
public class Problem_212_WordSearchII {

    public static class TrieNode {
        public TrieNode[] nexts;
        public int pass;
        public boolean end;

        public TrieNode() {
            nexts = new TrieNode[128]; // 有大小写, 有符号
            pass = 0;
            end = false;
        }
    }

    public static List<String> boggleBoard(char[][] board, String[] words) {
        TrieNode head = new TrieNode();  // 前缀树头结点
        Set<String> set = new HashSet<>(); // words 去重
        for (String word : words) {
            if (!set.contains(word)) {
                // 生成前缀树
                fillWord(head, word);
                set.add(word);
            }
        }
        List<String> ans = new ArrayList<>();
        // 沿途走过的路收集到 path 里
        List<Character> path = new LinkedList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 枚举每一个 board 中的位置, 从每一个位置出发的答案都收集
                process(board, i, j, path, head, ans);
            }
        }
        return ans;
    }

    // 从 board[i][j]位置出发, 之前的路(走过的字符)都在 path 里
    // cur 还没有登上去, 是需要等待检查能不能登上去的节点
    // 如果找到 word 中的某个单词就记录在 res 里
    // 返回值: 从 i,j 出发一共收集到了多少个单词
    private static int process(char[][] board, int i, int j, List<Character> path, TrieNode cur, List<String> res) {
        if (!canWalk(board, i, j)) {
            return 0;
        }
        char cha = board[i][j]; //后面要抹掉, 不走回头路
        if (cha == 0) {
            //回头路
            return 0;
        }
        int index = cha;
        // 如果没路
        // 或者之前这条路收集过结果了
        if (cur.nexts[index] == null || cur.nexts[index].pass == 0) {
            return 0;
        }
        // 没走回头路, 且可以登上去
        cur = cur.nexts[index];
        path.add(cha); //当前词加入路径
        int fix = 0; //从 i,j 出发一共搞定的答案数目
        if (cur.end) { // 是单词结尾, 可以收集
            res.add(generatePath(path));
            cur.end = false;
            fix++;
        }
        //往上下左右X 8个方向做搜索
        board[i][j] = 0;//防止走回头路

        fix += process(board, i - 1, j, path, cur, res);
        fix += process(board, i + 1, j, path, cur, res);
        fix += process(board, i, j - 1, path, cur, res);
        fix += process(board, i, j + 1, path, cur, res);

        fix += process(board, i - 1, j-1, path, cur, res);
        fix += process(board, i + 1, j+1, path, cur, res);
        fix += process(board, i+1, j - 1, path, cur, res);
        fix += process(board, i-1, j + 1, path, cur, res);

        board[i][j] = cha; // DFS 恢复
        path.remove(path.size() - 1);
        cur.pass -= fix;
        return fix;
    }

    private static boolean canWalk(char[][] board, int i, int j) {
        int N = board.length;
        int M = board[0].length;
        if (i < 0 || j < 0 || i == N || j == M) {
            return false;
        }
        return true;
    }

    private static String generatePath(List<Character> path) {
        StringBuilder sb = new StringBuilder();
        for (char ch : path) {
            sb.append(ch);
        }
        return sb.toString();
    }

    private static void fillWord(TrieNode head, String word) {
        head.pass++;
        char[] chs = word.toCharArray();
        TrieNode node = head;
        for (int i = 0; i < chs.length; i++) {
            int index = chs[i];
            if (node.nexts[index] == null) {
                node.nexts[index] = new TrieNode();
            }
            node = node.nexts[index];
            node.pass++;
        }
        node.end = true;
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'t', 'h', 'i', 's', 'i', 's', 'a'},
                {'s', 'i', 'm', 'p', 'l', 'e', 'x'},
                {'b', 'x', 'x', 'x', 'x', 'e', 'b'},
                {'x', 'o', 'g', 'g', 'l', 'x', 'o'},
                {'x', 'x', 'x', 'D', 'T', 'r', 'a'},
                {'R', 'E', 'P', 'E', 'A', 'd', 'x'},
                {'x', 'x', 'x', 'x', 'x', 'x', 'x'},
                {'N', 'O', 'T', 'R', 'E', '-', 'P'},
                {'x', 'x', 'D', 'E', 'T', 'A', 'E'}
        };
        String[] words = new String[]{"this", "is", "not", "a", "simple", "boggle", "board", "test", "REPEATED", "NOTRE-PEATED"};

        List<String> ans = boggleBoard(board, words);
        for (String str : ans) {
            System.out.println(str);
        }

    }
}
