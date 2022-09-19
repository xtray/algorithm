package _aTemplate.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

// IMP: Dijkstra算法, 重要, 多看!!

// 有向无负权重的图, 一定要给出出发点

// 从出发点到所有点的最短距离的一张表

// IMP: 不使用加强堆, 记录不删除, 简化的做法!!!

public class Dijkstra {

    public static class NodeRecord {
        public Node node;
        public int distance; // 从原始点到当前点的距离

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    // 方法1: 优先队列(小根堆), 数据更新时不删除也不更新老的
    // 新数据更小更靠近堆顶先出栈, 老数据再次弹出时used==true, 跳过

    // NOTE: 使用优先队列精简的不删除的Dijkstra版本, 重要!!
    public static HashMap<Node, Integer> dijkstra1(Node from) {
        HashMap<Node, Integer> distanceMap = new HashMap<>(); // 距离表
        PriorityQueue<NodeRecord> pq = new PriorityQueue<>((o1, o2) -> o1.distance - o2.distance);
        Set<Node> used = new HashSet<>();// 打过对号的 锁定的点
        pq.add(new NodeRecord(from, 0));
        while (!pq.isEmpty()) {
            NodeRecord record = pq.poll();
            Node cur = record.node;
            int distance = record.distance;
            if (used.contains(cur)) continue; // 老数据再次弹出时used==true, 跳过
            used.add(cur);
            // 每次弹出的这个点在收集之前看看能不能让其他点的距离变小
            for (Edge edge : cur.edges) {
                Node toNode = edge.to;
                pq.add(new NodeRecord(toNode,distance + edge.weight));
            }
            distanceMap.put(cur, distance);
        }
        return distanceMap;
    }

    // 方法2: 加强堆的改法 --> Dijkstra_02

    // PENDING
    // 数组形式的
}
