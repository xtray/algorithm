package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_145_PostOrderTraversal {

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

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        postOrder(root, ans);
        return ans;
    }

    private void postOrder(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        postOrder(root.left, ans);
        postOrder(root.right, ans);
        ans.add(root.val);
    }

    // Morris实现后序遍历
    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        postOrderMorris(root, ans);
        return ans;
    }

    private void postOrderMorris(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }
                mostRight.right = null;
                // 第二次回到自己的时候, 逆序打印自己的左树右边界
                // NOTE: !!注意顺序, 要放在置空指针之后
                printEdge(cur.left, ans);
            }
            cur = cur.right;
        }
        // NOTE: 最后不要忘了逆序打印整棵树的右边界
        printEdge(root, ans);
    }

    // 打印node节点的左树右边界
    private static void printEdge(TreeNode head, List<Integer> ans) {
        TreeNode tail = reverseEdge(head);
        TreeNode cur = tail;
        while (cur != null) {
            ans.add(cur.val);
            cur = cur.right;
        }
        // 打完之后再翻转回来
        reverseEdge(tail);
    }

    // 链表翻转
    private static TreeNode reverseEdge(TreeNode head) {
        TreeNode pre = null;
        TreeNode next = null;
        while (head != null) {
            next = head.right;
            head.right = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
