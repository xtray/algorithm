package LeetCode.JZOffer;

// NOTE: 回看!!

public class Problem_JZ27_MirrorTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        return process(root);
    }

    // 左树跟右树交换
    private TreeNode process(TreeNode root) {
        if (root.left == null && root.right == null) { // 叶子节点不用交换
            return root;
        }
        if (root.left == null || root.right == null) { // 有一个分支为null
            if (root.left == null) {
                root.left = process(root.right);
                root.right = null;
            } else {
                root.right = process(root.left);
                root.left = null;
            }
            return root;
        }
        // 都不是空
        TreeNode tmp = root.left;
        root.left = process(root.right);
        root.right = process(tmp);
        return root;
    }

    public TreeNode mirrorTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = root.left;
        root.left = mirrorTree2(root.right);
        root.right = mirrorTree2(left);
        return root;
    }

}
