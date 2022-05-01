package _LintCode;

// IMP: 判定满二叉树, 重要!!

// NOTE: 满二叉树有国内, 国外两种不同的定义
// ref: https://baike.baidu.com/item/%E6%BB%A1%E4%BA%8C%E5%8F%89%E6%A0%91

import java.util.ArrayDeque;

public class Problem_726_FullBT {

    public static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    // NOTE: 国外的满二叉树定义
    // 如果一棵二叉树的结点要么是叶子结点，要么它有两个子结点，这样的树就是满二叉树。

    public boolean isFullTree(TreeNode root) {

        if (root == null) {
            return true;
        }
        // NOTE: 学习异或的写法
        if (root.left == null ^ root.right == null) {
            return false;
        }
        return isFullTree(root.left) && isFullTree(root.right);
    }


    // 国内教程定义：一个二叉树，如果每一个层的结点数都达到最大值，则这个二叉树就是满二叉树。
    // 方法1:
    // 收集整棵树的高度h，和节点数n
    // 只有满二叉树满足 : 2 ^ h - 1 == n
    public static class Info {
        public int height;
        public int nodes; // 节点数

        public Info(int h, int n) {
            height = h;
            nodes = n;
        }
    }

    public boolean isFullTree1(TreeNode root) {
        if (root == null) {
            return true;
        }
        Info all = process(root);
        return (1 << all.height) - 1 == all.nodes;
    }

    private Info process(TreeNode root) {
        if (root == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;
        return new Info(height, nodes);
    }


    // 第二种方法
    // 收集子树是否是满二叉树
    // 收集子树的高度
    // 左树满 && 右树满 && 左右树高度一样 -> 整棵树是满的
    public static boolean isFull2(TreeNode head) {
        if (head == null) {
            return true;
        }
        return process2(head).isFull;
    }

    public static class Record {
        public boolean isFull;
        public int height;

        public Record(boolean f, int h) {
            isFull = f;
            height = h;
        }
    }

    public static Record process2(TreeNode h) {
        if (h == null) {
            return new Record(true, 0);
        }
        Record leftInfo = process2(h.left);
        Record rightInfo = process2(h.right);
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Record(isFull, height);
    }

}
