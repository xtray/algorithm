package LeetCode;

import java.util.*;

public class Problem_656_PrintTree {

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

    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        // 1. 先取得树的高度
        int h = getHeight(root);
        h--;
        int N = h + 1;
        int M = (1 << (h + 1)) - 1;
        String[][] grid = new String[N][M];
        grid[0][(M - 1) >> 1] = String.valueOf(root.val);
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        ArrayDeque<int[]> posQueue = new ArrayDeque<>();
        queue.addLast(root);
        posQueue.addLast(new int[]{0, (M - 1) >> 1});
        while (!queue.isEmpty()) {
            // int size = queue.size();
            // for (int i = 0; i < size; i++) {
            TreeNode cur = queue.pollFirst();
            int[] pos = posQueue.pollFirst();
            int r = pos[0];
            int c = pos[1];
            int nr = r + 1;
            int offset = 1 << (h - r - 1);
            ;
            int nc = 0;
            if (cur.left != null) {
                nc = c - offset;
                grid[nr][nc] = String.valueOf(cur.left.val);
                queue.addLast(cur.left);
                posQueue.addLast(new int[]{nr, nc});
            }
            if (cur.right != null) {
                nc = c + offset;
                grid[nr][nc] = String.valueOf(cur.right.val);
                queue.addLast(cur.right);
                posQueue.addLast(new int[]{nr, nc});
            }
            // }
        }
        // 转换为List
        for (int i = 0; i < N; i++) {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < M; j++) {
                list.add(grid[i][j] == null ? "" : grid[i][j]);
            }
            ans.add(list);
        }
        return ans;
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    public List<List<String>> printTree1(TreeNode root) {
        List<List<String>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        // 1. 先取得树的高度
        int h = getHeight(root);
        h--;
        int N = h + 1;
        int M = (1 << (h + 1)) - 1;
        // 2. 空间初始化
        for (int i = 0; i < N; i++) {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < M; j++) {
                list.add("");
            }
            ans.add(list);
        }
        // 3. 填充
        fill(root, h, 0, (M - 1) >> 1, ans);
        return ans;
    }

    private void fill(TreeNode root, int h, int r, int c, List<List<String>> ans) {
        if (root == null) return;
        ans.get(r).set(c, String.valueOf(root.val));
        fill(root.left, h, r + 1, c - (1 << (h - r - 1)), ans);
        fill(root.right, h, r + 1, c + (1 << (h - r - 1)), ans);
    }

    public static void main(String[] args) {
        // TreeNode root = new TreeNode(1);
        // root.left = new TreeNode(2);

        // TreeNode root = new TreeNode(1);
        // root.left = new TreeNode(2);
        // root.left.right = new TreeNode(4);
        // root.right = new TreeNode(3);


        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(3);

        var ans = new Problem_656_PrintTree().printTree(root);
        System.out.println(ans);
    }
}
