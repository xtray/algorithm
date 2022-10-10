package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class Problem_933_Cousins {

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

    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }
        Map<Integer, Integer> fa = new HashMap<>();
        Map<Integer, Integer> depth = new HashMap<>();
        process(root, 0, fa, depth);
        return fa.get(x) != fa.get(y) && depth.get(x) == depth.get(y);
    }

    private void process(TreeNode root, int level, Map<Integer, Integer> fa, Map<Integer, Integer> depth) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            fa.put(root.left.val, root.val);
            depth.put(root.left.val, level + 1);
            process(root.left, level + 1, fa, depth);
        }
        if (root.right != null) {
            fa.put(root.right.val, root.val);
            depth.put(root.right.val, level + 1);
            process(root.right, level + 1, fa, depth);
        }
    }
}
