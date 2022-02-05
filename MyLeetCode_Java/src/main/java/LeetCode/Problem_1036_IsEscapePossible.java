package LeetCode;

import java.util.*;

public class Problem_1036_IsEscapePossible {


    // BFS
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        int[][] dirs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        LinkedList<int[]> queue = new LinkedList<>();
        long width = (long) 1e6;
        Set<Long> set = new HashSet<>();
        Set<Long> block = new HashSet<>();
        for (int[] b : blocked) {
            block.add(b[0] * width + b[1]);
        }
        queue.add(source);
        set.add(source[0] * width + source[1]);
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx >= 0 && nx < (int) 1e6 &&
                        ny >= 0 && ny < (int) 1e6 &&
                        !set.contains(nx * width + ny) &&
                        !block.contains(nx * width + ny)) {
                    if (nx == target[0] && ny == target[1]) {
                        return true;
                    }
                    set.add(nx * width + ny);
                    queue.add(new int[]{nx, ny});
                }
            }
        }
        return false;
    }

    private final int[][] dir = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    // 离散化, block 点的个数<=200, 考虑每个点的位置关系, 算上其四周的点
    // 203*5  = 1015 个点
    public boolean isEscapePossible2(int[][] blocked, int[] source, int[] target) {

        // key: org
        // value: index
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> dots = new TreeSet<>();
        addDots(dots, source);
        addDots(dots, target);
        for (int[] b : blocked) {
            addDots(dots, b);
        }
        int index = 0;
        for (int d : dots) {
            map.put(d, index++);
        }

        int width = 1000;
        Set<Integer> set = new HashSet<>();
        Set<Integer> block = new HashSet<>();
        for (int[] b : blocked) {
            int bx = map.get(b[0]);
            int by = map.get(b[1]);
            block.add(bx * width + by);
        }
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[]{map.get(source[0]), map.get(source[1])});
        set.add(map.get(source[0]) * width + map.get(source[1]));
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            for (int[] d : dir) {
                int nx = x + d[0];
                int ny = y + d[1];
                int oneDPos = nx * width + ny;
                if (nx>=0 && nx < index && ny>=0 && ny < index &&
                        !set.contains(oneDPos) &&
                        !block.contains(oneDPos)) {
                    int destX = map.get(target[0]);
                    int destY = map.get(target[1]);
                    if (nx == destX && ny == destY) {
                        return true;
                    }
                    set.add(nx * width + ny);
                    queue.add(new int[]{nx, ny});
                }
            }
        }
        return false;
    }

    // 将当前点跟它的上下左右点加入
    private void addDots(Set<Integer> dots, int[] dot) {
        int x = dot[0];
        int y = dot[1];
        dots.add(x);
        dots.add(y);

        for (int[] d : dir) {
            int nx = x + d[0];
            int ny = y + d[1];
            if (nx >= 0 && nx < (int) 1e6 &&
                    ny >= 0 && ny < (int) 1e6) {
                dots.add(nx);
                dots.add(ny);
            }
        }
    }

    private int[] source;
    private int[] target;
    private int MaxPoint;
    private Set<Long> blockSet = new HashSet<>();

    // https://leetcode-cn.com/problems/escape-a-large-maze/solution/bian-cheng-xiong-bfs-acmjin-pai-ti-jie-b-dquc/
    public boolean isEscapePossible3(int[][] blocked, int[] source, int[] target) {
        this.source = source;
        this.target = target;
        this.MaxPoint = (blocked.length + 1) * (blocked.length + 2) / 2;
				// 将block块，放到哈希表，后面遍历地图会用到
        for (int i = 0; i < blocked.length; i++)
            blockSet.add(blocked[i][0] * (long)1e6 + blocked[i][1]);
        // 分别从起点和终点BFS出发遍历地图
        return bfs(source[0], source[1], true) && bfs(target[0], target[1], false);
    }

    boolean bfs(int x, int y, boolean is_source) {
        int[][] dir = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};

        // vis存放已经访问过的点
        Set<Long> vis = new HashSet<>();
        // 维护BFS过程的队列
        Deque<Long> d = new ArrayDeque<>();
        // 将起点放入队列中，并在vis中标记
        d.add(x * (long)1e6 + y);
        vis.add(x * (long)1e6 + y);

        // 超过最大还没有找到,说明没有被包围, 可以到达
        while ((!d.isEmpty()) && (vis.size() <= MaxPoint)) {
            long p = d.pollFirst();
            long px = (long)(p / 1e6);
            long py = (long)(p - px * 1e6);

            for (int i = 0; i < 4; i++) {
                // 找到相邻的点
                long nx = px + dir[i][0];
                long ny = py + dir[i][1];
                // 出边界 或者 是block块
                if (nx < 0 || nx >= 1e6 || ny < 0 || ny >= 1e6 || blockSet.contains(nx * (long)1e6 + ny))
                    continue;
                // 已经访问过
                if (vis.contains(nx * (long)1e6 + ny))
                    continue;
                // 已经找到终点
                if (isArrive(is_source, nx, ny))
                    return true;
                // 放入队列中，并在vis中标记
                d.add(nx * (long)1e6 + ny);
                vis.add(nx * (long)1e6 + ny);
            }
        }
        // 判断访问过点的数量
        return vis.size() > MaxPoint;
    }
    // 如果从起点出发，那么中途到达终点；
    // 如果从终点出发，那么中途到达起点；
    boolean isArrive(boolean is_source, long x, long y) {
        if (is_source && x == target[0] && y == target[1])
            return true;

        if ((!is_source) && x == source[0] && y == source[1])
            return true;

        return false;
    }


    public static void main(String[] args) {
        int[][] blocked = {{0,3},{1,0},{1,1},{1,2},{1,3}};
        int[] source = {0, 0};
        int[] target = {0, 2}; // true
        // int[][] blocked = {};
        // int[] source = {0, 0};
        // int[] target = {999999, 999999};
        // int[][] blocked = {{2,1},{1,2}};
        // int[] source = {0,0};
        // int[] target =  {2,2};
        // 0 2 0
        // 0 0 2
        // 1 0 0
        var ans = new Problem_1036_IsEscapePossible().isEscapePossible2(blocked, source, target);
        System.out.println(ans);
    }

}
