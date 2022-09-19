package LeetCode;

import java.util.*;

public class Problem_987_VerticalTraversal {

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

    private class NodeInfo {
        private TreeNode node;
        private int order;

        public NodeInfo(TreeNode n, int o) {
            node = n;
            order = o;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        ArrayDeque<NodeInfo> queue = new ArrayDeque<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        queue.addLast(new NodeInfo(root, 0));
        int maxMinus = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            Map<Integer, List<Integer>> tmp = new HashMap<>();
            for (int i = 0; i < size; i++) {
                NodeInfo info = queue.pollFirst();
                TreeNode node = info.node;
                int order = info.order;
                maxMinus = Math.min(maxMinus, order);
                tmp.computeIfAbsent(order, key -> new ArrayList<>()).add(node.val);
                if (node.left != null) {
                    queue.add(new NodeInfo(node.left, order - 1));
                }
                if (node.right != null) {
                    queue.add(new NodeInfo(node.right, order + 1));
                }
            }
            for (int key : tmp.keySet()) {
                List<Integer> tmpList = tmp.get(key);
                tmpList.sort((o1, o2) -> o1 - o2);
                map.computeIfAbsent(key, k -> new ArrayList<>()).addAll(tmpList);
            }
        }
        int size = map.size();
        for (int i = 0; i < size; i++) {
            ans.add(new ArrayList<>());
        }
        for (int key : map.keySet()) {
            List<Integer> list = map.get(key);
            ans.set(key - maxMinus, list);
        }
        return ans;
    }

}
