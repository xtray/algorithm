package _LintCode;

public class Problem_1358_PathSum {

    public static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    public boolean pathSum(TreeNode root, int sum) {
        if (root == null) {
            return sum == 0;
        }
        boolean p1 = pathSum(root.left, sum - root.val);
        boolean p2 = pathSum(root.right, sum - root.val);
        return p1 || p2;
    }

}
