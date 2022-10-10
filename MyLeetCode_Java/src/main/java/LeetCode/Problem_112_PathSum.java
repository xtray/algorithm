package LeetCode;

import java.util.ArrayDeque;

public class Problem_112_PathSum {
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

    public boolean hasPathSum(TreeNode root, int t) {
        if (root == null) {
            return false;
        }
        return dfs(root, t);
    }

    private boolean dfs(TreeNode root, int rest) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            // return rest == 0;
            return root.val == rest;
        }
        return dfs(root.left, rest - root.val) || dfs(root.right, rest - root.val);
    }

    public boolean hasPathSum2(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        return process(root, targetSum);
    }

    private boolean process(TreeNode root, int remain) {
        if (root.left == null && root.right == null) {
            return root.val == remain;
        }
        boolean p1 = false;
        if (root.left != null) {
            p1 = process(root.left, remain - root.val);
        }
        boolean p2 = false;
        if (root.right != null) {
            p2 = process(root.right, remain - root.val);
        }
        return p1 || p2;
    }

    private static class Pair {
        public TreeNode node;
        public int val;

        public Pair(TreeNode n, int v) {
            node = n;
            val = v;
        }
    }

    public boolean hasPathSum3(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        ArrayDeque<Pair> queue = new ArrayDeque<>();
        queue.addLast(new Pair(root, targetSum));
        while (!queue.isEmpty()) {
            Pair cur = queue.pollFirst();
            TreeNode node = cur.node;
            int rest = cur.val;
            if (node.left == null && node.right == null && node.val == rest) {
                return true;
            }
            if (node.left != null) {
                queue.addLast(new Pair(node.left, rest - node.val));
            }
            if (node.right != null) {
                queue.addLast(new Pair(node.right, rest - node.val));
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        int k = 1;
        var ans = new Problem_112_PathSum().hasPathSum(root, k);
        System.out.println(ans);
    }


}
