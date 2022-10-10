package _LintCode;

import java.util.ArrayDeque;

public class Problem_1197_BottomLeftVal {

    public static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return 0;
        }
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        TreeNode left = root;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.pollFirst();
                left = cur.left;
                if (cur.right != null) {
                    queue.addLast(cur.right);
                }
                if (cur.left != null) {
                    queue.addLast(cur.left);
                }
            }
        }
        return left.val;
    }
}
