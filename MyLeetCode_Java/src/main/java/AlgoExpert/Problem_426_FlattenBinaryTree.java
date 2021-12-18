package AlgoExpert;

public class Problem_426_FlattenBinaryTree {

    public static class Info {
        BinaryTree start;
        BinaryTree end;

        public Info(BinaryTree s, BinaryTree e) {
            start = s;
            end = e;
        }
    }

    public static BinaryTree flattenBinaryTree(BinaryTree root) {
        if (root == null) {
            return null;
        }
        Info headInfo = process(root);
        return headInfo.start;
    }

    private static Info process(BinaryTree x) {
        if (x == null) {
            return new Info(null, null);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        if (leftInfo.end != null) {
            leftInfo.end.right = x;
        }
        x.left = leftInfo.end;
        x.right = rightInfo.start;
        if (rightInfo.start != null) {
            rightInfo.start.left = x;
        }
        return new Info(leftInfo.start != null ? leftInfo.start : x, rightInfo.end != null ? rightInfo.end : x);
    }

    // This is the class of the input root. Do not edit it.
    static class BinaryTree {
        int value;
        BinaryTree left = null;
        BinaryTree right = null;

        public BinaryTree(int value) {
            this.value = value;
        }
    }
}
