package _LintCode;

// IMP: 二叉树递归讨论, 判断是否是CBT, 非常重要!!

import java.util.ArrayDeque;

public class Problem_467_CBT {
    public static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    // 二叉树按层遍历的解法
    public boolean isComplete(TreeNode root) {
        if(root == null) {
            return true;
        }
        TreeNode left = null;
        TreeNode right = null;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        boolean leaf = false; // 是否遇到过左右两个孩子不双全的节点
        while (!queue.isEmpty()) {
            TreeNode cur = queue.pollFirst();
            if(leaf && (cur.left != null || cur.right != null)) {
                // 情况2: 遇到第一个叶节点以后, 又碰到非叶子节点
                return false;
            }
            if(cur.left == null && cur.right != null) {
                // 情况1: 有右无左, 返回false
                return false;
            }
            if(cur.left != null) {
                queue.addLast(cur.left);
            }
            if(cur.right != null) {
                queue.addLast(cur.right);
            }
            if(cur.left == null || cur.right == null) {
                leaf = true; // 第一次遇到左右两个孩子不双全的节点
            }
        }
        return true;
    }
}
