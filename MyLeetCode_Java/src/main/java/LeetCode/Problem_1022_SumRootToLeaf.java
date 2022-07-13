package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_1022_SumRootToLeaf {

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

    public int sumRootToLeaf(TreeNode root) {
        if (root == null) {
            return 0;
        }

        List<int[]> list = process(root);
        int ans = 0;
        for (int[] num : list) {
            ans += num[1];
        }
        return ans;
    }


    private List<int[]> process(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<int[]> leftList = process(root.left);
        List<int[]> rightList = process(root.right);
        int cur = root.val;
        List<int[]> list = new ArrayList<>();
        if (leftList != null) {
            modify(leftList, cur);
            list.addAll(leftList);
        }
        if (rightList != null) {
            modify(rightList, cur);
            list.addAll(rightList);
        }
        if (root.left == null && root.right == null) {
            list.add(new int[]{1, cur});
        }
        return list;
    }

    private void modify(List<int[]> list, int cur) {
        for (int i = 0; i < list.size(); i++) {
            int move = list.get(i)[0];
            int val = list.get(i)[1];
            if (cur == 1) {
                val |= 1 << move;
            }
            move++;
            list.set(i, new int[]{move, val});
        }
    }

    public int sumRootToLeaf2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int val) {
        int ans = 0;
        int cur = (val << 1) + root.val;

        if (root.left != null) {
            ans += dfs(root.left, cur);
        }
        if (root.right != null) {
            ans += dfs(root.right, cur);
        }
        if (root.left == null && root.right == null) {
            ans = cur;
        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(1);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(1);
        var ans = new Problem_1022_SumRootToLeaf().sumRootToLeaf(root);
        System.out.println(ans);

    }
}
