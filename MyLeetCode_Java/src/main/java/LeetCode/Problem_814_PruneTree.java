package LeetCode;

public class Problem_814_PruneTree {

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

    public TreeNode pruneTree0(TreeNode root) {
        if (root == null) {
            return null;
        }
        return process(root) == 0 ? null : root;
    }

    private int process(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root.val;
        }
        int left = 0;
        int right = 0;
        if (root.left != null) {
            left = process(root.left);
        }
        if (left == 0) {
            root.left = null;
        }
        if (root.right != null) {
            right = process(root.right);
        }
        if (right == 0) {
            root.right = null;
        }
        return left + right + root.val;
    }

    // 题目要求剪除二叉树中所有节点的值为 0 的子树，那么怎么知道当前二叉树所有节点的值为0，使用后续遍历无疑了
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        // 当左右节点都为空且当前节点的值为0的情况下，即可剪枝
        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }
        return root;
    }

}
