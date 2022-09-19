package _Exercise;

public class Problem_98_ValidBST {
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

    // 二叉树的递归套路
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

    public boolean isValidBST1(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root).isBST;
    }

    private Info process(TreeNode root) {
        if (root == null) {
            return null;
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        boolean isBST = true;
        if ((leftInfo != null && !leftInfo.isBST) || (rightInfo != null && !rightInfo.isBST)) {
            isBST = false;
        }
        if ((leftInfo != null && root.val <= leftInfo.maxVal) || (rightInfo != null && root.val >= rightInfo.minVal)) {
            isBST = false;
        }
        int maxVal = root.val;
        if(leftInfo!=null) {
            maxVal = Math.max(maxVal, leftInfo.maxVal);
        }
        if(rightInfo!=null) {
            maxVal = Math.max(maxVal, rightInfo.maxVal);
        }
        int minVal = root.val;
        if(leftInfo!=null) {
            minVal = Math.min(minVal, leftInfo.minVal);
        }
        if(rightInfo!=null) {
            minVal = Math.min(minVal, rightInfo.minVal);
        }
        return new Info(maxVal, minVal, isBST);
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return false;
        }
        TreeNode cur = root;
        TreeNode mostRight = null;
        TreeNode pre = null;
        boolean ans = true;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) { // 左孩子不是空, 找左树最右节点
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
            // 1. 没有左树向右移动
            // 2. 有左树, 到自己两次
            if (pre != null && pre.val >= cur.val) {
                ans = false;
            }
            pre = cur;
            cur = cur.right;
        }
        return ans;
    }

}
