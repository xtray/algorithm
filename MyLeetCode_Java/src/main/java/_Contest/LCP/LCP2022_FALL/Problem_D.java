package _Contest.LCP.LCP2022_FALL;

public class Problem_D {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class Info {
        public int open; // 当前节点为头全亮的次数
        public int close; // 当前节点为头全灭的次数
        public int subOpen; // 当前节点为头 子树全亮的次数
        public int subClose; // 当前节点为头 子树全灭的次数

        public Info(int o, int c, int so, int sc) {
            open = o;
            close = c;
            subOpen = so;
            subClose = sc;
        }
    }

    // 二叉树递归套路
    // 考虑节点X, 最少次数让x节点为头的子树上的灯全亮
    public int closeLampInTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Info info = process(root);
        int ans1 = Math.min(info.close, info.open + 1);
        // 开关3
        int close1 = Integer.MAX_VALUE;
        if (root.left != null && root.right == null && root.left.val == 1 && root.val == 1) {
            close1 = info.subClose + 1;
        }
        if (root.right != null && root.left == null && root.right.val == 1 && root.val == 1) {
            close1 = info.subClose + 1;
        }
        if (root.right != null && root.left != null && root.left.val == 1 && root.right.val == 1 && root.val == 1) {
            close1 = info.subClose + 1;
        }
        return Math.min(ans1, close1);
    }

    private Info process(TreeNode root) {
        if (root == null) {
            return new Info(0, 0, 0, 0);
        }

        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);

        // 1.让左右子树都闭

        // 左子开, 右子闭
        int p1 = leftInfo.open + 1 + rightInfo.close;
        // 右子开, 左子闭
        int p2 = leftInfo.close + rightInfo.open + 1;
        // 左子开, 右子开
        int p3 = leftInfo.open + 1 + rightInfo.open + 1;
        // 左子闭, 右子闭
        int p4 = leftInfo.close + rightInfo.close;
        // 让左右子树都关闭, 单独关当前
        int close1 = Math.min(Math.min(p1, p2), Math.min(p3, p4)) + root.val == 0 ? 0 : 1;
        // 开关3
        int close2 = Integer.MAX_VALUE;
        if (root.left != null && root.right == null && root.left.val == 1 && root.val == 1) {
            close2 = leftInfo.subClose + 1;
        }
        int close3 = Integer.MAX_VALUE;
        if (root.right != null && root.left == null && root.right.val == 1 && root.val == 1) {
            close3 = rightInfo.subClose + 1;
        }
        int close4 = Integer.MAX_VALUE;
        if (root.right != null && root.left != null && root.left.val == 1 && root.right.val == 1 && root.val == 1) {
            close4 = leftInfo.subClose + rightInfo.subClose + 1;
        }
        int close = Math.min(Math.min(close1, close2), Math.min(close3, close4));
        // 2.让左右子树都开

        // 左子开, 右子闭
        int q1 = leftInfo.open + rightInfo.close + 1;
        // 右子开, 左子闭
        int q2 = leftInfo.close + 1 + rightInfo.open;
        // 左子开, 右子开
        int q3 = leftInfo.open + rightInfo.open;
        // 左子闭, 右子闭
        int q4 = leftInfo.close + 1 + rightInfo.close + 1;
        int open1 = Math.min(Math.min(q1, q2), Math.min(q3, q4)) + root.val == 1 ? 0 : 1;

        // 开关3
        int open2 = Integer.MAX_VALUE;
        if (root.left != null && root.right == null && root.left.val == 0 && root.val == 0) {
            open2 = leftInfo.subOpen + 1;
        }
        int open3 = Integer.MAX_VALUE;
        if (root.right != null && root.left == null && root.right.val == 0 && root.val == 0) {
            open3 = rightInfo.subOpen + 1;
        }
        int open4 = Integer.MAX_VALUE;
        if (root.right != null && root.left != null && root.left.val == 0 && root.right.val == 0 && root.val == 0) {
            open4 = leftInfo.subOpen + rightInfo.subOpen + 1;
        }
        int open = Math.min(Math.min(open1, open2), Math.min(open3, open4));

        int curOpen = Math.min(close + 1, open);
        int curClose = Math.min(open + 1, close);
        int subOpen = leftInfo.open + rightInfo.open;
        int subClose = leftInfo.close + rightInfo.close;
        return new Info(curOpen, curClose, subOpen, subClose);
    }
}
