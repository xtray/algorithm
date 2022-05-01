package L_INPRG;

public class Problem_1367_SubPath {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

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

    private ListNode orgHead;

    public boolean isSubPath(ListNode head, TreeNode root) {
        orgHead = head;
        return process(root, head);
    }

    // 从二叉树的root节点出发, 能不能走出head开头的链表
    // TLE
    private boolean process(TreeNode root, ListNode head) {
        if (head == null) { // 走到末尾了
            return true;
        }
        if (root == null) { // 二叉树这个分支不存在
            return false;
        }
        // 可能性1: 从当前节点出发能不能找到
        boolean p1 = false;
        if (root.val == head.val) {
            p1 = process(root.left, head.next) || process(root.right, head.next);
        }
        // 可能性2: 从1的子节点出发能不能找到, 从最初的头开始重新找
        boolean p2 = process(root.left, orgHead) || process(root.right, orgHead);
        return p1 || p2;
    }

    public boolean isSubPath1(ListNode head, TreeNode root) {
        if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        //先判断当前的节点，如果不对，再看左子树和右子树呗
        return dfs(head, root) || isSubPath1(head, root.left) || isSubPath1(head, root.right);
    }

    private boolean dfs(ListNode head, TreeNode node) {
        //特判：链表走完了，返回true
        if (head == null) {
            return true;
        }
        //特判：链表没走完，树走完了，这肯定不行，返回false
        if (node == null) {
            return false;
        }
        //如果值不同，必定不是啊
        if (head.val != node.val) {
            return false;
        }
        //如果值相同，继续看，左边和右边有一个满足即可
        return dfs(head.next, node.left) || dfs(head.next, node.right);
    }


    // TODO: 二叉树的递归套路, LCA类似?
    public static class Info {
        public boolean curFound; // 从当前节点出发能不能找到
        public boolean leftFound; // 左子树上能不能找到
        public boolean rightFound; // 右子树上能不能找到

        public Info(boolean c, boolean l, boolean r) {
            curFound = c;
            leftFound = l;
            rightFound = r;
        }
    }


    // public boolean isSubPath2(ListNode head, TreeNode root) {
    //     Info info = process2(root, head);
    //     return info.curFound || info.leftFound || info.rightFound;
    // }
    //
    // private Info process2(TreeNode root, ListNode head) {
    //     if (head == null) {
    //         return new Info(true, false, false);
    //     }
    //     if (root == null) {
    //         return new Info(false, false, false);
    //     }
    //     Info leftInfo = process2(root.left, head);
    //     Info rightInfo = process2(root.right, head);
    //     boolean leftFound = leftInfo.curFound || leftInfo.leftFound || leftInfo.rightFound;
    //     boolean rightFound = rightInfo.curFound || rightInfo.leftFound || rightInfo.rightFound;
    //     boolean p1 = false;
    //     boolean p2 = false;
    //     if (root.val == head.val) {
    //         p1 = process2(root.left, head.next);
    //         p2 = process2(root.right, head.next);
    //     }
    //     boolean curFound = p1 || p2;
    //     return new Info(curFound, leftFound, rightFound);
    // }

    public static void main(String[] args) {
        // TreeNode root = new TreeNode(1);
        // root.left = new TreeNode(4);
        // root.left.right = new TreeNode(2);
        // root.left.right.left = new TreeNode(1);
        // root.right = new TreeNode(4);
        // root.right.left = new TreeNode(2);
        // root.right.left.left = new TreeNode(6);
        // root.right.left.right = new TreeNode(8);
        // root.right.left.right.left = new TreeNode(1);
        // root.right.left.right.right = new TreeNode(3);

        // ListNode head = new ListNode(4);
        // head.next = new ListNode(2);
        // head.next.next = new ListNode(8);

        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(1);
        root.right.left.left = new TreeNode(9);

        ListNode head = new ListNode(1);
        head.next = new ListNode(10);

        var ans = new Problem_1367_SubPath().isSubPath(head, root);
        System.out.println(ans);
    }
}
