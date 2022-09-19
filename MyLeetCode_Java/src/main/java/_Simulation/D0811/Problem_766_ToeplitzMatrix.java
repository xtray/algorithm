package _Simulation.D0811;

public class Problem_766_ToeplitzMatrix {

    // NOTE: 要走一个连续的直角边
    // 规定出发点的轨迹, 从每一个出发点斜向下遍历检查
    public boolean isToeplitzMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        int N = matrix.length;
        int M = matrix[0].length;
        int startx = N - 1;
        int starty = 0;
        while (startx >= 0 && starty < M) {
            int i = startx;
            int j = starty;
            int pre = matrix[i][j]; // 出发点
            while (i < N && j < M) {
                int cur = matrix[i][j];
                if (cur != pre) {
                    return false;
                }
                i++;
                j++;
            }
            starty = startx == 0 ? starty + 1 : 0;
            startx = startx != 0 ? startx - 1 : 0;
        }
        return true;
    }

    // 检测: 每一个元素和它左上角的元素相比对即可
    public boolean isToeplitzMatrix1(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        int N = matrix.length;
        int M = matrix[0].length;
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // int[][] matrix = {
        //         {1, 2},
        //         {2, 2}}; // false
        // int[][] matrix = {
        //         {11, 74, 0, 93},
        //         {40, 11, 74, 7}}; // false
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 1, 2, 3},
                {9, 5, 1, 2}
        }; // true
        var ans = new Problem_766_ToeplitzMatrix().isToeplitzMatrix(matrix);
        System.out.println(ans);
    }
}
