package LeetCode;

import java.util.ArrayDeque;

public class Problem_1302_DeepestLeavesSum {

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

    private static int sum = 0;
    private static int maxLevel = -1;

    public int deepestLeavesSum(TreeNode root) {
        sum = 0;
        maxLevel = -1;
        if (root == null) {
            return 0;
        }
        dfs(root, 0);
        return sum;
    }

    private void dfs(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (level > maxLevel) {
            maxLevel = level;
            sum = root.val;
        } else if (level == maxLevel) {
            sum += root.val;
        }
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);
    }

    public int deepestLeavesSum1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.pollFirst();
                sum += cur.val;
                if (cur.left != null) {
                    queue.addLast(cur.left);
                }
                if (cur.right != null) {
                    queue.addLast(cur.right);
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(7);
        root.left.left = new TreeNode(2);
        root.left.left.left = new TreeNode(9);
        root.left.right = new TreeNode(7);
        root.left.right.left = new TreeNode(1);
        root.left.right.right = new TreeNode(4);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(2);
        root.right.right.right = new TreeNode(5);
        var ans = new Problem_1302_DeepestLeavesSum().deepestLeavesSum(root);
        System.out.println(ans);

        var ans1 = new Problem_1302_DeepestLeavesSum().deepestLeavesSum1(root);
        System.out.println(ans1);
    }
}
