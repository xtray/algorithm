package LeetCode.Jianzhi;

import java.util.HashSet;
import java.util.Set;

public class Problem_JZ13_MovingCount {
    public int movingCount(int m, int n, int k) {
        if (m == 0 || n == 0) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }
        Set<Integer> visted = new HashSet<>();
        return process(m, n, 0, 0, k, visted);
    }

    private static final int[] dir = new int[]{0, -1, 0, 1, 0};

    // 从(i,j)位置出发能到达的格子数
    private int process(int m, int n, int i, int j, int k, Set<Integer> visted) {
        if (i < 0 || i >= m || j < 0 || j >= n || getSum(i, j) > k || visted.contains(i * n + j)) {
            return 0;
        }
        int step = 1;
        visted.add(i * n + j);
        for (int d = 1; d < dir.length; d++) {
            int nx = i + dir[d - 1];
            int ny = j + dir[d];
            step += process(m, n, nx, ny, k, visted);
        }
        return step;
    }

    private int getSum(int i, int j) {
        int sum = 0;
        while (i != 0) {
            sum += i % 10;
            i /= 10;
        }
        while (j != 0) {
            sum += j % 10;
            j /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        int m = 2, n = 3, k = 1;
        var ans = new Problem_JZ13_MovingCount().movingCount(m, n, k);
        System.out.println(ans);
    }
}
