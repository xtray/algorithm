package _BasicConcept;

import java.util.*;

public class TopologySort {

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

    public static List<Node> topologySort(Graph graph) {
        HashMap<Node, Integer> inMap = new HashMap<>(); //每个点的入度
        Queue<Node> zeroInQueue = new LinkedList<>(); // 入度为零的点进队列
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        List<Node> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            Node cur = zeroInQueue.poll();
            result.add(cur);
            for (Node next : cur.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }
}
