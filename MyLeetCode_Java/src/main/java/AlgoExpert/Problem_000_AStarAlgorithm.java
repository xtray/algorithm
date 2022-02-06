package AlgoExpert;

import java.util.*;

// 返回 A*算法的最优行进路径

public class Problem_000_AStarAlgorithm {

    private final int[][] dirs = new int[][]{{0, -1}, {0,1}, {-1,0}, {1,0}};

    // 笨一点的办法
    public int[][] aStarAlgorithm0(int startRow, int startCol, int endRow, int endCol, int[][] graph) {
        // [x,y, distance, manDistance]
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] + o1[3] - o2[2] - o2[3]);
        int N = graph.length;
        int M = graph[0].length;
        boolean[][] used = new boolean[N][M];
        pq.add(new int[]{startRow, startCol, 0, getdis(startRow, startCol, endRow, endCol)});
        Map<Integer, Integer> map = new HashMap<>();
        map.put(startRow*M + startCol, getdis(startRow, startCol, endRow, endCol));
        String[][] path = new String[N][M];
        int step = -1;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int x = cur[0];
            int y = cur[1];
            int dis = cur[2];
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
                int nd = map.getOrDefault(nx*M + ny, Integer.MAX_VALUE);
                if(dis + 1 + getdis(nx, ny, endRow, endCol) < nd) {
                    pq.add(new int[]{nx, ny, dis + 1, getdis(nx, ny, endRow, endCol)});
                    map.put(nx*M + ny, dis + 1 + getdis(nx, ny, endRow, endCol));
                    path[nx][ny] = x + "_" + y;
                }

            }
        }
        System.out.println(step);
        if(step == -1) {
            return new int[][]{};
        }

        List<int[]> ans = new ArrayList<>();
        int x = endRow;
        int y = endCol;
        ans.add(new int[]{x,y});
        while(x != startRow || y != startCol) {
            String[] str = path[x][y].split("_");
            x = Integer.parseInt(str[0]);
            y = Integer.parseInt(str[1]);
            ans.add(0, new int[]{x,y});
        }
        return ans.toArray(new int[0][]);
    }

    // 好一点的 path 记录方法
    public int[][] aStarAlgorithm(int startRow, int startCol, int endRow, int endCol, int[][] graph) {
        // [posOneD, from, distance, manhattanDistance]
        // posOneD :用一维表示的坐标
        // from: 来到当前点的上一个点(用一维表示的坐标)
        // distance: 源点到当前点的距离
        // manhattanDistance: 当前点到目标点的曼哈顿距离
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] + o1[3] - o2[2] - o2[3]);
        int N = graph.length;
        int M = graph[0].length;
        boolean[][] used = new boolean[N][M];
        pq.add(new int[]{startRow * M + startCol,-1, 0, getdis(startRow, startCol, endRow, endCol)});
        int[][] path = new int[N][M]; // 路径表
        int step = -1; // 到目的点走过的步数
        for(int i =0 ;i<N;i++) { // 初始化路径表
            for(int j =0 ;j<M;j++) {
                path[i][j] = -1;
            }
        }
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int x = cur[0] / M;
            int y = cur[0] % M;
            int from = cur[1];
            int dis = cur[2];
            if (used[x][y]) {
                // 重复的, 那些不是足够短的, 第二次弹出的, 忽略
                // 如果是加强堆, 则不会有这些点出现
                continue;
            }
            // 弹出的时候记录路径
            path[x][y] = from;
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
                pq.add(new int[]{nx * M + ny, x * M + y, dis + 1, getdis(nx, ny, endRow, endCol)});
            }
        }
        // System.out.println(step);
        if(step == -1) {
            return new int[][]{};
        }
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
