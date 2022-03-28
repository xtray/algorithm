package LeetCode;

import java.util.LinkedList;

public class Problem_404_LeftLeavesSum {

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

    // 解法1: 二叉树的递归套路
    public static class Info {
        public int leftSum;
        public Info(int left) {
            leftSum = left;
        }
    }
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return process(root).leftSum;
    }

    private Info process(TreeNode root) {
        if(root == null) {
            return new Info(0);
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        int curLeft = leftInfo.leftSum + rightInfo.leftSum;
        TreeNode leftNode = root.left;
        if(leftNode != null && leftNode.left == null && leftNode.right == null) {
            curLeft += root.left.val;
        }
        return new Info(curLeft);
    }

    // 解法2: 二叉树按层遍历
    public int sumOfLeftLeaves2(TreeNode root) {
        if(root == null) {
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i< size; i++) {
                TreeNode node = queue.pollFirst();
                if(node.left != null) {
                    if(isLeafNode(node.left)) {
                        ans += node.left.val;
                    }
                    queue.addLast(node.left);
                }
                if(node.right != null) {
                    queue.addLast(node.right);
                }
            }
        }
        return ans;
    }

    private boolean isLeafNode(TreeNode node) {
        if(node == null) {
            return false;
        }
        return node.left == null && node.right == null;
    }
}
