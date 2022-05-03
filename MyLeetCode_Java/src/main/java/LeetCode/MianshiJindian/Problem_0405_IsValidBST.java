package LeetCode.MianshiJindian;

// IMP: 重点基础题

public class Problem_0405_IsValidBST {

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

    // IMP: Morris遍历的解法
    public boolean isValidBST1(TreeNode root) {
        if (root == null) {
            return true;
        }
        TreeNode cur = root;
        TreeNode mostRight = null; // 左树上的最右节点
        TreeNode pre = null; // 前一个节点
        boolean ans = true;
        while (cur != null) {
            mostRight = cur.left; // 先来到左孩子
            if (mostRight != null) { // 左孩子不是空, 找左树最右节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right; // 不断往右
                }
                // mostRight 一定是左树最右节点, 但是有两种情况
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { // mostRight.right == cur
                    mostRight.right = null; // 恢复, 指向空
                }
            }
            // 中序遍历的时机做判断
            // 1. 没有左树向右移动
            // 2. 有左树, 到自己两次
            if (pre != null && pre.val >= cur.val) { // NOTE: >= 都不是, 要严格递增
                ans = false;
            }
            pre = cur;
            cur = cur.right;
        }
        return ans;
    }

    // IMP: 二叉树递归套路的写法
    // 以X为头的整棵树的信息
    public static class Info {
        public int maxVal;
        public int minVal;
        public boolean isBST;

        public Info(int max, int min, boolean bst) {
            maxVal = max;
            minVal = min;
            isBST = bst;
        }
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root).isBST;
    }

    private Info process(TreeNode root) {
        if (root == null) { // 空树不好想返回值, 直接返回空,后面专门对空做判断
            return null;
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        // 生成我自己的三个信息
        // 更新最大值
        int maxVal = root.val;
        if (leftInfo != null) {
            maxVal = Math.max(maxVal, leftInfo.maxVal);
        }
        if (rightInfo != null) {
            maxVal = Math.max(maxVal, rightInfo.maxVal);
        }
        // 更新最小值
        int minVal = root.val;
        if (leftInfo != null) {
            minVal = Math.min(minVal, leftInfo.minVal);
        }
        if (rightInfo != null) {
            minVal = Math.min(minVal, rightInfo.minVal);
        }
        // 我自己是不是BST
        boolean isBST = true;
        if ((leftInfo != null && !leftInfo.isBST) || (rightInfo != null && !rightInfo.isBST)) {
            isBST = false;
        }
        if ((leftInfo != null && root.val <= leftInfo.maxVal) || (rightInfo != null && root.val >= rightInfo.minVal)) {
            isBST = false;
        }
        return new Info(maxVal, minVal, isBST);
    }

    // NOTE: 错的!, 当节点值为最值时会出错
    private Info process2(TreeNode root) {
        if (root == null) { // NOTE: 不能返回极值, 应该返回null
            return new Info(Integer.MIN_VALUE, Integer.MAX_VALUE, true);
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        boolean isBST = true;
        if (!leftInfo.isBST || !rightInfo.isBST) {
            isBST = false;
        }
        if (root.val <= leftInfo.maxVal || root.val >= rightInfo.minVal) {
            isBST = false;
        }
        int maxVal = Math.max(leftInfo.maxVal, rightInfo.maxVal);
        maxVal = Math.max(maxVal, root.val);
        int minVal = Math.min(leftInfo.minVal, rightInfo.minVal);
        minVal = Math.min(minVal, root.val);
        return new Info(maxVal, minVal, isBST);
    }

}
