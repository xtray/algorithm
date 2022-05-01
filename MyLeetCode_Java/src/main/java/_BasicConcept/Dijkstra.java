package _BasicConcept;

import java.util.*;

// IMP: 不使用加强堆, 记录不删除, 简化的做法!!!

public class Dijkstra {

    public static class NodeInfo {
        public int distance;
        public Node node;

        public NodeInfo(int d, Node n) {
            distance = d;
            node = n;
        }
    }

    // 方法1: 优先队列, 小顶堆, 数据更新时, 不删除也不更新老的
    // 新数据更小更靠近堆顶先出栈, 老数据再出栈时, selected判断为true, 跳过
    public static HashMap<Node, Integer> dijkstra1(Node from) {
        HashMap<Node, Integer> distanceMap = new HashMap<>(); // 距离表
        PriorityQueue<NodeInfo> pq = new PriorityQueue<>((o1, o2) -> o1.distance - o2.distance); // 按距离排序点
        Set<Node> selected = new HashSet<>();// 打过对号的点
        distanceMap.put(from, 0);
        pq.add(new NodeInfo(0, from));
        while (!pq.isEmpty()) {
            NodeInfo nf = pq.poll();
            Node minNode = nf.node;
            int distance = nf.distance;
            if (selected.contains(minNode)) continue; // 老数据再出栈时, selected判断为true, 跳过
            selected.add(minNode);
            for (Edge edge : minNode.edges) {
                Node toNode = edge.to;
                if (!distanceMap.containsKey(toNode)) {
                    distanceMap.put(toNode, distance + edge.weight);
                    pq.add(new NodeInfo(distanceMap.get(toNode), toNode));
                } else {
                    if (distance + edge.weight < distanceMap.get(toNode)) { // 要被更新
                        distanceMap.put(toNode, distance + edge.weight);
                        // 多加一遍, 新的节点数值小更靠近堆顶, 先弹出, 老值被selected过滤, 不影响
                        pq.add(new NodeInfo(distanceMap.get(toNode), toNode));
                    }
                }
            }
        }
        return distanceMap;
    }

    // 方法2: 加强堆的改法
}
