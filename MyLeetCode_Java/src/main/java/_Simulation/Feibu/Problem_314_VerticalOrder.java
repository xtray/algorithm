package _Simulation.Feibu;

import java.util.*;

public class Problem_314_VerticalOrder {

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

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        // [Node, 列号]
        Map<TreeNode, Integer> colMap = new HashMap<>();
        ArrayDeque<TreeNode> queue = new ArrayDeque<>(); // 按层遍历
        // [列号, 数组list]
        Map<Integer, List<Integer>> resMap = new HashMap<>();
        resMap.computeIfAbsent(0, k -> new ArrayList<>()).add(root.val);
        queue.addLast(root);
        colMap.put(root, 0);
        int minCol = 0; // 最小列, 最后总体偏移量
        while (!queue.isEmpty()) {
            TreeNode cur = queue.pollFirst();
            int col = colMap.get(cur);
            if (cur.left != null) {
                queue.add(cur.left);
                colMap.put(cur.left, col - 1);
                minCol = Math.min(minCol, col - 1);
                resMap.computeIfAbsent(col - 1, k -> new ArrayList<>()).add(cur.left.val);
            }
            if (cur.right != null) {
                queue.add(cur.right);
                colMap.put(cur.right, col + 1);
                resMap.computeIfAbsent(col + 1, k -> new ArrayList<>()).add(cur.right.val);
            }
        }
        int len = resMap.size();
        for (int i = 0; i < len; i++) {
            ans.add(new ArrayList<>());
        }
        for (int col : resMap.keySet()) {
            ans.set(col - minCol, resMap.get(col));
        }
        return ans;
    }
}
