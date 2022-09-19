package LeetCode;

import java.util.*;

public class Problem_662_WithOfBinaryTree {

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

    public static class Pair<K, V> {
        public K key;
        public V value;

        public Pair(K k, V v) {
            key = k;
            value = v;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int maxWidth = 0;
        LinkedList<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.addLast(new Pair<>(root, 1));
        while (!queue.isEmpty()) {
            int size = queue.size();
            int maxId = 0;
            int minId = queue.peekFirst().value; // 本层第一个就是最小
            for (int i = 0; i < size; i++) {
                Pair<TreeNode, Integer> pair = queue.pollFirst();
                TreeNode cur = pair.key;
                int id = pair.value;
                maxId = id; // 不断推高本层的最大id
                if (cur.left != null) {
                    queue.addLast(new Pair<>(cur.left, id << 1));
                }
                if (cur.right != null) {
                    queue.addLast(new Pair<>(cur.right, id << 1 | 1));
                }
            }
            maxWidth = Math.max(maxWidth, maxId - minId + 1);
        }
        return maxWidth;
    }

    private Map<Integer, Integer> levelMin = new HashMap<>(); // 存储每层编号的最小值

    public int widthOfBinaryTree1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dfs(root, 1, 1);
    }
    // index: 当前节点编号
    // 返回宽度的最大差值
    public int dfs(TreeNode node, int depth, int index) {
        if (node == null) {
            return 0;
        }
        // 每一层最先访问到的节点会是最左边的节点，即每一层编号的最小值
        levelMin.putIfAbsent(depth, index);
        return Math.max(index - levelMin.get(depth) + 1, // 每次用当前层编号刷新宽度
                Math.max(dfs(node.left, depth + 1, index * 2), dfs(node.right, depth + 1, index * 2 + 1)));
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(9);
        var ans = new Problem_662_WithOfBinaryTree().widthOfBinaryTree(root);
        System.out.println(ans);

        // [1,1,1,1,1,1,1,null,null,null,1,null,null,null,null,2,2,2,2,2,2,2,null,2,null,null,2,null,2],  8
    }

}
