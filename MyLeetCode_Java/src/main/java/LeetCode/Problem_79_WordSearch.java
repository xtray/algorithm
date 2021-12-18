package LeetCode;

//https://leetcode-cn.com/problems/word-search/
public class Problem_79_WordSearch {
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
        boolean ans =false;
        if (noLoop(m, i - 1, j, str, k + 1) || noLoop(m, i + 1, j, str, k + 1) || noLoop(m, i, j - 1, str, k + 1) || noLoop(m, i, j + 1, str, k + 1)) {
            ans = true;
        }
        m[i][j] = str[k];
        return ans;
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "SEE";
        boolean ans = exist(board, word);
        System.out.println(ans);
    }
}
