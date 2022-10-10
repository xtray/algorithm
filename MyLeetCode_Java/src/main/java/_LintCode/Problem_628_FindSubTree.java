package _LintCode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Problem_628_FindSubTree {

    public static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }


    private static class Info {
        private int maxVal; // 整体最大值
        private int sumWithHead; // 以当前节点为头的子树累加和
        private TreeNode maxNode;


        public Info(int maxVal, int sumWithHead, TreeNode node) {
            this.maxVal = maxVal;
            this.sumWithHead = sumWithHead;
            this.maxNode = node;
        }
    }

    public TreeNode findSubtree(TreeNode root) {
        if (root == null) {
            return null;
        }

        Info info = process(root);
        return info.maxNode;
    }

    private Info process(TreeNode root) {
        if (root == null) {
            return new Info(Integer.MIN_VALUE, Integer.MIN_VALUE, null);
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);

        // 可能性1: 以当前节点为头
        int sumWithHead = root.val;
        if (leftInfo.maxNode != null) {
            sumWithHead += leftInfo.sumWithHead;
        }
        if (rightInfo.maxNode != null) {
            sumWithHead += rightInfo.sumWithHead;
        }
        TreeNode maxNode = root;
        // 可能性2: 不以当前节点为头
        int curMaxVal = sumWithHead;
        if (leftInfo.maxVal > sumWithHead) {
            curMaxVal = leftInfo.maxVal;
            maxNode = leftInfo.maxNode;
        }
        if (rightInfo.maxVal > curMaxVal) {
            curMaxVal = rightInfo.maxVal;
            maxNode = rightInfo.maxNode;
        }
        return new Info(curMaxVal, sumWithHead, maxNode);
    }

    // DFS
    public TreeNode maxNode = null;
    public int maxVal = Integer.MIN_VALUE;

    public TreeNode findSubtree1(TreeNode root) {
        dfs(root);
        return maxNode;
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftVal = dfs(root.left);
        int rightVal = dfs(root.right);

        if (maxNode == null || leftVal + rightVal + root.val > maxVal) {
            maxVal = leftVal + rightVal + root.val;
            maxNode = root;
        }

        return leftVal + rightVal + root.val;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(-5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(-4);
        root.right.right = new TreeNode(-5); // 2
        var ans = new Problem_628_FindSubTree().findSubtree(root);
        printTree(ans);
    }

    private static void printTree(TreeNode root) {
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<TreeNode> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.pollFirst();
                list.add(cur);
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            for (TreeNode node : list) {
                System.out.printf(" %d ", node.val);
            }
            System.out.println();
        }
    }
}
