package LeetCode;

public class Problem_965_UniValTree {

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

    public boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root);
    }

    // NOTE: 注意DFS的写法
    private boolean process(TreeNode root) {
        if(root == null) {
            return true;
        }
        boolean left = true;
        if(root.left != null) {
            if(root.val != root.left.val || !process(root.left)) {
                left = false;
            }
        }
        boolean right = true;
        if(root.right != null) {
            if(root.val != root.right.val || !process(root.right)) {
                right = false;
            }
        }
        return left && right;
    }

    public boolean isUnivalTree1(TreeNode root) {
        if (root == null) {
            return true;
        }
        TreeNode cur = root;
        TreeNode pre = null;
        boolean ans = true;
        while (cur != null) {
            TreeNode mostRight = cur.left;
            if (mostRight != null) { // 有左树
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }
                // mostRight.right == cur
                mostRight.right = null;
            }
            if (pre != null && pre.val != cur.val) {
                ans = false;
            }
            pre = cur;
            cur = cur.right;
        }
        return ans;
    }
}
