package LeetCode;

public class Problem_558_QuadTree {

    static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {
        }

        public Node(boolean _val, boolean _isLeaf) {
            val = _val;
            isLeaf = _isLeaf;
        }

        public Node(boolean _val, boolean _isLeaf, Node _topLeft, Node _topRight, Node _bottomLeft, Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    }

    // ref: https://leetcode.cn/problems/logical-or-of-two-binary-grids-represented-as-quad-trees/solution/si-cha-shu-jiao-ji-by-leetcode-solution-wy1u/
    public Node intersect(Node qt1, Node qt2) {
        if (qt1.isLeaf) {
            if (qt1.val) {
                return new Node(true, true);
            }
            return new Node(qt2.val, qt2.isLeaf, qt2.topLeft, qt2.topRight, qt2.bottomLeft, qt2.bottomRight);
        }
        if (qt2.isLeaf) {
            return intersect(qt2, qt1);
        }
        // qt1, qt2都不是叶子节点, 4个子树分别合并
        Node n1 = intersect(qt1.topLeft, qt2.topLeft);
        Node n2 = intersect(qt1.topRight, qt2.topRight);
        Node n3 = intersect(qt1.bottomLeft, qt2.bottomLeft);
        Node n4 = intersect(qt1.bottomRight, qt2.bottomRight);
        boolean isLeaf = n1.isLeaf && n2.isLeaf && n3.isLeaf && n4.isLeaf;
        // 如果是全0, 或者全1, 需要合并
        boolean isSame = n1.val == n2.val && n1.val == n3.val && n1.val == n4.val;
        if (isLeaf && isSame) { // 满足叶子节点, 合并
            return new Node(true, true);
        }
        return new Node(false, false, n1, n2, n3, n4);
    }
}
