package LeetCode.MianshiJindian;

// LC285
// IMP: Morris遍历

public class Problem_0406_InorderSuccessor {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        TreeNode cur = root;
        TreeNode mostRight = null;
        TreeNode pre = null;
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
            }
            // 中序遍历的改造时间点, 第二次到一个节点
            if (pre == p) {
                return cur;
            } else {
                pre = cur;
            }
            cur = cur.right;
        }
        return null;
    }
}
