package LeetCode;

// https://leetcode-cn.com/problems/valid-tic-tac-toe-state/solution/c-jian-ji-qing-xi-mo-ni-by-zeroac-b8cs/
public class Problem_794_ValidTicTacToe {
    public boolean validTicTacToe(String[] board) {
        if (board == null || board.length == 0) {
            return false;
        }
        // 验证规则:
        // 只有 1 个, 必须是 X
        // X 的数量==O 的数量, 或者 X的数量 = O 的数量+1
        // 如果模拟放置过程中碰到
        // 1. X,O 行列斜线赢的状况后还有剩余棋子 false
        // 统计 X, O 的个数
        return checkWin(board);
    }


    private boolean checkWin(String[] board) {
        int[] row = new int[3];
        int[] col = new int[3];
        int[] cross = new int[2];

        boolean owin = false;
        boolean xwin = false;
        int xcount = 0;
        int ocount = 0;

        // x -->1, o --> -1
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i].charAt(j) == 'X') {
                    row[i]++;
                    col[j]++;
                    xcount++;
                }
                if (row[i] == 3 || col[j] == 3) {
                    xwin = true;
                }
                if (board[i].charAt(j) == 'O') {
                    row[i]--;
                    col[j]--;
                    ocount++;
                }
                if (row[i] == -3 || col[j] == -3) {
                    owin = true;
                }
                if (i == j) {
                    if (board[i].charAt(j) == 'X') {
                        cross[0]++;
                    }
                    if (board[i].charAt(j) == 'O') {
                        cross[0]--;
                    }
                    if (cross[0] == 3) {
                        xwin = true;
                    }
                    if (cross[0] == -3) {
                        owin = true;
                    }
                }
                if (i + j == 2) {
                    if (board[i].charAt(j) == 'X') {
                        cross[1]++;
                    }
                    if (board[i].charAt(j) == 'O') {
                        cross[1]--;
                    }
                    if (cross[1] == 3) {
                        xwin = true;
                    }
                    if (cross[1] == -3) {
                        owin = true;
                    }
                }
            }
        }
        if (xwin && owin) { // 两人不可能同时赢
            return false;
        }
        if (xwin && xcount != ocount + 1) { // x赢, 则 x 棋子比 o 多一个
            return false;
        }
        if (owin && xcount != ocount) { // o 赢, 两者棋子一样多
            return false;
        }
        // 没有赢的 x 棋子跟 o 一样多, 或者多一个
        if (!owin && !xwin && xcount != ocount && xcount != ocount + 1) {
            return false;
        }
        return true;
    }
}
