package LeetCode;

import java.util.*;

public class Problem_623_AddOneRow {

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

    public static TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (root == null) {
            return root;
        }

        if (depth == 1) {
            TreeNode head = new TreeNode(val);
            head.left = root;
            return head;
        }

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int level = 1;
        depth--;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (level == depth) {
                    TreeNode preLeft = cur.left;
                    TreeNode left = new TreeNode(val);
                    cur.left = left;
                    left.left = preLeft;
                    TreeNode preRight = cur.right;
                    TreeNode right = new TreeNode(val);
                    cur.right = right;
                    right.right = preRight;
                    // 到达指定楼层时, 不会再向队列追加, 下次que就空了, 就退出了
                } else {
                    if (cur.left != null) {
                        queue.add(cur.left);
                    }
                    if (cur.right != null) {
                        queue.add(cur.right);
                    }
                }
            }
            level++;
            if (level > depth) break;
        }
        return root;
    }

    public static void levelPrint(TreeNode root) {
        if (root == null) return;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                System.out.print(cur == null ? "null, " : cur.val + ", ");
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        addOneRow(root, 5, 4);
        levelPrint(root);
        System.out.println();
    }
}
