package LeetCode;

public class Problem_104_MaxDepth {

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

        // 解法1: 二叉树的递归套路解法
        public static class Info {
            public int maxHeight;

            public Info(int h) {
                maxHeight = h;
            }
        }

        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return process(root).maxHeight;
        }

        private Info process(TreeNode root) {
            if (root == null) {
                return new Info(0);
            }
            Info leftInfo = process(root.left);
            Info rightInfo = process(root.right);
            int curHeight = 0;
            curHeight = Math.max(rightInfo.maxHeight, leftInfo.maxHeight) + 1;
            return new Info(curHeight);
        }

        // 解法2: 普通递归
        public int maxDepth2(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return process2(root);
        }

        private int process2(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return Math.max(process2(root.left), process2(root.right)) + 1;
        }

    }
}
