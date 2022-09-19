package LeetCode;

import java.util.*;

public class Problem_782_Chessboard {




    // 第一个判断无解的条件：若起始棋盘的行/列种类数不为2，必然无法构造出合法棋盘。
    // 第二性质：若能构成合法棋盘，r1 和 r2 中0 和1 的数量必然相等，c1 和 c2 中的0 和 1 的数量必然相等。
    // 第二性质可拓展为：r1 和 r2 对称位置为必然不同，c1 和 c2 对称位置必然不同，
    // 即两者异或结果为必然为(111...111)，即为 mask = (1 << n) - 1，否则必然无解。
    private static final int INF = 0x3f3f3f3f;

    public int movesToChessboard(int[][] grid) {
        int N = grid.length;
        int r1 = -1, r2 = -1;
        int c1 = -1, c2 = -1;
        int mask1 = (1 << N) - 1; // N个1, 标准棋盘状态
        for (int i = 0; i < N; i++) {
            int rowNum = 0;
            int colNum = 0;
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1) rowNum |= (1 << j);
                if (grid[j][i] == 1) colNum |= (1 << j);
            }
            // 提取至多两个种类的数字
            if (r1 == -1) {
                r1 = rowNum;
            } else if (r2 == -1 && rowNum != r1) {
                r2 = rowNum;
            }
            if (c1 == -1) {
                c1 = colNum;
            } else if (c2 == -1 && colNum != c1) {
                c2 = colNum;
            }
            // 如果超过两个种类, 不合格
            if (rowNum != r1 && rowNum != r2) return -1;
            if (colNum != c1 && colNum != c2) return -1;
        }
        // if (Integer.bitCount(r1) + Integer.bitCount(r2) != N) return -1;
        // if (Integer.bitCount(c1) + Integer.bitCount(c2) != N) return -1;
        if ((r1 ^ r2) != mask1 || (c1 ^ c2) != mask1) return -1;
        int mask2 = 0; // 标准棋盘的状态
        for (int i = 0; i < N; i += 2) {
            mask2 += (1 << i);
        }
        int ans = Math.min(getCnt(r1, mask2), getCnt(r2, mask2)) + Math.min(getCnt(c1, mask2), getCnt(c2, mask2));
        return ans >= INF ? -1 : ans;
    }

    private int getCnt(int a, int b) {
        return Integer.bitCount(a) != Integer.bitCount(b) ? INF : Integer.bitCount(a ^ b) / 2;
    }

    // 因为只能行列变换，所以棋盘上所有行的种类只会有2种，而且这2种是完全相反的，也就是说只要计算一行就可以了。
    // 而列也同理，因此可以把棋盘简化为一行一列，只用在一行一列内操作就可以了。
    // 2 <= n <= 30
    public int movesToChessboard1(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;
        // 行的种类最多有两种
        // 每一行可以加工为一个数字
        int[] row = new int[N];
        int[] col = new int[M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                row[i] |= grid[i][j] != 0 ? (1 << j) : 0; // 第i行
                col[j] |= grid[i][j] != 0 ? (1 << i) : 0; // 第j列
            }
        }
        Map<Integer, Integer> rowMap = new HashMap<>();
        Map<Integer, Integer> colMap = new HashMap<>();

        for (int num : row) {
            rowMap.put(num, rowMap.getOrDefault(num, 0) + 1);
        }
        for (int num : col) {
            colMap.put(num, colMap.getOrDefault(num, 0) + 1);
        }
        if (rowMap.size() != 2 || colMap.size() != 2) {
            return -1;
        }
        int mask1 = 0; // 标准棋盘的状态
        for (int i = 0; i < N; i += 2) {
            mask1 |= (1 << i);
        }
        // 要求行, 列 0, 1的个数要么相等, 要么相差1
        int r1 = -1, r2 = -1;
        int c1 = -1, c2 = -1;
        int p1 = Integer.MAX_VALUE;
        for (int num : rowMap.keySet()) {
            if (r1 == -1) {
                r1 = num;
            } else if (r2 == -1) {
                r2 = num;
            }
            p1 = Math.min(p1, getMove(mask1, num));
        }
        int mask2 = (1 << N) - 1;
        if (p1 == Integer.MAX_VALUE || (r1 ^ r2) != mask2) {
            return -1;
        }
        int p2 = Integer.MAX_VALUE;
        for (int num : colMap.keySet()) {
            if (c1 == -1) {
                c1 = num;
            } else if (c2 == -1) {
                c2 = num;
            }
            p2 = Math.min(p2, getMove(mask1, num));
        }
        if (p2 == Integer.MAX_VALUE || (c1 ^ c2) != mask2) {
            return -1;
        }
        return p1 + p2;
    }

    private int getMove(int a, int b) {
        return Integer.bitCount(a) != Integer.bitCount(b) ? Integer.MAX_VALUE : Integer.bitCount(a ^ b) / 2;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 1},
                {1, 1}
        };
        var ans = new Problem_782_Chessboard().movesToChessboard1(grid);
        System.out.println(ans);
    }
}
