package AlgoExpert;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

// https://www.algoexpert.io/questions/Cycle%20In%20Graph
// 检测图中的环
// edges = [
//  [1, 3],
//  [2, 3, 4],
//  [0],
//  [],
//  [2, 5],
//  [],
// ]
public class Problem_000_CycleInGraph {

    public class Node {
        public int value;
        public ArrayList<Node> nexts; // 下级节点
        public Node(int value) {
            this.value = value;
            nexts = new ArrayList<>();
        }
    }

    public boolean circle = false;

    public  boolean cycleInGraph(int[][] edges) {
        HashMap<Integer, Node> nodes = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            if (!nodes.containsKey(i)) {
                nodes.put(i, new Node(i));
            }
            Node fromNode = nodes.get(i);
            for (int j = 0; j < edges[i].length; j++) {
                if (!nodes.containsKey(edges[i][j])) {
                    nodes.put(edges[i][j], new Node(edges[i][j]));
                }
                Node toNode = nodes.get(edges[i][j]);
                fromNode.nexts.add(toNode);
            }
        }
        // 哪些点已经走过了
        for(Node node : nodes.values()) { // 随便挑了一个点
            // node 是开始点, 从每一个点做 DFS
            HashSet<Node> nodeSet = new HashSet<>();
            nodeSet.add(node);
            dfs(node, nodeSet);
            if (circle) {
                return true;
            }
        }
        return false;
    }

    private void dfs(Node start, HashSet<Node> nodeSet) {
        if(start == null) {
            return;
        }
        for(Node node : start.nexts) {
            if(nodeSet.contains(node)) {
                circle = true;
                break;
            }
            nodeSet.add(node);
            dfs(node, nodeSet);
            nodeSet.remove(node);
        }
    }

    public static void main(String[] args) {
        Problem_000_CycleInGraph sl = new Problem_000_CycleInGraph();
        int[][] edges = new int[][] {
                {1, 3},
                {2, 3, 4},
                {0},
                {},
                {2, 5},
                {}
        };
//        int[][] edges = new int[][] {
//                {1, 2},
//                {2},
//                {}
//        };
        boolean ans = sl.cycleInGraph(edges);
        System.out.println(ans);
    }
}
