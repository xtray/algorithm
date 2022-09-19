package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_1260_Shift2DGrid {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        int N = grid.length;
        int M = grid[0].length;
        for (int i = 0; i < N; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < M; j++) {
                list.add(0);
            }
            ans.add(list);
        }
        k %= M * N;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int pos1 = i * M + j + k;
                pos1 %= M * N;
                int nr = pos1 / M;
                int nc = pos1 % M;
                ans.get(nr).set(nc, grid[i][j]);
            }
        }
        return ans;
    }
}
