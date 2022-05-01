package _LintCode;

import java.util.*;

// https://www.lintcode.com/problem/127/
// IMP: 重要, 多看!!

public class Problem_127_TopSort {

    public static class DirectedGraphNode {

        int label;

        List<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {

            label = x;

            neighbors = new ArrayList<DirectedGraphNode>();

        }

    }


    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        if (graph == null) {
            return ans;
        }
        HashMap<DirectedGraphNode, Long> cntMap = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            getNodeCnts(node, cntMap); // 计算每一个node的点次
        }
        graph.sort((o1, o2) -> cntMap.get(o2).compareTo(cntMap.get(o1)));
        return graph;
    }

    // NOTE: 点次评价下的拓扑序, 点次越大的拓扑序越靠前
    // IMP: 计算每个节点的点次, 存在map里
    private long getNodeCnts(DirectedGraphNode node, HashMap<DirectedGraphNode, Long> cntMap) {
        if (cntMap.containsKey(node)) {
            return cntMap.get(node);
        }
        // 没有算过
        long nodes = 0;
        for (DirectedGraphNode next : node.neighbors) {
            nodes += getNodeCnts(next, cntMap); // 所有点次要加起来
        }
        cntMap.put(node, nodes + 1);
        return nodes + 1;
    }

    public ArrayList<DirectedGraphNode> topSort2(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        if (graph == null) {
            return ans;
        }
        HashMap<DirectedGraphNode, Integer> depthMap = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            getNodeDepth(node, depthMap); // 计算每一个node的点次
        }
        graph.sort((o1, o2) -> depthMap.get(o2).compareTo(depthMap.get(o1)));
        return graph;
    }

    // NOTE: 最大深度评价的拓扑序, 深度越大的拓扑序越靠前
    private int getNodeDepth(DirectedGraphNode node, HashMap<DirectedGraphNode, Integer> depthMap) {
        if (depthMap.containsKey(node)) {
            return depthMap.get(node);
        }
        // 没有算过
        int depth = 0;
        for (DirectedGraphNode next : node.neighbors) {
            // 最大深度是所有下级中的最大值
            depth = Math.max(depth, getNodeDepth(next, depthMap));
        }
        depthMap.put(node, depth + 1); // 我自己的要+1
        return depth + 1;
    }
}
