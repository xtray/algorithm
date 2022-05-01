package LeetCode;

// IMP: 多看!!
public class Problem_101_SymmetricTree {

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

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return check(root, root);
    }

    private boolean check(TreeNode h1, TreeNode h2) {
        if (h1 == null && h2 == null) {
            return true;
        }
        if (h1 == null || h2 == null) {
            return false;
        }
        return h1.val == h2.val && check(h1.left, h2.right) && check(h1.right, h2.left);
    }

}
