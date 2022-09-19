package LeetCode;


import java.util.*;

public class Problem_269_AlienOrder {

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

    public String alienOrder1(String[] words) {
        List<List<Integer>> graph = new ArrayList<>();
        // 初始化邻接表
        for (int i = 0; i < 26; i++) {
            graph.add(new ArrayList<>());
        }
        // 1.根据words建图
        // 规则:
        //   第一个字母不同, 排在前面的字典序在前, 建边
        //   第一个相同, 继续比较短的长度, 直到第一个不同, 建边
        //              如果一直到结束都相同, 且第一个串长, 非法, 返回
        int N = words.length;
        int[] inMap = new int[26];
        boolean[] set = new boolean[26];
        for (char ch : words[0].toCharArray()) {
            set[ch - 'a'] = true;
        }
        for (int i = 1; i < N; i++) {
            char[] pre = words[i - 1].toCharArray();
            char[] cur = words[i].toCharArray();
            for (char ch : cur) {
                set[ch - 'a'] = true;
            }
            int len1 = pre.length;
            int len2 = cur.length;
            if (pre[0] != cur[0]) {
                graph.get(pre[0] - 'a').add(cur[0] - 'a');
                inMap[cur[0] - 'a']++;
            } else {
                int j = 1;
                int len = Math.min(len1, len2);
                while (j < len && pre[j] == cur[j]) {
                    j++;
                }
                if (j == len) { //一路相等
                    if (len2 < len1) return "";
                } else {
                    graph.get(pre[j] - 'a').add(cur[j] - 'a');
                    inMap[cur[j] - 'a']++;
                }
            }
        }
        // 拓扑排序
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < 26; i++) {
            if (set[i] && inMap[i] == 0) {
                queue.addLast(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int cur = queue.pollLast();
            sb.append((char) (cur + 'a'));
            for (int next : graph.get(cur)) {
                --inMap[next];
                if (inMap[next] == 0) {
                    queue.addLast(next);
                }
            }
        }
        int nodeCnt = 0;
        for (boolean s : set) {
            if (s) nodeCnt++;
        }
        // NOTE: 最后拓扑排序的点数量不等于总点数说明有环路
        return sb.length() == nodeCnt ? sb.toString() : "";
    }

    public static void main(String[] args) {
        String[] words = {"wrt", "wrf", "er", "ett", "rftt"};
        // String[] words = {"z", "x", "a", "zb", "zx"}; // ""
        // String[] words = {"z", "z"};
        // String[] words = {"aac","aabb","aaba"};
        // String[] words = {"abc", "ab"}; // ""
        // String[] words = {"ab", "ba", "aa"};
        var ans = new Problem_269_AlienOrder().alienOrder(words);
        System.out.println(ans);

        var ans1 = new Problem_269_AlienOrder().alienOrder1(words);
        System.out.println(ans1);
    }
}
