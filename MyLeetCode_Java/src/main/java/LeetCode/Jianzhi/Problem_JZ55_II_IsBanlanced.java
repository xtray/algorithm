package LeetCode.Jianzhi;

public class Problem_JZ55_II_IsBanlanced {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static class Info {
        private int height;
        private boolean isBalanced;

        public Info(int h, boolean b) {
            height = h;
            isBalanced = b;
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
            return new Info(0, true);
        }
        Info left = process(root.left);
        Info right = process(root.right);
        int height = Math.max(left.height, right.height) + 1;
        boolean isBanlanced = true;
        if (!left.isBalanced || !right.isBalanced) {
            isBanlanced = false;
        }
        if (Math.abs(left.height - right.height) > 1) {
            isBanlanced = false;
        }
        return new Info(height, isBanlanced);
    }
}
