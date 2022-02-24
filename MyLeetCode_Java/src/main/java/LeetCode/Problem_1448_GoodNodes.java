package LeetCode;

import javax.swing.tree.TreeNode;

public class Problem_1448_GoodNodes {
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


    public int goodNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return process(root, root.val);
    }

    // 返回当前节点出发的好节点数目
    private int process(TreeNode cur, int maxVal) {
        if (cur == null) {
            return 0;
        }
        int ans = 0;
        int curMax = Math.max(maxVal, cur.val);
        int left = process(cur.left, curMax);
        int right = process(cur.right, curMax);
        if (curMax == cur.val) {
            ans++;
        }
        return ans + left + right;
    }
}
