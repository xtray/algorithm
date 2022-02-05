package AlgoExpert;

import java.util.*;

public class Problem_000_AStarAlgorithm {

    private final int[][] dirs = new int[][]{{0, -1}, {0,1}, {-1,0}, {1,0}};

    public int[][] aStarAlgorithm(int startRow, int startCol, int endRow, int endCol, int[][] graph) {
        // [posOneD, distance, manDistance]
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] + o1[2] - o2[1] - o2[2]);
        int N = graph.length;
        int M = graph[0].length;
        boolean[][] used = new boolean[N][M];
        pq.add(new int[]{startRow * M + startCol, 0, getdis(startRow, startCol, endRow, endCol)});
        int[][] path = new int[N][M];
        int step = 0;
        for(int i =0 ;i<N;i++) {
            for(int j =0 ;j<M;j++) {
                path[i][j] = -1;
            }
        }
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int x = cur[0] / M;
            int y = cur[0] % M;
            int dis = cur[1];
            if (used[x][y]) {
                continue;
            }
            used[x][y] = true;
            if (x == endRow && y == endCol) {
                step = dis;
                break;
            }
            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M || used[nx][ny] || graph[nx][ny] == 1) {
                    continue;
                };
                pq.add(new int[]{nx * M + ny, dis + 1, getdis(nx, ny, endRow, endCol)});
                path[nx][ny] = x*M+y;
            }
        }
        System.out.println(step);

        List<int[]> ans = new ArrayList<>();
        int x = endRow;
        int y = endCol;
        ans.add(new int[]{x,y});
        while(x != startRow || y != startCol) {
            int pos = path[x][y];
            x = pos/M;
            y = pos%M;
            ans.add(0, new int[]{x,y});
        }
        return ans.toArray(new int[0][]);
    }

    // 计算两个点的曼哈顿距离
    private int getdis(int startRow, int startCol, int endRow, int endCol) {
        return Math.abs(endRow - startRow) + Math.abs(endCol - startCol);
    }

    public static void main(String[] args) {
        int startRow = 1;
        int startCol = 8;
        int endRow = 8;
        int endCol = 1;
        int[][] graph =
                new int[][]{
                        {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                        {0, 0, 1, 0, 1, 0, 0, 0, 0, 0},
                        {0, 0, 1, 0, 1, 1, 1, 0, 0, 0},
                        {0, 0, 1, 0, 0, 0, 1, 0, 0, 0},
                        {0, 0, 1, 1, 1, 0, 1, 0, 0, 0},
                        {0, 0, 1, 0, 1, 0, 1, 0, 0, 0},
                        {0, 0, 1, 0, 1, 0, 1, 0, 0, 0},
                        {0, 0, 1, 0, 1, 0, 1, 0, 0, 0},
                        {0, 0, 1, 0, 1, 0, 1, 1, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
                };
        // int startRow = 0;
        // int startCol = 1;
        // int endRow = 4;
        // int endCol = 3;
        // int[][] graph =
        //         new int[][]{
        //                 {0, 0, 0, 0, 0},
        //                 {0, 1, 1, 1, 0},
        //                 {0, 0, 0, 0, 0},
        //                 {1, 0, 1, 1, 1},
        //                 {0, 0, 0, 0, 0}
        //         };
        // int startRow = 0;
        // int startCol = 3;
        // int endRow = 3;
        // int endCol = 0;
        // int[][] graph =
        //         new int[][]{
        //                 {0, 1, 0, 0},
        //                 {0, 1, 0, 0},
        //                 {0, 1, 0, 0},
        //                 {0, 1, 0, 0},
        //                 {1, 0, 0, 0}
        //         };
        var ans = new Problem_000_AStarAlgorithm().aStarAlgorithm(startRow, startCol, endRow, endCol, graph);
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i][0] + " " + ans[i][1]);
        }
        System.out.println();
    }
}
