package _LintCode;

import java.util.*;

public class Problem_854_ClosestLeaf {
    public static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    private TreeNode targetNode = null;

    public int findClosestLeaf(TreeNode root, int k) {
        Map<TreeNode, TreeNode> faMap = new HashMap<>();
        faMap.put(root, null);
        dfs(root, k, faMap);

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(targetNode);
        Set<TreeNode> set = new HashSet<>();
        while (!queue.isEmpty()) {
            TreeNode cur = queue.pollFirst();
            if (cur.left == null && cur.right == null) {
                return cur.val;
            }
            // 向当前点下级及父亲节点扩散
            // add成功返回true
            if (cur.left != null && set.add(cur.left)) {
                queue.add(cur.left);
            }
            if (cur.right != null && !set.contains(cur.right)) {
                set.add(cur.right);
                queue.add(cur.right);
            }
            if (faMap.get(cur) != null && !set.contains(faMap.get(cur))) {
                set.add(faMap.get(cur));
                queue.add(faMap.get(cur));
            }
        }

        return -1;
    }

    private void dfs(TreeNode root, int k, Map<TreeNode, TreeNode> father) {
        if (root == null) return;

        if (root.val == k) targetNode = root;

        if (root.left != null) {
            father.put(root.left, root);
        }
        if (root.right != null) {
            father.put(root.right, root);
        }

        dfs(root.left, k, father);
        dfs(root.right, k, father);
    }

    public static void main(String[] args) {
        // TreeNode root = new TreeNode(1);
        // root.left = new TreeNode(2);
        // root.left.left = new TreeNode(4);
        // root.left.right = new TreeNode(5);
        // root.right = new TreeNode(2);
        // int k = 4;

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        int k = 1;
        var ans = new Problem_854_ClosestLeaf().findClosestLeaf(root, k);
        System.out.println(ans);
    }

}
