package _aTemplate;

// IMP: 数组形式的并查集

public class UnionFind_1 {

    public static class UnionFind {
        // parent[i] = k ： i的父亲是k
        private int[] parent;
        // size[i] = k ： 如果i是代表节点，size[i]才有意义，否则无意义
        // i所在的集合大小是多少
        private int[] size;
        private int[] stack; // 数组做栈
        private int setSize; // 一共有多少个集合

        public UnionFind(int N) {
            parent = new int[N];
            size = new int[N];
            stack = new int[N];
            setSize = N;
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        // // 从i开始一直往上，往上到不能再往上，代表节点，返回
        // // 这个过程要做路径压缩
        // private int find(int i) {
        //     int hi = 0;
        //     while (i != parent[i]) {
        //         stack[hi++] = i;
        //         i = parent[i];
        //     }
        //     for (hi--; hi >= 0; hi--) { // 扁平化
        //         parent[stack[hi]] = i;
        //     }
        //     return i;
        // }

        // 路径压缩的简单写法
        // private int find(int i) {
        //     if (parent[i] == i) return i;
        //     return parent[i] = find(parent[i]);
        // }

        private int find(int i) {
            if (parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        public void union(int i, int j) {
            int f1 = find(i);
            int f2 = find(j);
            if (f1 != f2) {
                // 小挂大
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                setSize--;
            }
        }

        public boolean isSameSet(int i, int j) {
            return find(i) == find(j);
        }

        public int setSize() {
            return setSize;
        }
    }
}
