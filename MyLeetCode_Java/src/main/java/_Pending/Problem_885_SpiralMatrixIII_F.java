package _Pending;

/**
 * Not Pass!
 * DayQuestions
 */

public class Problem_885_SpiralMatrixIII_F {

    private int R;
    private int C;
    private int idx;

    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {

        R = rows;
        C = cols;
        idx = 0;
        int[][] ans = new int[rows*cols][2];

        // 1. 先定下最大旋转半径, 是上下左右里边界的最大格子距离

        // 2. 按最大半径控制循环
        // 2.1 每一个小圈 S1 (x,y) --> E1 (x-1, y)
        //              S2 (x-1,y) --> E2(x-2, y)

        // 3. 一圈怎么打, 按半径旋转
        // 难点: 怎么判断碰到的格子在矩阵里, 单独写一个函数判断
        int radius = 0;
        radius = Math.max(cols - 1 - cStart, cStart);
        radius = Math.max(radius, Math.max(rows - 1 - rStart, rStart));
        int x1 = rStart;
        int y1 = cStart;
        for (int i = 1; i <= radius; i++) {
            oneLoop(x1, y1, i, ans);
            x1 -= 1;
        }
        return ans;
    }

    // 从x1,y1 开始打印一圈到 x2, y2
    private void oneLoop(int x1, int y1, int round, int[][] ans) {
        int nx = x1;
        int ny = y1;
        // 先往右
        while (ny < y1 + round) {
            checkAndSave(nx,ny, ans);
            ny++;
        }
        // 往下
        while (nx < x1 + round) {
            checkAndSave(nx,ny, ans);
            nx++;
        }
        // 往左
        while (ny > y1 - round) {
            checkAndSave(nx,ny, ans);
            ny--;
        }
        // 往上
        while (nx > x1 - round) {
            checkAndSave(nx,ny, ans);
            nx--;
        }
        // 往右
        while (ny < y1) {
            checkAndSave(nx,ny, ans);
            ny++;
        }
    }

    private void checkAndSave(int nx, int ny, int[][] ans) {
        if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
            ans[idx++] = new int[]{nx,ny};
        };
    }

    public static void main(String[] args) {
        // int rows = 1;
        // int cols =4 ;
        // int rStart = 0;
        // int cStart = 0;
        int rows = 5;
        int cols = 6;
        int rStart = 1;
        int cStart = 4;
        var ans = new Problem_885_SpiralMatrixIII_F().spiralMatrixIII(rows, cols, rStart, cStart);
        for (int[] item : ans) {
            System.out.print("[" + item[0] + "," + item[1] + "]");
        }
        System.out.println();
    }
}
