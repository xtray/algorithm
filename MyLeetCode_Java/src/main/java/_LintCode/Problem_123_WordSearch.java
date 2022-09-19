package _LintCode;

public class Problem_123_WordSearch {

    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) {
            return true;
        }
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return false;
        }
        int N = board.length;
        int M = board[0].length;
        boolean res = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                res = process(board, i, j, 0, word.toCharArray());
                if (res) {
                    return true;
                }
            }
        }
        return false;
    }

    // 在board里从(i,j)位置出发, 能否找到str从index出发到结束
    private boolean process(char[][] board, int i, int j, int index, char[] str) {
        int N = board.length;
        int M = board[0].length;
        if (index == str.length) {
            return true;
        }
        if (i < 0 || i >= N || j < 0 || j >= M || board[i][j] != str[index]) {
            return false;
        }
        board[i][j] = 0; // 防止走重复路
        boolean res = process(board, i + 1, j, index + 1, str) ||
                process(board, i - 1, j, index + 1, str) ||
                process(board, i, j + 1, index + 1, str) ||
                process(board, i, j - 1, index + 1, str);
        board[i][j] = str[index];
        return res;
    }


}
