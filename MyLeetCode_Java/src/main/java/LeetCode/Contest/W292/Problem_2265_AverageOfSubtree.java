package LeetCode.Contest.W292;

// https://leetcode.cn/problems/count-nodes-equal-to-average-of-subtree/

public class Problem_2265_AverageOfSubtree {

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
        public int nodeCnt;
        public int nodeSum;

        public Info(int cnt, int sum) {
            nodeCnt = cnt;
            nodeSum = sum;
        }
    }

    private int totalCnt = 0;

    public int averageOfSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Info info = process(root);
        return totalCnt;
    }

    private Info process(TreeNode root) {
        if (root == null) {
            return null;
        }
        Info left = process(root.left);
        Info right = process(root.right);
        int nodeSum = 0;
        int nodeCnt = 0;
        if (left != null) {
            nodeSum += left.nodeSum;
            nodeCnt += left.nodeCnt;
        }
        if (right != null) {
            nodeSum += right.nodeSum;
            nodeCnt += right.nodeCnt;
        }
        nodeCnt++;
        nodeSum += root.val;
        if (root.val == nodeSum / nodeCnt) {
            totalCnt++;
        }
        return new Info(nodeCnt, nodeSum);
    }
}
