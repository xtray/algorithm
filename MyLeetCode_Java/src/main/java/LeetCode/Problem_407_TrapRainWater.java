package LeetCode;

import java.util.PriorityQueue;

public class Problem_407_TrapRainWater {

    public class Node {
        public int value;
        public int row;
        public int col;

        public Node(int v, int r, int c) {
            value = v;
            row = r;
            col = c;
        }
    }

    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0] == null || heightMap[0].length == 0) {
            return 0;
        }
        int N = heightMap.length;
        int M = heightMap[0].length;
        boolean[][] isEnter = new boolean[N][M];
        int[][] dir = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}}; // 方向向量
        // 小根堆
        PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> a.value - b.value);
        // 先填充周围一圈
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (row == 0 || col == 0 || row == N - 1 || col == M - 1) {
                    isEnter[row][col] = true;
                    heap.add(new Node(heightMap[row][col], row, col));
                }
            }
        }
        int water = 0;
        int max = 0;
        while (!heap.isEmpty()) {
            Node cur = heap.poll();
            max = Math.max(max, cur.value);
            int r = cur.row;
            int c = cur.col;
            for (int i = 0; i < 4; i++) {
                int x = r + dir[i][0];
                int y = c + dir[i][1];
                if (x > 0 && x < N - 1 && y > 0 && y < M - 1 && !isEnter[x][y]) {
                    water += Math.max(0, max - heightMap[x][y]);
                    isEnter[x][y] = true;
                    heap.add(new Node(heightMap[x][y], x, y));
                }
            }
        }
        return water;
    }

    public int trapRainWater2(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0] == null || heightMap[0].length == 0) {
            return 0;
        }
        int N = heightMap.length;
        int M = heightMap[0].length;
        boolean[][] isEnter = new boolean[N][M];
        PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> a.value - b.value);
        for (int col = 0; col < M - 1; col++) {
            isEnter[0][col] = true;
            heap.add(new Node(heightMap[0][col], 0, col));
        }
        for (int row = 0; row < N - 1; row++) {
            isEnter[row][M - 1] = true;
            heap.add(new Node(heightMap[row][M - 1], row, M - 1));
        }
        for (int col = M - 1; col > 0; col--) {
            isEnter[N - 1][col] = true;
            heap.add(new Node(heightMap[N - 1][col], N - 1, col));
        }
        for (int row = N - 1; row > 0; row--) {
            isEnter[row][0] = true;
            heap.add(new Node(heightMap[row][0], row, 0));
        }
        int water = 0;
        int max = 0;
        while (!heap.isEmpty()) {
            Node cur = heap.poll();
            max = Math.max(max, cur.value);
            int r = cur.row;
            int c = cur.col;
            if (r > 0 && !isEnter[r - 1][c]) {
                water += Math.max(0, max - heightMap[r - 1][c]);
                isEnter[r - 1][c] = true;
                heap.add(new Node(heightMap[r - 1][c], r - 1, c));
            }
            if (r < N - 1 && !isEnter[r + 1][c]) {
                water += Math.max(0, max - heightMap[r + 1][c]);
                isEnter[r + 1][c] = true;
                heap.add(new Node(heightMap[r + 1][c], r + 1, c));
            }
            if (c > 0 && !isEnter[r][c - 1]) {
                water += Math.max(0, max - heightMap[r][c - 1]);
                isEnter[r][c - 1] = true;
                heap.add(new Node(heightMap[r][c - 1], r, c - 1));
            }
            if (c < M - 1 && !isEnter[r][c + 1]) {
                water += Math.max(0, max - heightMap[r][c + 1]);
                isEnter[r][c + 1] = true;
                heap.add(new Node(heightMap[r][c + 1], r, c + 1));
            }
        }
        return water;
    }

    public static void main(String[] args) {
        Problem_407_TrapRainWater sl = new Problem_407_TrapRainWater();
//        int[][] heightMap = {{3,3,3,3,3},{3,2,2,2,3},{3,2,1,2,3},{3,2,2,2,3},{3,3,3,3,3}};
        int[][] heightMap = {{1,4,3,1,3,2},{3,2,1,3,2,4},{2,3,3,2,3,1}};
        int ans = sl.trapRainWater(heightMap);
        int ans2 = sl.trapRainWater2(heightMap);
        System.out.println(ans);
        System.out.println(ans2);
    }
}
