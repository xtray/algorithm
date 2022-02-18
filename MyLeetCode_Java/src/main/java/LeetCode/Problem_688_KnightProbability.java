package LeetCode;

public class Problem_688_KnightProbability {

    private static final int[][] dirs = new int[][]{{-2,-1}, {-2,1}, {2,-1},{2,1},{-1,-2},{1,-2},{-1,2},{1,2}};

    // TLE
    public double knightProbability(int n, int k, int row, int column) {
        if (n <= 0 || k < 0) {
            return 0;
        }
        return (double) process(n, row, column, k) / Math.pow(8, k);
    }

    // 目前在 row, col 位置, 还有 rest 步可走, 走完了还在格子中获得 1 个生存点
    // 返回总的生存点数
    private long process(int n, int row, int column, int rest) {
        if (row < 0 || row >= n || column < 0 || column >= n) {
            return 0;
        }
        // 还在网格中
        if (rest == 0) {
            return 1;
        }
        int step = 0;
        for(int[] dir : dirs) {
            step+=process(n, row+dir[0], column+dir[1], rest-1);
        }
        return step;
    }

    // 改动态规划
    public double knightProbability2(int n, int k, int row, int column) {
        if (n <= 0 || k < 0) {
            return 0;
        }
        double[][][] dp = new double[n][n][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][0] = 1;
            }
        }
        for (int rest = 1; rest <= k; rest++) {
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    for(int[] dir : dirs) {
                        dp[r][c][rest]+=pick(dp, n, r+dir[0], c+dir[1], rest-1);
                    }
                }
            }
        }
        return dp[row][column][k] / Math.pow(8, k);
    }

    private double pick(double[][][] dp, int n, int row, int col, int rest) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            return 0.0;
        }
        return dp[row][col][rest];
    }

    public double knightProbability3(int n, int k, int row, int column) {
        if (n <= 0 || k < 0) {
            return 0;
        }
        double[][][] dp = new double[n][n][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][0] = 1;
            }
        }
        for (int rest = 1; rest <= k; rest++) {
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    for(int[] dir : dirs) {
                        dp[r][c][rest]+=pick3(dp, n, r+dir[0], c+dir[1], rest-1)/8.0;
                    }
                }
            }
        }
        return dp[row][column][k];
    }

    private double pick3(double[][][] dp, int n, int row, int col, int rest) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            return 0.0;
        }
        return dp[row][col][rest];
    }


    // by zuo
    public static double knightProbability4(int n, int k, int row, int col) {
        double[][][] dp = new double[n][n][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int t = 0; t <= k; t++) {
                    dp[i][j][t] = -1;
                }
            }
        }
        return process4(row, col, n, k, dp);
    }

    public static int[] next = { -1, -2, -2, -1, -2, 1, -1, 2, 1, -2, 2, -1, 2, 1, 1, 2 };

    // 这题，数量量大，就不收集点数了, 收集概率呗
    // 然后每个分支占1/8权重,调整一下都累加
    public static double process4(int row, int col, int n, int k, double[][][] dp) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            return 0;
        }
        if (dp[row][col][k] != -1) {
            return dp[row][col][k];
        }
        double ans = 0;
        if (k == 0) {
            ans = 1;
        } else {
            for (int i = 0; i < 16; i += 2) {
                ans += process4(row + next[i], col + next[i + 1], n, k - 1, dp) / 8;
            }
        }
        dp[row][col][k] = ans;
        return ans;
    }


    public static void main(String[] args) {
        // int n = 3, k =2, row = 0, col = 0;
        // int n = 1, k =0, row = 0, col = 0;
        int n = 8, k =30, row = 6, col = 4;
        // var ans = new Problem_688_KnightProbability().knightProbability(n,k,row, col);
        // System.out.println(ans);
        var ans2 = new Problem_688_KnightProbability().knightProbability2(n,k,row, col);
        System.out.println(ans2);
    }

}
