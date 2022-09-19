package LeetCode;

import java.util.ArrayDeque;

public class Problem_129_NodeSum {

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

    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        process(root, 0);
        return sum;
    }

    private int sum = 0;

    // 返回root出发到叶子节点的和
    private void process(TreeNode root, int path) {
        if (root.left == null && root.right == null) {
            sum += root.val + path * 10;
            return;
        }
        int cur = root.val + path * 10;
        if (root.left != null) {
            process(root.left, cur);
        }
        if (root.right != null) {
            process(root.right, cur);
        }
    }

    public int sumNumbers1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dfs(root, 0);
    }

    // 返回root出发到所有叶子节点的和
    private int dfs(TreeNode root, int path) {
        if(root == null) {
            return 0;
        }
        int cur = root.val + path * 10;
        if (root.left == null && root.right == null) {
            return cur;
        }
        return dfs(root.left, cur) + dfs(root.right, cur);
    }

    public int sumNumbers2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        ArrayDeque<TreeNode> nodeQueue = new ArrayDeque<>();
        ArrayDeque<Integer> numQueue = new ArrayDeque<>();
        nodeQueue.offer(root);
        numQueue.offer(root.val);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int num = numQueue.poll();
            TreeNode left = node.left, right = node.right;
            if (left == null && right == null) {
                sum += num;
            } else {
                if (left != null) {
                    nodeQueue.offer(left);
                    numQueue.offer(num * 10 + left.val);
                }
                if (right != null) {
                    nodeQueue.offer(right);
                    numQueue.offer(num * 10 + right.val);
                }
            }
        }
        return sum;
    }


    public static void main(String[] args) {
        TreeNode node = new TreeNode(4);
        node.left = new TreeNode(9);
        node.right = new TreeNode(0);
        node.left.left = new TreeNode(5);
        node.left.right = new TreeNode(1);
        var ans = new Problem_129_NodeSum().sumNumbers(node);
        System.out.println(ans);
    }


}
