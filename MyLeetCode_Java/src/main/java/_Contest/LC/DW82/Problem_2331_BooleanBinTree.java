package _Contest.LC.DW82;

public class Problem_2331_BooleanBinTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean evaluateTree1(TreeNode root) {
        if (root == null) {
            return false;
        }
        return process(root).value;
    }

    public static class Info {
        boolean value;

        public Info(boolean v) {
            value = v;
        }
    }

    private Info process(TreeNode root) {
        if (root == null) {
            return null;
        }
        Info left = process(root.left);
        Info right = process(root.right);
        if (left == null && right == null) {
            return new Info(root.val == 1);
        }
        boolean cur = false;
        if (left != null && right != null) {
            if (root.val == 2) {
                cur = left.value | right.value;
            } else if (root.val == 3) {
                cur = left.value & right.value;
            }
        }
        return new Info(cur);
    }

    // public boolean evaluateTree(TreeNode root) {
    //     if (root.val <= 1) {
    //         return root.val == 1;
    //     }
    //     // 2, 3
    //     boolean left = evaluateTree(root.left);
    //     boolean right = evaluateTree(root.right);
    //     return root.val == 2 ? left | right : left & right;
    // }

    // 通过判断当前节点值是否<=1 来断定是否有子树
    public boolean evaluateTree(TreeNode root) {
        if (root.val <= 1) {
            return root.val == 1;
        }
        // 2, 3
        return root.val == 2 ?
                evaluateTree(root.left) || evaluateTree(root.right) :
                evaluateTree(root.left) && evaluateTree(root.right);
    }
}
