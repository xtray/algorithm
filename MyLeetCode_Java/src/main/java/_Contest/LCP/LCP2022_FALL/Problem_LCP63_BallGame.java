package _Contest.LCP.LCP2022_FALL;

import java.util.*;

public class Problem_LCP63_BallGame {

    public class Node {
        public int idx; // 标识
        public int x;
        public int y;
        public int dir; // dirs数组表示
        public int rest;

        public Node(int idx, int x, int y, int dir, int rest) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.rest = rest;
        }
    }

    //                              右(0)  下(1)  左(2)    上(3)
    private static int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 顺时针

    public int[][] ballGame(int num, String[] plate) {
        int N = plate.length;
        int M = plate[0].length();

        char[][] grid = new char[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                grid[i][j] = plate[i].charAt(j);
            }
        }

        Set<String> set = new HashSet<>();
        ArrayDeque<Node> queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i == 0 || i == N - 1) {
                    // 空白区域 处（弹珠盘的四角除外）
                    if (j != 0 && j != M - 1 && grid[i][j] == '.') {
                        // 右(0)  下(1)  左(2)    上(3)
                        int dir = i == 0 ? 1 : 3;
                        queue.add(new Node(i * M + j, i, j, dir, num));
                        int idx = i * M + j;
                        set.add(idx + "_" + dir);
                    }
                }
                if (j == 0 || j == M - 1) {
                    if (i != 0 && i != N - 1 && grid[i][j] == '.') {
                        int dir = j == 0 ? 0 : 2;
                        queue.add(new Node(i * M + j, i, j, dir, num));
                        int idx = i * M + j;
                        set.add(idx + "_" + dir);
                    }
                }
            }
        }

        List<int[]> list = new ArrayList<>();

        while (!queue.isEmpty()) {
            Node curNode = queue.pollFirst();
            int orgIdx = curNode.idx;
            int x = curNode.x;
            int y = curNode.y;
            int d = curNode.dir;
            int rest = curNode.rest;
            if (grid[x][y] == 'O') {
                list.add(new int[]{orgIdx / M, orgIdx % M});
                continue;
            }
            if (rest == 0) continue;
            // 右(0)  下(1)  左(2)    上(3)
            if (grid[x][y] == 'W') d = (d + 3) % 4; // 逆时针
            if (grid[x][y] == 'E') d = (d + 1) % 4; // 顺时针
            x += dirs[d][0];
            y += dirs[d][1];
            int idx = x * M + y;
            if (x < 0 || x >= N || y < 0 || y >= M || set.contains(idx + "_" + d)) continue;
            set.add(idx + "_" + d);
            queue.add(new Node(orgIdx, x, y, d, rest - 1));
        }

        int[][] ans = new int[list.size()][2];
        int idx = 0;
        for (int[] p : list) {
            ans[idx][0] = p[0];
            ans[idx++][1] = p[1];
        }

        return ans;

    }

    public static void main(String[] args) {
        // int num = 4;
        // String[] plate = {"..E.", ".EOW", "..W."};

        int num = 3;
        String[] plate = {".....","....O","....O","....."};

        var ans = new Problem_LCP63_BallGame().ballGame(num, plate);
        System.out.println(Arrays.deepToString(ans));
    }


}
