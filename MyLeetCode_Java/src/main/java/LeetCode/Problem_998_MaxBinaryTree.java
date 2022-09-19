package LeetCode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Problem_998_MaxBinaryTree {

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

    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        List<Integer> list = new ArrayList<>();
        inOrder(root, list);
        list.add(val);
        return constructMaximumBinaryTree(list);
    }

    private void inOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);
    }

    // https://leetcode.cn/problems/maximum-binary-tree/
    // 利用 654. 最大二叉树
    public TreeNode constructMaximumBinaryTree(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        for (int num : list) {
            TreeNode node = new TreeNode(num);
            while (!stack.isEmpty() && num > stack.peekFirst().val) {
                // 当前值比栈顶大, 栈顶是当前节点的左侧节点
                node.left = stack.pollFirst(); // 不断刷新, 用最大的覆盖
            }

            if (!stack.isEmpty()) {
                stack.peekFirst().right = node;
            }
            stack.addFirst(node);
        }
        // 返回栈底的元素，也就是最大值
        // 此方法为双端队列的方法，严格意义并不是栈的方法
        return stack.peekLast();
    }

    public TreeNode insertIntoMaxTree1(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        TreeNode node = new TreeNode(val);
        TreeNode prev = null, cur = root;
        while (cur != null && cur.val > val) {
            prev = cur;
            cur = cur.right;
        }
        if (prev == null) {
            node.left = cur;
            return node;
        } else {
            prev.right = node;
            node.left = cur;
            return root;
        }
    }

}
