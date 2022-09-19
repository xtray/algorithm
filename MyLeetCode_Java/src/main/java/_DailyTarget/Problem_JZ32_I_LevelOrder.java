package _DailyTarget;

import java.util.*;

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
        if (root == null) {
            return new int[0];
        }
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            list.add(cur.val);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        int[] ans = new int[list.size()];
        int idx = 0;
        for (int num : list) {
            ans[idx++] = num;
        }
        return ans;
    }

    public static class Info {
        public TreeNode node;
        public int level;

        public Info(TreeNode n, int l) {
            node = n;
            level = l;
        }
    }

    // DFS
    public int[] levelOrder1(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        ArrayDeque<Info> stack = new ArrayDeque<>();
        Set<TreeNode> set = new HashSet<>();
        stack.addLast(new Info(root, 1));
        set.add(root);
        Map<Integer, List<Integer>> map = new HashMap<>();
        Set<TreeNode> valSet = new HashSet<>();
        valSet.add(root);
        map.computeIfAbsent(1, key -> new ArrayList<>()).add(root.val);
        int cnt = 1;
        while (!stack.isEmpty()) {
            Info info = stack.pollLast(); // first
            TreeNode cur = info.node;
            if (!valSet.contains(cur)) {
                valSet.add(cur);
                map.computeIfAbsent(info.level, key -> new ArrayList<>()).add(cur.val);
            }
            if (cur.right != null && !set.contains(cur.right)) {
                cnt++;
                set.add(cur.right);
                stack.addLast(info);
                stack.addLast(new Info(cur.right, info.level + 1));
            }
            if (cur.left != null && !set.contains(cur.left)) {
                cnt++;
                set.add(cur.left);
                stack.addLast(info);
                stack.addLast(new Info(cur.left, info.level + 1));
            }
        }
        int[] ans = new int[cnt];
        int idx = 0;
        for (int i = 1; i <= map.size(); i++) {
            for (int num : map.get(i)) {
                ans[idx++] = num;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        var ans = new Problem_JZ32_I_LevelOrder().levelOrder1(root);
        System.out.println(Arrays.toString(ans));
    }
}
