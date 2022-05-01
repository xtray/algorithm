package LeetCode;

public class Problem_112_PathSum {
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

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        return process(root, targetSum);
    }

    private boolean process(TreeNode root, int remain) {
        if (root.left == null && root.right == null) {
            return root.val == remain;
        }
        boolean p1 = false;
        if (root.left != null) {
            p1 = process(root.left, remain - root.val);
        }
        boolean p2 = false;
        if (root.right != null) {
            p2 = process(root.right, remain - root.val);
        }
        return p1 || p2;
    }


}
