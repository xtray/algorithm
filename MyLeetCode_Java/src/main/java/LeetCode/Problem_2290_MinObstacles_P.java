package LeetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

// PENDING

public class Problem_2290_MinObstacles_P {


    public int minimumObstacles(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;
        return process(grid, 0, 0);
    }

    private static final int[] dir = new int[]{0, -1, 0, 1, -1, 0, 1, 0};

    // 从[i][j] 到 t
    // 碰到障碍, 算移除
    // 不走回头路
    private int process(int[][] grid, int i, int j) {
        int N = grid.length;
        int M = grid[0].length;
        if (i == N - 1 && j == M - 1) {
            return 0;
        }
        if (i < 0 || i >= N || j < 0 || j >= M || grid[i][j] == 2) {
            return Integer.MAX_VALUE;
        }
        int p1 = Integer.MAX_VALUE;
        int p2 = Integer.MAX_VALUE;
        if (grid[i][j] == 0) {
            for (int d = 0; d < dir.length; d += 2) {
                int nx = i + dir[d];
                int ny = j + dir[d + 1];
                grid[i][j] = 2;
                int next = process(grid, nx, ny);
                grid[i][j] = 0;
                if (next != Integer.MAX_VALUE) {
                    p1 = Math.min(p1, next);
                }
            }
        }
        if (grid[i][j] == 1) {
            for (int d = 0; d < dir.length; d += 2) {
                int nx = i + dir[d];
                int ny = j + dir[d + 1];
                grid[i][j] = 2;
                int next = process(grid, nx, ny);
                grid[i][j] = 1;
                if (next != Integer.MAX_VALUE) {
                    p2 = Math.min(p2, 1 + next);
                }
            }
        }
        return Math.min(p1, p2);
    }

    public static class Node {
        public int row;
        public int col;

        public Node(int r, int c) {
            row = r;
            col = c;
        }
    }

    public static class NodeRecord {
        public Node node;
        public int distance; // 从原始点到当前点的距离

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public int minimumObstacles2(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;

        HashMap<Node, Integer> distanceMap = new HashMap<>(); // 距离表
        PriorityQueue<NodeRecord> pq = new PriorityQueue<>((o1, o2) -> o1.distance - o2.distance);
        Set<Node> used = new HashSet<>();// 打过对号的 锁定的点
        Node from = new Node(0,0);
        pq.add(new NodeRecord(from, 0));
        while (!pq.isEmpty()) {
            NodeRecord record = pq.poll();
            Node cur = record.node;
            int i = cur.row;
            int j = cur.col;
            int distance = record.distance;
            if (i < 0 || i >= N || j < 0 || j >= M || used.contains(cur)) {
                continue;
            }
            used.add(cur);
            if(grid[i][j] == 1) {
                distance++;
            }
            for (int d = 0; d < dir.length; d += 2) {
                int nx = i + dir[d];
                int ny = j + dir[d + 1];
                pq.add(new NodeRecord(new Node(nx, ny), distance));
            }
            distanceMap.put(cur, distance);
        }
        return distanceMap.get(from);
    }



}
