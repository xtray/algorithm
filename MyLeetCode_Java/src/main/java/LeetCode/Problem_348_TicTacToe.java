package LeetCode;

public class Problem_348_TicTacToe {

    class TicTacToe {

        public int[][] grid;
        public int n;

        public TicTacToe(int n) {
            this.n = n;
            grid = new int[n][n];
        }

        public int move(int row, int col, int player) {
            grid[row][col] = player;
            if (check(player)) {
                return player;
            }
            return 0;
        }

        private boolean check(int player) {
            int[] sumCol = new int[n];
            int[] sumRow = new int[n];
            int x1 = 0;
            int x2 = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    sumRow[i] += grid[i][j] == player ? 1 : 0;
                    sumCol[i] += grid[j][i] == player ? 1 : 0;
                    if (i == j) x1 += grid[i][j] == player ? 1 : 0;
                    if (i + j == n - 1) x2 += grid[i][j] == player ? 1 : 0;
                }
                if (sumRow[i] == n || sumCol[i] == n) {
                    return true;
                }
            }
            if (x1 == n || x2 == n) return true;
            return false;
        }
    }
}
