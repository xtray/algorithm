package LeetCode;

public class Problem_669_TrimBST {

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

    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        // 根节点比最大值大，右树更大(越界), 直接返回左子树的trimBST
        if (root.val > high) {
            return trimBST(root.left, low, high);
        }
        //根节点比最小值小，左树更小(越界), 直接返回右子树的trimBST
        if (root.val < low) {
            return trimBST(root.right, low, high);
        }
        // 分别对左右子树进行处理
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }


}
