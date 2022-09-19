package LeetCode;

// tag: LCA
// ref: Problem_236_LowestCommonAncestor

public class Problem_1650_LCA {
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

    public Node lowestCommonAncestor(Node p, Node q) {
        Node a = p;
        Node b = q;
        while (a != b) {
            // a 向上走一步，如果走到根节点，转到 q 节点
            a = a == null ? q : a.parent;
            // b 向上走一步，如果走到根节点，转到 p 节点
            b = b == null ? p : b.parent;
        }
        return a;
    }

}
