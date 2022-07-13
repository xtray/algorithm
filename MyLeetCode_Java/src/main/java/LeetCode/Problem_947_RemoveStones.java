package LeetCode;

import java.util.HashMap;
import java.util.Map;

// NOTE: 并查集中用石头的标号代表石头
// tag: 并查集

public class Problem_947_RemoveStones {

    public static class UnionFind {

        public int[] father;
        public int[] size;
        public int[] help;
        public int sets;

        public UnionFind(int n) {
            father = new int[n];
            size = new int[n];
            help = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
                size[i] = 1;
            }
            sets = n;
        }

        private int find(int i) {
            int hi = 0;
            while (i != father[i]) {
                help[hi++] = i;
                i = father[i];
            }
            while (hi != 0) {
                father[help[--hi]] = i;
            }
            return i;
        }

        public void union(int i, int j) {
            int fi = find(i);
            int fj = find(j);
            if (fi != fj) {
                if (size[fi] >= size[fj]) {
                    father[fj] = fi;
                    size[fi] += size[fj];
                } else {
                    father[fi] = fj;
                    size[fj] += size[fi];
                }
                sets--;
            }
        }

        public int sets() {
            return sets;
        }

    }

    public int removeStones(int[][] stones) {
        if (stones == null || stones.length == 0) {
            return 0;
        }
        // 每一行的行列代表
        Map<Integer, Integer> rowMap = new HashMap<>();
        Map<Integer, Integer> colMap = new HashMap<>();
        int n = stones.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i<n; i++) {
            int x = stones[i][0];
            int y = stones[i][1];
            if (rowMap.containsKey(x)) {
                uf.union(i, rowMap.get(x));
            } else {
                rowMap.put(x, i); // 用石头的标号代替这个石头
            }
            if (colMap.containsKey(y)) {
                uf.union(i, colMap.get(y));
            } else {
                colMap.put(y, i); // 用石头的标号代替这个石头
            }
        }
        return n - uf.sets();
    }
}
