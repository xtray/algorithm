package LeetCode;

import java.util.*;

public class Problem_508_MostFreqSubtreeNum {

    public class TreeNode {
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

    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        List<Integer> list = new ArrayList<>();
        process(root, list);
        Map<Integer, Integer> map = new HashMap<>();
        int maxCnt = 0;
        for (int num : list) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            maxCnt = Math.max(maxCnt, map.get(num));
        }
        list.clear();
        for (int key : map.keySet()) {
            if (map.get(key) == maxCnt) {
                list.add(key);
            }
        }
        int[] ans = new int[list.size()];
        int idx = 0;
        for (int num : list) {
            ans[idx++] = num;
        }
        return ans;
    }

    private int process(TreeNode root, List<Integer> list) {
        if (root == null) {
            return 0;
        }
        int left = process(root.left, list);
        int right = process(root.right, list);
        int cur = root.val + left + right;
        list.add(cur);
        return cur;
    }
}
