package BigComQues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * m*n的迷宫。地形元素有起点、终点、地雷、墙、陷阱、路（都由不同数字代表），
 * 人能上下左右移动，墙不能通过，路走一步要花费1个单位时间，
 * 走到陷阱要花3个单位时间，走到炸弹会将上下左右的墙炸成路(只能选择一个炸弹爆炸)
 * 要求输出到达终点的最短时间
 */
public class Problem_000_BombDistance {

    private static final int[] dirs = {0, -1, 0, 1, 0};
    private int N;
    private int M;
    private int[][] grid;

    // 起点、终点、地雷、墙、陷阱、路
    // 0     1    2    3   4    5
    public int getMinTime(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;
        this.N = N;
        this.M = M;
        this.grid = grid;

        // distance[0][i] -> 0 : 前一个位置到达i, 没有使用炸弹最小代价
        // distance[1][i] -> 1 : 前一个位置到达i, 使用炸弹最小代价, 第一次使用炸弹以后, 后面都是使用炸弹
        int[][] distance = new int[2][N * M]; // 二维转一维
        Arrays.fill(distance[0], Integer.MAX_VALUE);
        Arrays.fill(distance[1], Integer.MAX_VALUE);
        distance[0][0] = 0;
        distance[1][0] = 0;
        // 小根堆, 根据耗费时间排序, 时间小的在上面
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        pq.add(new Node(0, 0, 0));
        boolean[][] visited = new boolean[2][N * M];
        List<Integer> minList = new ArrayList<>();
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (visited[cur.bomb][cur.pos]) {
                continue;
            }
            visited[cur.bomb][cur.pos] = true;
            // 不是用炸弹
            int x = cur.pos / M;
            int y = cur.pos % M;
            // 如果是终点收集答案
            // if (grid[x][y] == 1) {
            //     minList.add(cur.cost);
            //     if (minList.size() == 2) break;
            // }

            if (cur.bomb == 1) { // 已经用过炸弹了
                // 不能用炸弹, 再次遇到炸弹要跳过
                for (int d = 1; d < dirs.length; d++) {
                    int nx = x + dirs[d - 1];
                    int ny = y + dirs[d];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                    // 地雷、墙、陷阱、路
                    // 2    3   4    5
                    if (grid[x][y] == 2 || grid[x][y] == 3) continue;
                    int next = nx * M + ny;
                    if (grid[nx][ny] == 5 && distance[1][next] > cur.cost + 1) { // 走路
                        distance[0][next] = cur.cost + 1;
                        pq.add(new Node(0, next, distance[0][next]));
                        continue;
                    }
                    if (grid[nx][ny] == 4 && distance[1][next] > cur.cost + 3) { // 跳陷阱
                        distance[0][next] = cur.cost + 3;
                        pq.add(new Node(0, next, distance[0][next]));
                    }
                }
            } else {
                // 当前站在炸弹上, 可以选择引爆走下级路, 或者不引爆(死路)
                if (grid[x][y] == 2) {
                    // 只能引爆, 用炸弹
                    for (int d = 1; d < dirs.length; d++) {
                        int nx = x + dirs[d - 1];
                        int ny = y + dirs[d];
                        if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                        // 地雷、墙、陷阱、路
                        // 2    3   4    5
                        if (grid[x][y] == 2) continue; // 引爆以后,不能再处理炸弹了
                        int next = nx * M + ny;
                        // 引爆可以选择是墙的入队列
                        if ((grid[nx][ny] == 3 || grid[nx][ny] == 5) && distance[1][next] > cur.cost + 1) { // 走路
                            distance[0][next] = cur.cost + 1;
                            pq.add(new Node(1, next, distance[0][next]));
                            continue;
                        }
                        if (grid[nx][ny] == 4 && distance[1][next] > cur.cost + 3) { // 跳陷阱
                            distance[0][next] = cur.cost + 3;
                            pq.add(new Node(0, next, distance[0][next]));
                        }
                    }
                } else { // 当前不是炸弹, 可以走 炸弹, 陷阱, 路
                    for (int d = 1; d < dirs.length; d++) {
                        int nx = x + dirs[d - 1];
                        int ny = y + dirs[d];
                        if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                        // 地雷、墙、陷阱、路
                        // 2    3   4    5
                        int next = nx * M + ny;
                        // 没引爆过炸弹, 可以走上炸弹(后面引爆)
                        if ((grid[nx][ny] == 2 || grid[nx][ny] == 5) && distance[1][next] > cur.cost + 1) { // 走路
                            distance[0][next] = cur.cost + 1;
                            pq.add(new Node(1, next, distance[0][next]));
                            continue;
                        }
                        if (grid[nx][ny] == 4 && distance[1][next] > cur.cost + 3) { // 跳陷阱
                            distance[0][next] = cur.cost + 3;
                            pq.add(new Node(0, next, distance[0][next]));
                        }
                    }
                }
            }

        }
        minList.sort((o1, o2) -> o1 - o2);
        return minList.get(0);
    }

    // 起点、终点、地雷、墙、陷阱、路
// 0     1    2    3   4    5
    private void addNode(int nx, int ny, int bomb, PriorityQueue<Node> pq) {
        if (nx < 0 || nx >= N || ny < 0 || ny >= M) return;
        // 地雷、墙、陷阱、路
        // 2    3   4    5

    }

    public static class Node {
        public int bomb; // 0: 没用炸弹, 1: 用了炸弹
        public int pos; // 到达的位置
        public int cost; // 花费的时间

        public Node(int a, int b, int c) {
            bomb = a;
            pos = b;
            cost = c;
        }

    }


    public static void main(String[] args) {

        // 起点、终点、地雷、墙、陷阱、路
        // 0     1    2    3   4    5
        int[][] grid = {
                {0, 5, 5},
                {3, 3, 5},
                {2, 4, 1}
        };
        var ans = new Problem_000_BombDistance().getMinTime(grid);
        System.out.println(ans);
    }
}
