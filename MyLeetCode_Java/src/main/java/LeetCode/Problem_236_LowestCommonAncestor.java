package LeetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Problem_236_LowestCommonAncestor {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    // 用Map记录父子关系
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        dfs(null, root, parent);
        Set<TreeNode> set = new HashSet<>();
        while (p != null) {
            set.add(p);
            p = parent.get(p);
        }
        while (q != null) {
            if (set.contains(q)) {
                return q;
            }
            q = parent.get(q);
        }
        return null;
    }

    private void dfs(TreeNode parent, TreeNode cur, Map<TreeNode, TreeNode> map) {
        if (cur == null) {
            return;
        }
        map.put(cur, parent);
        dfs(cur, cur.left, map);
        dfs(cur, cur.right, map);
    }


    // IMP: 二叉树递归套路
    public static class Info {
        public boolean findA;
        public boolean findB;
        public TreeNode ans;

        public Info(boolean fA, boolean fB, TreeNode an) {
            findA = fA;
            findB = fB;
            ans = an;
        }
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
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
        boolean findA = (x == a) || leftInfo.findA || rightInfo.findA;
        boolean findB = (x == b) || leftInfo.findB || rightInfo.findB;
        TreeNode ans = null;
        if (leftInfo.ans != null) {
            ans = leftInfo.ans;
        } else if (rightInfo.ans != null) {
            ans = rightInfo.ans;
        } else {
            if (findA && findB) {
                ans = x;
            }
        }
        return new Info(findA, findB, ans);
    }
}
