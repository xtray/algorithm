package LeetCode;

public class Problem_111_MinDepth {

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

    // 二叉树的递归套路的解法
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root);
    }

    // 返回以root为头结点的二叉树的最小深度
    private int process(TreeNode root) {
        if (root.left == null && root.right == null) {
            return 1;
        }
        int leftH = Integer.MAX_VALUE;
        if (root.left != null) {
            leftH = process(root.left);
        }
        int rightH = Integer.MAX_VALUE;
        if (root.right != null) {
            rightH = process(root.right);
        }
        return Math.min(leftH, rightH) + 1;
    }

    // TODO: morris遍历的版本,
    // 很难..

}
