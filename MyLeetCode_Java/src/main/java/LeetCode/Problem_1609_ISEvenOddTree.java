package LeetCode;


import java.util.LinkedList;
import java.util.Queue;

public class Problem_1609_ISEvenOddTree {
    // Definition for a binary tree node.
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

    // 二叉树按层遍历
    public boolean isEvenOddTree(TreeNode root) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if ((level & 1) == 0 && (node.val & 1) != 1) { // 偶数层都是奇数
                    return false;
                }
                if ((level & 1) == 1 && (node.val & 1) != 0) { // 奇数层, 都是偶数
                    return false;
                }
                if ((level & 1) == 0) {
                    arr[i] = node.val;
                } else {
                    arr[size - i - 1] = node.val;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            for (int i = 1; i < size; i++) {
                if (arr[i] <= arr[i - 1]) {
                    return false;
                }
            }
            level++;
        }
        return true;
    }

    public boolean isEvenOddTree2(TreeNode root) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean isEven = true; // 偶数层 flag
        while (!queue.isEmpty()) {
            int size = queue.size();
            int pre = isEven ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (isEven && ((node.val & 1) != 1 || node.val <= pre)) { // 偶数层都是奇数
                    return false;
                }
                if (!isEven && ((node.val & 1) != 0 || node.val >= pre)) { // 奇数层, 都是偶数
                    return false;
                }
                pre = node.val;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            isEven = !isEven;
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(7);

        // TreeNode root = new TreeNode(1);
        // root.left = new TreeNode(10);
        // root.right = new TreeNode(4);
        // root.left.left = new TreeNode(3);
        // root.left.left.left = new TreeNode(12);
        // root.left.left.right = new TreeNode(8);
        // root.right.left = new TreeNode(7);
        // root.right.left.left = new TreeNode(6);
        // root.right.right = new TreeNode(8);
        // root.right.right.right = new TreeNode(2);
        var res = new Problem_1609_ISEvenOddTree().isEvenOddTree(root);
        System.out.println(res);
    }

}
