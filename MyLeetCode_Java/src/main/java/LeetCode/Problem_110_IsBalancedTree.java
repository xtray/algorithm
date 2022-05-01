package LeetCode;

public class Problem_110_IsBalancedTree {

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

    public static class Info {
        public boolean isBalanced;
        public int maxHeight;

        public Info(boolean b, int h) {
            isBalanced = b;
            maxHeight = h;
        }
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root).isBalanced;
    }

    private Info process(TreeNode root) {
        if (root == null) {
            return new Info(true, 0);
        }
        Info left = process(root.left);
        Info right = process(root.right);
        int maxHeight = Math.max(left.maxHeight, right.maxHeight) + 1;
        boolean balanced = left.isBalanced && right.isBalanced &&
                (Math.abs(left.maxHeight - right.maxHeight) <= 1);
        return new Info(balanced, maxHeight);
    }

    // DFS
    public boolean isBalanced2(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(height(root.left)-height(root.right))<=1 &&
                isBalanced2(root.left) && isBalanced2(root.right);
    }

    // 返回以root为头的数的高度
    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }
}
