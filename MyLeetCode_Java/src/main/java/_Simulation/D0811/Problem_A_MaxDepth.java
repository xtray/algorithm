package _Simulation.D0811;

import java.util.List;

public class Problem_A_MaxDepth {
    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;


    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        for (Node child : root.children) {
            int cur = maxDepth(child);
            depth = Math.max(depth, cur);
        }
        return depth + 1;
    }

}
