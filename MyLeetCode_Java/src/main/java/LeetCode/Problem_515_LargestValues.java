package LeetCode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Problem_515_LargestValues {

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

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int maxVal = queue.peekFirst().val;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.pollFirst();
                maxVal = Math.max(maxVal, cur.val);
                if (cur.left != null) {
                    queue.addLast(cur.left);
                }
                if (cur.right != null) {
                    queue.addLast(cur.right);
                }
            }
            ans.add(maxVal);
        }
        return ans;
    }
}
