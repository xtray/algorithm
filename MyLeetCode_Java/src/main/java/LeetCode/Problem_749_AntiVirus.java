package LeetCode;

import java.util.*;

public class Problem_749_AntiVirus {

    private static final int[] dirs = new int[]{0, -1, 0, 1, 0};

    public static class UnionFind {
        private int N;
        private int M;
        private int[][] grid;
        private int[] fatherMap; // 每个点的代表节点是谁
        // 并查集代表节点所在集合的待感染区块List
        // 有重复, 一个块被扫到几次就是要封几条边
        private Map<Integer, List<Integer>> infectMap;
        private Map<Integer, Integer> sizeMap;
        private boolean[] blocked; // 代表节点所在集合的是否被锁定
        private int[] stack; // 数组做栈

        public UnionFind(int[][] matrix) {
            initSpace(matrix);
            initConnect();
        }

        private void initSpace(int[][] matrix) {
            grid = matrix;
            N = grid.length;
            M = grid[0].length;
            int all = N * M;
            infectMap = new HashMap<>();
            sizeMap = new HashMap<>();
            fatherMap = new int[all];
            blocked = new boolean[all];
            stack = new int[all];
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < M; col++) {
                    if (grid[row][col] == 1) {
                        int index = row * M + col;
                        fatherMap[index] = index;
                        sizeMap.put(index, 1);
                        // 生成当前代表节点的待感染区块
                        infectMap.put(index, getInfectArea(row, col));
                    }
                }
            }
        }

        // 每一个格子上下左右进入待感染区块, 重复计算格子
        private List<Integer> getInfectArea(int row, int col) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < dirs.length - 1; i++) {
                int nx = row + dirs[i];
                int ny = col + dirs[i + 1];
                if (valid(nx, ny, 0)) {
                    list.add(nx * M + ny);
                }
            }
            return list;
        }

        private void initConnect() {
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < M; col++) {
                    union(row, col, row, col + 1);
                    union(row, col, row + 1, col);
                    // union(row, col, row, col - 1);
                    // union(row, col, row - 1, col);
                }
            }
        }

        private int find(int row, int col) {
            int stackSize = 0;
            int index = row * M + col;
            while (index != fatherMap[index]) {
                stack[stackSize++] = index;
                index = fatherMap[index];
            }
            while (stackSize != 0) {
                fatherMap[stack[--stackSize]] = index;
            }
            return index;
        }

        public void finger(int val) {
            int row = val / M;
            int col = val % M;
            grid[row][col] = 1;
            fatherMap[val] = val;
            sizeMap.put(val, 1);
            union(row, col, row - 1, col);
            union(row, col, row + 1, col);
            union(row, col, row, col - 1);
            union(row, col, row, col + 1);
        }

        private void union(int r1, int c1, int r2, int c2) {
            if (valid(r1, c1, 1) && valid(r2, c2, 1)) {
                int father1 = find(r1, c1);
                int father2 = find(r2, c2);
                List<Integer> list1 = infectMap.get(father1);
                List<Integer> list2 = infectMap.get(father2);
                if (father1 != father2) {
                    int size1 = sizeMap.get(father1);
                    int size2 = sizeMap.get(father2);
                    if (size1 <= size2) {
                        fatherMap[father1] = father2;
                        sizeMap.put(father2, size1 + size2);
                        sizeMap.remove(father1);
                        if (list1 != null) list2.addAll(list1); // 合并感染表
                        infectMap.remove(father1);
                    } else {
                        fatherMap[father2] = father1;
                        sizeMap.put(father1, size1 + size2);
                        sizeMap.remove(father2);
                        if (list2 != null) list1.addAll(list2); // 合并感染表
                        infectMap.remove(father2);
                    }
                }
            }
        }
        // 待感染区块的特点:
        // 在格子范围以内 &&  grid[row][col] 值为0
        private boolean valid(int row, int col, int val) {
            if (!(row >= 0 && row < N && col >= 0 && col < M && grid[row][col] == val)) {
                return false;
            }
            // union的时候, grid[row][col] == 1, 就有代表节点, 要求代表节点不在黑名单里
            if (grid[row][col] == 1 && blocked[find(row, col)]) {
                return false;
            }
            return true;
        }
    }

    public int containVirus(int[][] isInfected) {
        int N = isInfected.length;
        int M = isInfected[0].length;
        UnionFind uf = new UnionFind(isInfected);
        int ans = 0;
        while (true) {
            // 获取当前可用的病毒待感染区域
            int maxIdx = -1;
            int maxSize = 0;
            int sum = 0;
            // 找对未感染区威胁最大的
            for (int fa : uf.sizeMap.keySet()) { // 枚举所有代表节点
                // 根据代表节点取到每一个感染区
                List<Integer> area = uf.infectMap.getOrDefault(fa, new ArrayList<>());
                Set<Integer> netArea = new HashSet<>();
                netArea.addAll(area);
                // 找最大的感染区
                sum += netArea.size();
                if (netArea.size() > maxSize) {
                    maxIdx = fa;
                    maxSize = netArea.size();
                }
            }
            // 不存在感染区域返回
            if (sum == 0) {
                break;
            }
            // 最大的感染区域安装栅栏
            // 栅栏数量是List列表的数量
            ans += uf.infectMap.get(maxIdx).size();
            uf.blocked[maxIdx] = true; // 屏蔽
            uf.infectMap.remove(maxIdx);
            uf.sizeMap.remove(maxIdx);

            Set<Integer> infSet = new HashSet<>(); // 感染区去重
            for(int fa : uf.sizeMap.keySet()) {
                List<Integer> area = uf.infectMap.getOrDefault(fa, new ArrayList<>());
                infSet.addAll(area);
            }

            for (int val : infSet) {
                // 标记所有感染区块为1
                uf.finger(val);
            }
            // 同时更新感染区块
            Map<Integer, List<Integer>> nextMap = new HashMap<>();
            for (int val : infSet) {
                int fa = uf.find(val / M, val % M); // 找到代表节点
                List<Integer> area = uf.getInfectArea(val / M, val % M);
                nextMap.computeIfAbsent(fa, key -> new ArrayList<>()).addAll(area);
            }
            for (int fa : nextMap.keySet()) {
                uf.infectMap.put(fa, nextMap.get(fa));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // int[][] isInfected = {
        //         {0, 1, 0, 0, 0, 0, 0, 1},
        //         {0, 1, 0, 0, 0, 0, 0, 1},
        //         {0, 0, 0, 0, 0, 0, 0, 1},
        //         {0, 0, 0, 0, 0, 0, 0, 0}}; // 10
        // int[][] isInfected = {
        //         {1, 1, 1},
        //         {1, 0, 1},
        //         {1, 1, 1}}; // 4
        // int[][] isInfected = {
        //         {1, 1, 1, 0, 0, 0, 0, 0, 0},
        //         {1, 0, 1, 0, 1, 1, 1, 1, 1},
        //         {1, 1, 1, 0, 0, 0, 0, 0, 0}}; // 13

        int[][] isInfected = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}}; // 56


        // int[][] isInfected = {
        //         {0, 0, 0, 0, 0, 0, 0},
        //         {1, 0, 0, 0, 1, 0, 0},
        //         {0, 0, 0, 0, 1, 0, 0},
        //         {0, 0, 0, 0, 0, 0, 1}}; // 16


        // int[][] isInfected = {
        //         {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        //         {0, 0, 1, 0, 0, 0, 1, 0, 0, 0},
        //         {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
        //         {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        //         {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        //         {0, 0, 0, 0, 0, 0, 0, 0, 1, 0}}; // 23

        // int[][] isInfected = {
        //         {1, 0, 0, 0, 0, 0, 0},
        //         {0, 0, 1, 0, 1, 0, 0},
        //         {0, 0, 0, 0, 1, 0, 0},
        //         {0, 0, 0, 0, 0, 0, 1}}; // 18
        var ans = new Problem_749_AntiVirus().containVirus(isInfected);
        System.out.println(ans);

    }
}
