package LeetCode.Jianzhi;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Problem_JZ32_I_LevelOrder {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int[] levelOrder(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return new int[0];
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            ans.add(cur.val);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        int[] res = new int[ans.size()];
        int idx = 0;
        for (int num : ans) {
            res[idx++] = num;
        }
        return res;
    }
}
