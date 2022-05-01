package LeetCode;

import java.util.*;

// https://leetcode-cn.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/
// https://leetcode-cn.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/solution/zhao-dao-zui-xiao-sheng-cheng-shu-li-de-gu57q/
// NOTE: 本题涉及多个知识点, 后面多看看

public class Problem_1489_FindCriticalEdges {

    // 点结构的描述
    public static class Node {
        public int value;
        public int in;
        public int out;
        public ArrayList<Node> nexts;
        public ArrayList<Edge> edges;

        public Node(int value) {
            this.value = value;
            in = 0;
            out = 0;
            nexts = new ArrayList<>();
            edges = new ArrayList<>();
        }
    }

    // 边结构的描述
    public static class Edge {
        public int weight;
        public Node from;
        public Node to;

        public Edge(int weight, Node from, Node to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }

    // 图的描述
    public static class Graph {
        public HashMap<Integer, Node> nodes;
        public HashSet<Edge> edges;

        public Graph(int n) {
            nodes = new HashMap<>();
            edges = new HashSet<>();
            for (int i = 0; i < n; i++) {
                nodes.put(i, new Node(i));
            }
        }
    }

    // K算法使用的并查集结构
    public static class UnionFind {
        private HashMap<Node, Node> fatherMap;
        private HashMap<Node, Integer> sizeMap;

        public UnionFind() {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        public void init(Collection<Node> nodes) {
            for (Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        private Node findFather(Node node) {
            Stack<Node> path = new Stack<>();
            while (fatherMap.get(node) != node) {
                path.push(node);
                node = fatherMap.get(node);
            }
            while (!path.isEmpty()) { // 扁平化
                fatherMap.put(path.pop(), node);
            }
            return node;
        }

        public boolean isSameSet(Node a, Node b) {
            return findFather(a) == findFather(b);
        }

        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }
            Node aFa = findFather(a);
            Node bFa = findFather(b);
            if (aFa != bFa) {
                int aSzie = sizeMap.get(aFa);
                int bSzie = sizeMap.get(bFa);
                Node big = aSzie == Math.max(aSzie, bSzie) ? aFa : bFa;
                Node small = big == aFa ? bFa : aFa;
                fatherMap.put(small, big);
                sizeMap.put(big, aSzie + bSzie);
                sizeMap.remove(small);
            }
        }
    }

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        List<Integer> realKeyEdges = new ArrayList<>();
        List<Integer> potentialEdges = new ArrayList<>();
        List<Integer> pseudoKeyEdges = new ArrayList<>();

        // 1.获取标准的最小生成树的权值
        int minWeight = getMinWeight(n, edges, -1);
        for (int i = 0; i < edges.length; i++) {
            int curWeight = getMinWeight(n, edges, i);
            if (curWeight > minWeight) {
                realKeyEdges.add(i);
            } else if (curWeight == minWeight){
                potentialEdges.add(i); // 潜在的伪关键边
            }
        }

        // 检查伪关键边
        for (Integer index : potentialEdges) {
            int weight = getMinWeightWithStart(n, edges, index);
            if (weight == minWeight) {
                pseudoKeyEdges.add(index);
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        ans.add(realKeyEdges);
        ans.add(pseudoKeyEdges);
        return ans;
    }

    /**
     * 伪关键边：可能会出现在某些最小生成树中但不会出现在所有最小生成树中的边。
     * 也就是说，我们可以在计算最小生成树的过程中，最先考虑这条边，
     * 即最先将这条边的两个端点在并查集中合并。设最终得到的最小生成树权值为 v，
     * 如果 v = value，那么这条边就是伪关键边。
     *
     */
    private int getMinWeightWithStart(int n, int[][] edges, Integer start) {
        // 1. 生成标准的图结构
        Graph graph = generateGraph(n, edges, -1);
        // 2.根据K算法获得最小生成树的最小权值
        int minWeight = 0;
        UnionFind uf = new UnionFind();
        uf.init(graph.nodes.values());
        Node from = graph.nodes.get(edges[start][0]);
        Node to = graph.nodes.get(edges[start][1]);
        minWeight += edges[start][2]; // 不要忘了加起点边的权值!
        uf.union(from, to);

        // 边的权重小根堆
        // 没有必要使用优先队列, 普通的排序就可以
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        for (Edge edge : graph.edges) {
            pq.add(edge);
        }
        Set<Edge> result = new HashSet<>(); // 收集权重最小的边

        while (!pq.isEmpty()) {
            Edge curEdge = pq.poll();
            if (!uf.isSameSet(curEdge.from, curEdge.to)) {
                result.add(curEdge);
                minWeight += curEdge.weight;
                uf.union(curEdge.from, curEdge.to);
            }
        }
        return minWeight;
    }

    public int getMinWeight(int n, int[][] edges, int ignoreIdx) {
        // 1. 生成标准的图结构
        Graph graph = generateGraph(n, edges, ignoreIdx);
        // 2.根据K算法获得最小生成树的最小权值
        UnionFind uf = new UnionFind();
        uf.init(graph.nodes.values());
        // 边的权重小根堆
        // NOTE: 没有必要使用优先队列, 普通的排序就可以
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        for (Edge edge : graph.edges) {
            pq.add(edge);
        }
        Set<Edge> result = new HashSet<>(); // 收集权重最小的边
        int minWeight = 0;
        while (!pq.isEmpty()) {
            Edge curEdge = pq.poll();
            if (!uf.isSameSet(curEdge.from, curEdge.to)) {
                result.add(curEdge);
                minWeight += curEdge.weight;
                uf.union(curEdge.from, curEdge.to);
            }
        }

        return uf.sizeMap.size() == 1? minWeight : Integer.MAX_VALUE;
    }

    private Graph generateGraph(int n, int[][] edges, int ignoreIdx) {
        Graph graph = new Graph(n);
        int idx = 0;
        for (int[] e : edges) {
            if (idx++ == ignoreIdx) continue;
            int from = e[0];
            int to = e[1];
            int weight = e[2];
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);

            Edge newEdge = new Edge(weight, fromNode, toNode);

            fromNode.out++;
            toNode.in++;
            fromNode.nexts.add(toNode);
            fromNode.edges.add(newEdge);

            graph.edges.add(newEdge);
        }
        return graph;
    }

    public static void main(String[] args) {
        // int[][] edges = new int[][]{{0, 1, 1}, {1, 2, 1}, {2, 3, 2}, {0, 3, 2}, {0, 4, 3}, {3, 4, 3}, {1, 4, 6}};
        // int n = 5;

        // int[][] edges = new int[][]{{0,1,1},{1,2,1},{2,3,1},{0,3,1}};
        // int n = 4;

        int[][] edges = {{0,1,1},{1,2,1},{0,2,1},{2,3,4},{3,4,2},{3,5,2},{4,5,2}};
        int n = 6; // [[3],[0,1,2,4,5,6]]

        var ans = new Problem_1489_FindCriticalEdges().findCriticalAndPseudoCriticalEdges(n, edges);
        for (var item : ans.get(0)) {
            System.out.println(item + " ");
        }
        System.out.println();
        for (var item : ans.get(1)) {
            System.out.println(item + " ");
        }
        System.out.println();
    }
}
