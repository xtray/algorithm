package LeetCode;

public class Problem_687_LongestUniqueValPath {
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

    public static class Info {
        public int maxHead; // 当前节点的单侧(左or右)最大值
        public int maxAll; // 当前节点下(所有情况)的最大值

        public Info(int val, int all) {
            maxHead = val;
            maxAll = all;
        }

    }

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root).maxAll;
    }

    private Info process(TreeNode root) {
        if (root == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        // 能向上传递的情况:
        // 情况1: 要当前节点且当前节点为头单侧延伸到左树
        int p1 = 0;
        if (root.left != null && root.left.val == root.val) {
            p1 = 1 + leftInfo.maxHead;
        }
        // 情况2: 要当前节点且当前节点为头单侧延伸到右树
        int p2 = 0;
        if (root.right != null && root.right.val == root.val) {
            p2 = 1 + rightInfo.maxHead;
        }
        int maxHead = Math.max(p1, p2);
        // 不能向上传递的情况:
        // 情况3: 要当前节点, 向左右延伸
        int p3 = 0;
        if (root.left != null && root.right != null && root.left.val == root.val && root.right.val == root.val) {
            p3 = 2 + leftInfo.maxHead + rightInfo.maxHead;
        }

        // 情况4: 不要当前节点, 历史最大值
        int p4 = Math.max(leftInfo.maxAll, rightInfo.maxAll);
        int maxAll = Math.max(maxHead, Math.max(p3, p4));
        return new Info(maxHead, maxAll);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(5);
        var ans = new Problem_687_LongestUniqueValPath().longestUnivaluePath(root);
        System.out.println(ans);

    }


}
