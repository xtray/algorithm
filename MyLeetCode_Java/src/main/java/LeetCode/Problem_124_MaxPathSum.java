package LeetCode;

public class Problem_124_MaxPathSum {

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
        public int maxSum; // 以当前节点为头的所有路径里的最大路径和
        public int maxSumFromHead; // 包含当前节点的最大路径和
        public Info(int path, int head) {
            maxSum = path;
            maxSumFromHead = head;
        }
    }

    // 二叉树的递归套路
    public int maxPathSum(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return process(root).maxSum;
    }

    private Info process(TreeNode root) {
        if(root == null) {
            return null;
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        int p1 = Integer.MIN_VALUE;
        if(leftInfo != null) {
            p1 = leftInfo.maxSum;
        }
        int p2 = Integer.MIN_VALUE;
        if(rightInfo != null) {
            p2 = rightInfo.maxSum;
        }
        int p3 = root.val;
        int p4 = Integer.MIN_VALUE;
        if (leftInfo != null) {
            p4 = root.val + leftInfo.maxSumFromHead;
        }
        int p5 = Integer.MIN_VALUE;
        if (rightInfo != null) {
            p5 = root.val + rightInfo.maxSumFromHead;
        }
        int p6 = Integer.MIN_VALUE;
        if (leftInfo != null && rightInfo != null) {
            p6 = root.val + leftInfo.maxSumFromHead + rightInfo.maxSumFromHead;
        }
        int maxSum = Math.max(Math.max(Math.max(p1, p2), Math.max(p3, p4)), Math.max(p5, p6));
        int maxSumFromHead = Math.max(p3, Math.max(p4, p5));
        return new Info(maxSum, maxSumFromHead);
    }

}
