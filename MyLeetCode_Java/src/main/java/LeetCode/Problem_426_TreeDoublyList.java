package LeetCode;

public class Problem_426_TreeDoublyList {
    public static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    public class NodeInfo {
        Node start;
        Node end;

        public NodeInfo(Node s, Node e) {
            start = s;
            end = e;
        }
    }

    // 二叉树递归套路
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        NodeInfo headInfo = process(root);
        headInfo.end.right = headInfo.start;
        headInfo.start.left = headInfo.end;

        return headInfo.start;

    }

    private NodeInfo process(Node x) {
        if (x == null) {
            return new NodeInfo(null, null);
        }
        NodeInfo leftInfo = process(x.left);
        NodeInfo rightInfo = process(x.right);
        if (leftInfo.end != null) {
            leftInfo.end.right = x;
        }
        x.left = leftInfo.end;
        x.right = rightInfo.start;
        if (rightInfo.start != null) {
            rightInfo.start.left = x;
        }
        return new NodeInfo(leftInfo.start != null ? leftInfo.start : x, rightInfo.end != null ? rightInfo.end : x);
    }
}
