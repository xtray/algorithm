package _Contest.LCP.LCP2022_FALL_TEAM;

import java.util.ArrayDeque;

public class Problem_B {

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

    public TreeNode expandBinaryTree(TreeNode root) {
        if (root == null) {
            return root;
        }

        process(root);
        return root;

    }

    private TreeNode process(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            TreeNode left = root.left;
            root.left = new TreeNode(-1);
            root.left.left = process(left);
        }
        if (root.right != null) {
            TreeNode right = root.right;
            root.right = new TreeNode(-1);
            root.right.right = process(right);
        }
        return root;
    }
}
