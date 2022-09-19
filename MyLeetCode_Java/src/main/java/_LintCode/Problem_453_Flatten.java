package _LintCode;

public class Problem_453_Flatten {

    public static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    // DFS
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root);
    }

    // 返回扁平化后链表的尾部指针
    private TreeNode dfs(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode leftLast = dfs(root.left); // 左侧的尾部
        TreeNode rightLast = dfs(root.right);
        if (leftLast != null) {
            leftLast.right = root.right;
            root.right = root.left; // 左侧的头
            root.left = null;
        }
        if (rightLast != null) {
            return rightLast;
        }
        if (leftLast != null) {
            return leftLast;
        }
        return root;
    }

    // 二叉树的递归套路
    public void flatten1(TreeNode root) {
        if (root == null) {
            return;
        }
        process(root);
    }

    public static class Info {
        public TreeNode head;
        public TreeNode tail;

        public Info(TreeNode h, TreeNode t) {
            head = h;
            tail = t;
        }
    }

    private Info process(TreeNode root) {
        if (root == null) {
            return null;
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        // 构建自己的 head 信息
        root.left = null;
        root.right = leftInfo == null ? null : leftInfo.head;
        // 构建自己的 tail 信息
        TreeNode tail = leftInfo == null ? root : leftInfo.tail;
        tail.right = rightInfo == null ? null : rightInfo.head;
        // 矫正
        tail = rightInfo == null ? tail : rightInfo.tail;
        return new Info(root, tail);
    }

    // Morris遍历的解
    // 用left指针先连接已经遍历过的, 遍历完成后再修正为right指针
    public static void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode pre = null;
        TreeNode cur = root;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    if (pre != null) {
                        pre.left = cur;
                    }
                    pre = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {
                if (pre != null) {
                    pre.left = cur;
                }
                pre = cur;
            }
            cur = cur.right;
        }
        cur = root;
        TreeNode next = null;
        while (cur != null) {
            next = cur.left;
            cur.left = null;
            cur.right = next;
            cur = next;
        }
    }



}
