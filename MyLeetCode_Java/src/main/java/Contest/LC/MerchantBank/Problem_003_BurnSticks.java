package Contest.LC.MerchantBank;

import java.util.*;

public class Problem_003_BurnSticks {

    public static class Node {
        public int in; // 入度
        public int out; // 出度
        public int val; // 值
        public List<Node> nexts; // 下级的点
        public List<Edge> edges; // 出去的边

        public Node(int v) {
            val = v;
            in = 0;
            out = 0;
            nexts = new ArrayList<>();
            edges = new ArrayList<>();
        }
    }

    public static class Edge {
        public int from;
        public int to;
        public int weight; // 权重

        public Edge(int f, int t, int w) {
            from = f;
            to = t;
            weight = w;
        }
    }

    public static class Graph {
        public Map<Integer, Node> nodes;
        public Set<Edge> edges;

        public Graph() {
            nodes = new HashMap<>();
            edges = new HashSet<>();
        }
    }

    // 以每一个幸存点作为头,做BFS扩散, 遍历所有点的最小BFS步长
    // 1. 先求出最小步长
    // 2. 收集所有最小步长的头
    public int[] lightSticks(int height, int width, int[] indices) {
        if (indices == null || indices.length == 0) {
            return new int[]{};
        }
        // 1. 根据输入构建图
        Graph graph = generateGraph(height + 1, width + 1);

        // 难点: 怎样找到一个点周围的边

        // 2. 以每一个点为头做BFS

        return new int[]{};
    }

    // 是无向图
    private Graph generateGraph(int N, int M) {
        Graph graph = new Graph();
        for(int i = 0; i < N; i++ ) {
            for(int j = 0; j<M; j++) {
                int oneD = i *M + j;

                if(i == 0) { // 第零行

                }
                if(i == N-1) { // 第N-1行

                }
                if(j == 0) { // 第零列

                }
                if(j == M-1) { // 第M-1列

                }
            }
        }

        return graph;

    }
}
