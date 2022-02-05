package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

public class Problem_1765_HigestPeak {


    // 多源 BFS
    public int[][] highestPeak(int[][] isWater) {
        if (isWater == null || isWater.length == 0 || isWater[0] == null || isWater[0].length == 0) {
            return isWater;
        }

        int N = isWater.length;
        int M = isWater[0].length;
        int[][] ans = new int[N][M];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(isWater[i][j] == 1) {
                    queue.add(new int[]{i, j});
                }
                ans[i][j] = isWater[i][j] == 1 ? 0 : -1;
            }
        }
        int[][] dirs = new int[][]{{0,-1},{0,1},{-1,0},{1,0}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            for(int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if(nx <0 || nx >= N || ny<0 || ny >= M) continue;
                if(ans[nx][ny] != -1) continue; // 没有填过的
                ans[nx][ny] = ans[x][y] + 1;
                queue.add(new int[]{nx, ny});
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[][] water = new int[][]{{0,0,1},{1,0,0},{0,0,0}};
        var ans = new Problem_1765_HigestPeak().highestPeak(water);
        for(int [] row : ans) {
            for(int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
