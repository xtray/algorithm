package LeetCode;

import java.util.LinkedList;

public class Problem_513_BottomLeftValue {

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

    // BFS
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        int ans = root.val;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
                if (i == 0) {
                    ans = cur.val;
                }
            }
        }
        return ans;
    }

    // DFS
    private int maxDepth = -1;
    private int leftVal = -1;

    public int findBottomLeftValue2(TreeNode root) {
        if (root == null) {
            return -1;
        }
        process(root, 0);
        return leftVal;
    }

    /**
     * NOTE: DFS的做法
     * 判断当前深度 depth 是否为大于最大深度 maxDepth ，若大于，则将当前的节点值赋给 left ，同时更新最大深度的值。
     * 这种做法可以保证，当遇到当前深度大于最大深度时，赋值给 left 的值一定是这一层最左边的节点值，
     * 因为后面的都是同一层，不满足大于条件。若是判断大于等于，则 left 将被赋为这一层的最右节点的值。
     *
     */
    private void process(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if(depth > maxDepth) {
            maxDepth = depth;
            leftVal = root.val;
        }
        process(root.left, depth + 1);
        process(root.right, depth + 1);
    }

}
