package _DailyTarget;

import java.util.*;

public class Problem_742_ClosestLeaf {

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

    private TreeNode targetNode = null;

    public int findClosestLeaf(TreeNode root, int k) {
        Map<TreeNode, TreeNode> map = new HashMap<>();
        dfs(root, k, map);
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        Set<TreeNode> set = new HashSet<>();
        set.add(targetNode);
        queue.addLast(targetNode);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.pollFirst();
            if (cur.left == null && cur.right == null) {
                return cur.val;
            }
            if (cur.left != null && !set.contains(cur.left)) {
                set.add(cur.left);
                queue.addLast(cur.left);
            }
            if (cur.right != null && !set.contains(cur.right)) {
                set.add(cur.right);
                queue.addLast(cur.right);
            }
            if (map.get(cur) != null && set.add(map.get(cur))) {
                queue.addLast(map.get(cur));
            }
        }
        return -1;
    }

    private void dfs(TreeNode root, int k, Map<TreeNode, TreeNode> map) {
        if (root == null) return;
        if (root.val == k) targetNode = root;
        if (root.left != null) {
            map.put(root.left, root);
        }
        if (root.right != null) {
            map.put(root.right, root);
        }
        dfs(root.left, k, map);
        dfs(root.right, k, map);
    }
}
