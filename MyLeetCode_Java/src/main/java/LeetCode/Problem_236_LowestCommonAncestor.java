package LeetCode;

public class Problem_236_LowestCommonAncestor {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class Info {
        public boolean findA;
        public boolean findB;
        public TreeNode ans;

        public Info(boolean fA, boolean fB, TreeNode an) {
            findA = fA;
            findB = fB;
            ans = an;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        return process(root, p, q).ans;
    }

    public Info process(TreeNode x, TreeNode a, TreeNode b) {
        if (x == null) {
            return new Info(false, false, null);
        }
        Info leftInfo = process(x.left, a, b);
        Info rightInfo = process(x.right, a, b);
        boolean findA = (x==a)||leftInfo.findA||rightInfo.findA;
        boolean findB = (x==b)||leftInfo.findB||rightInfo.findB;
        TreeNode ans = null;
        if (leftInfo.ans!=null) {
            ans = leftInfo.ans;
        } else if (rightInfo.ans!=null) {
            ans = rightInfo.ans;
        } else {
            if (findA&&findB) {
                ans = x;
            }
        }
        return new Info(findA, findB, ans);
    }
}
