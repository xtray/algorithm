package _LintCode;

public class Problem_1360_SymmetricTree {

    public static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    public boolean isSymmetric(TreeNode root) {
        if(root == null) {
            return true;
        }
        return process(root, root);
    }

    private boolean process(TreeNode node1, TreeNode node2) {
        if(node1 == null && node2 == null) {
            return true;
        }
        if(node1 == null || node2 == null) {
            return false;
        }
        return node1.val == node2.val &&
                process(node1.left, node2.right) &&
                process(node1.right, node2.left);
    }
}
