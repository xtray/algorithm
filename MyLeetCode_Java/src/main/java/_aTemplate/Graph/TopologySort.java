package _aTemplate.Graph;

import java.util.*;

public class TopologySort {

    public static List<Node> topologySort(Graph graph) {
        HashMap<Node, Integer> inMap = new HashMap<>(); // 每个点的入度
        Queue<Node> zeroInQueue = new LinkedList<>(); // 入度为零的点进队列
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
        return result;
    }
}
