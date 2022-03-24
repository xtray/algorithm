package LeetCode;

import java.util.HashSet;
import java.util.Set;

// TODO: 改非递归 Morris

public class Problem_653_FindTarget {
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

    public boolean findTarget(TreeNode root, int k) {
        if(root == null) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        return process(root, k, set);
    }

    private boolean process(TreeNode root, int k, Set<Integer> set) {
        if(root == null) {
            return false;
        }
        int x = k - root.val;
        if(set.contains(x)) {
            return true;
        }
        set.add(root.val);
        boolean p1 = process(root.left, k, set);
        boolean p2 = process(root.right, k, set);
        return p1 || p2;
    }
}
