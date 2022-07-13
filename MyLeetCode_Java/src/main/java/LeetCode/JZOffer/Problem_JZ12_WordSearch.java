package LeetCode.JZOffer;

//https://leetcode-cn.com/problems/word-search/
public class Problem_JZ12_WordSearch {
    public static boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) {
            return true;
        }
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return false;
        }
        int N = board.length;
        int M = board[0].length;
        char[] w = word.toCharArray();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                boolean res = noLoop(board, i, j, w, 0);
                if (res) {
                    return true;
                }
            }
        }
        return false;
    }

    // board 从 i,j 位置出发,能不能走出 word 从 k 出发的后缀
    private static boolean noLoop(char[][] m, int i, int j, char[] str, int k) {
        if (k == str.length) {
            return true;
        }
        if (i < 0 || i == m.length || j < 0 || j == m[0].length || m[i][j] == 0 || m[i][j] != str[k]) {
            return false;
        }
        m[i][j] = 0;
        boolean ans = false;
        if (noLoop(m, i - 1, j, str, k + 1) || noLoop(m, i + 1, j, str, k + 1) || noLoop(m, i, j - 1, str, k + 1) || noLoop(m, i, j + 1, str, k + 1)) {
            ans = true;
        }
        m[i][j] = str[k];
        return ans;
    }

    public static boolean exist1(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return false;
        }
        if (word == null || word.length() == 0) {
            return true;
        }
        int N = board.length;
        int M = board[0].length;
        char[] w = word.toCharArray();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (process(board, i, j, w, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static final int[] dir = new int[]{0, -1, 0, 1, -1, 0, 1, 0};

    // 从i,j位置出发, 能不能走出word里从index往后的
    private static boolean process(char[][] board, int i, int j, char[] word, int index) {
        if (index == word.length) { // word走完了
            return true;
        }
        int N = board.length;
        int M = board[0].length;
        if (i < 0 || i >= N || j < 0 || j >= M) {
            return false;
        }
        if (board[i][j] != word[index]) {
            return false;
        }
        // char tmp = board[i][j];
        board[i][j] = 0; // 为了防止走回头路
        for (int k = 0; k < dir.length; k += 2) {
            int dx = i + dir[k];
            int dy = j + dir[k + 1];
            boolean ans = process(board, dx, dy, word, index + 1);
            if (ans) {
                return true;
            }
        }
        board[i][j] = word[index];
        return false;
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "SEE";
        boolean ans = exist(board, word);
        System.out.println(ans);
    }
}
