package LeetCode;

import java.util.*;
import java.util.HashMap;
import java.util.List;

public class Problem_652_DupSubTrees {

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

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        // [ 当前头前序遍历串, 出现次数]
        Map<String, Integer> cntMap = new HashMap<>();
        Map<String, TreeNode> nodeMap = new HashMap<>();
        process(root, cntMap, nodeMap);
        for (String key : cntMap.keySet()) {
            if (cntMap.get(key) >= 2) {
                ans.add(nodeMap.get(key));
            }
        }
        return ans;
    }

    private String process(TreeNode root, Map<String, Integer> cntMap, Map<String, TreeNode> nodeMap) {
        if (root == null) {
            return "#,";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(root.val).append(',');
        String left = process(root.left, cntMap, nodeMap);
        String right = process(root.right, cntMap, nodeMap);
        sb.append(left).append(right);
        String key = sb.toString();
        cntMap.put(key, cntMap.getOrDefault(key, 0) + 1);
        nodeMap.put(key, root);
        return key;
    }

    // 使用一个Map, 在插入时判断是否是重复的, 如果重复的, 保存后标记为已经使用
    public List<TreeNode> findDuplicateSubtrees1(TreeNode root) {
        List<TreeNode> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        // [ 当前头前序遍历串, 使用过没有]
        Map<String, Boolean> nodeMap = new HashMap<>();
        process1(root, nodeMap, ans);
        return ans;
    }

    private String process1(TreeNode root, Map<String, Boolean> nodeMap, List<TreeNode> ans) {
        if (root == null) {
            // return "#,";
            return "#";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(root.val).append(',');
        String left = process1(root.left, nodeMap, ans);
        String right = process1(root.right, nodeMap, ans);
        sb.append(left).append(right);
        String key = sb.toString();
        if (nodeMap.containsKey(key) && !nodeMap.get(key)) {
            nodeMap.put(key, true);
            ans.add(root);
        } else if (!nodeMap.containsKey(key)) {
            nodeMap.put(key, false);
        }
        return key;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.left.left = new TreeNode(0);
        root.right = new TreeNode(0);
        root.right.right = new TreeNode(0);
        root.right.right.right = new TreeNode(0);
        var ans = new Problem_652_DupSubTrees().findDuplicateSubtrees(root);

        System.out.println(ans);
    }
}
