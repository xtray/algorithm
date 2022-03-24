package LeetCode;

public class Problem_661_ImageSmoother {
    public int[][] imageSmoother(int[][] img) {
        if (img == null || img.length == 0 || img[0] == null || img[0].length == 0) {
            return img;
        }

        // sum[i][j] : 0,0 ~ i,j的累加和
        // x,y -- > x+1, y+1
        // sum[x+1][y+1] - sum[x-2][y+1] - sum[x+1][y-2] + sum[x-2][y-2]
        int N = img.length;
        int M = img[0].length;
        int[][] sum = new int[N + 2][M + 2];
        for (int j = 0; j < M; j++) { // [0,j] --> [1,j+1]
            sum[1][j + 1] = img[0][j] + sum[1][j];
        }
        sum[1][M + 1] = sum[1][M];
        for (int i = 1; i < N; i++) { // [i, 0] --> [i+1,1]
            sum[i + 1][1] = img[i][0] + sum[i][1];
        }
        sum[N + 1][1] = sum[N][1];
        for (int i = 1; i <= N + 1; i++) {
            for (int j = 1; j <= M + 1; j++) {
                sum[i][j] =
                        ((i <= N && j <= M) ? img[i - 1][j - 1] : 0) +
                                sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                img[i][j] = getVal(sum, i + 1, j + 1);
            }
        }
        return img;
    }

    private int getVal(int[][] sum, int x, int y) {
        int N = sum.length;
        int M = sum[0].length;
        int x1 = Math.max(1, x - 1);
        int y1 = Math.max(1, y - 1);
        int x2 = Math.min(N - 2, x + 1);
        int y2 = Math.min(M - 2, y + 1);
        int cnt = (x2 - x1 + 1) * (y2 - y1 + 1);
        int sumAll = sum[x + 1][y + 1] -
                (x >= 2 ? sum[x - 2][y + 1] : 0) -
                (y >= 2 ? sum[x + 1][y - 2] : 0) +
                ((x >= 2 && y >= 2) ? sum[x - 2][y - 2] : 0);
        return sumAll / cnt;
    }

    public static void main(String[] args) {
        // int[][] img = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        // int[][] img = {{100, 200, 100}, {200, 50, 200}, {100, 200, 100}};
        int[][] img = {{1}};
        var ans = new Problem_661_ImageSmoother().imageSmoother(img);
        for (int[] an : ans) {
            for (int a : an) {
                System.out.print(a + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
