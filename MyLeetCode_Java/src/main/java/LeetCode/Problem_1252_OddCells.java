package LeetCode;

public class Problem_1252_OddCells {

    public int oddCells(int m, int n, int[][] indices) {
        int[] rows = new int[m];
        int[] cols = new int[n];
        for (int[] indice : indices) {
            int r = indice[0];
            int c = indice[1];
            rows[r]++;
            cols[c]++;
        }
        int ans = 0;
        // 1. 行偶数 + 列奇数
        // 2. 行奇数数 + 列偶数
        int rowEven = 0;
        for (int i = 0; i < m; i++) {
            if ((rows[i] & 1) == 0) {
                rowEven++;
            }
        }
        int rowOdd = m - rowEven;
        int colEven = 0;
        for (int i = 0; i < n; i++) {
            if ((cols[i] & 1) == 0) {
                colEven++;
            }
        }
        int colOdd = n - colEven;
        ans = rowEven * colOdd;
        ans += rowOdd * colEven;
        return ans;
    }

    public static void main(String[] args) {
        int m = 2, n = 3;
        int[][] indices = {{0, 1}, {1, 1}};
        var ans = new Problem_1252_OddCells().oddCells(m, n, indices);
        System.out.println(ans);
    }
}
