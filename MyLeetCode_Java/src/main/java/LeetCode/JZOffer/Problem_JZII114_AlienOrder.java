package LeetCode.JZOffer;

import java.util.*;

public class Problem_JZII114_AlienOrder {

    public static class Node {
        public int in; // 入度
        public int value; // 值
        public List<Node> nexts; // 当前点下级的点 toNodes
        public Node(int val) {
            value = val;
            in = 0;
            nexts = new ArrayList<>();
        }
    }

    public static class Graph {
        public Map<Integer, Node> nodes; // 每一个数指向一个Node
        public Graph() {
            nodes = new HashMap<>();
        }
    }

    // 边仅由相邻单词w1和w2的「第一对同下标相异字符」给出
    // 有n个单词的words字符串数组，因为有n-1对相邻单词，所以最多能够构成一张具有n-1条边的图。
    public String alienOrder(String[] words) {

        Graph graph = generateGraph(words);
        if (graph == null) {
            return "";
        }
        List<Node> list = topologySort(graph);
        if (list == null) { // 有环
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Node node : list) {
            sb.append((char) (node.value + 'a'));
        }
        return sb.toString();
    }

    private Graph generateGraph(String[] words) {
        int N = words.length;
        Graph graph = new Graph();
        // 生成所有点集
        for (String word : words) {
            addNode(graph.nodes, word);
        }
        // 生成所有边集
        String pre = words[0];
        for (int i = 1; i < N; i++) {
            String word = words[i];
            int minLen = Math.min(pre.length(), word.length());
            int j = 0;
            for (; j < minLen; j++) {
                if (pre.charAt(j) != word.charAt(j)) {
                    break;
                }
            }
            if (j == minLen) {
                // pre 长
                if (pre.length() > word.length()) {
                    return null;
                } // pre<=word
                // word长, 不能确定边跳过
            } else { // j < minLen
                // 建立一条边
                int from = pre.charAt(j) - 'a';
                int to = word.charAt(j) - 'a';
                Node fromNode = graph.nodes.get(from);
                Node toNode = graph.nodes.get(to);
                fromNode.nexts.add(toNode);
                toNode.in++;
            }
            pre = word;
        }
        return graph;
    }

    private void addNode(Map<Integer, Node> nodes, String word) {
        for (char ch : word.toCharArray()) {
            nodes.computeIfAbsent(ch - 'a', k -> new Node(ch - 'a'));
        }
    }

    public static List<Node> topologySort(Graph graph) {
        HashMap<Node, Integer> inMap = new HashMap<>(); // 每个点的入度
        ArrayDeque<Node> zeroInQueue = new ArrayDeque<>(); // 入度为零的点进队列
        for (Node node : graph.nodes.values()) { // 遍历所有的点
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        List<Node> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            Node cur = zeroInQueue.poll();
            result.add(cur);
            for (Node next : cur.nexts) { // 消掉所有点的影响
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        if (result.size() != graph.nodes.size()) {
            return null; // 有环!
        }
        return result;
    }

    public static void main(String[] args) {
        // String[] words = {"wrt", "wrf", "er", "ett", "rftt"};
        // String[] words = {"z","x","a","zb","zx"};
        String[] words = {"z", "x", "a", "c", "d"};
        var ans = new Problem_JZII114_AlienOrder().alienOrder(words);
        System.out.println(ans);
    }
}
