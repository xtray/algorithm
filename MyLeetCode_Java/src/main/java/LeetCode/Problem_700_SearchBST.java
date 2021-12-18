package LeetCode;

public class Problem_700_SearchBST {

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

    public TreeNode searchBST(TreeNode root, int val) {
        if( root == null) {
            return null;
        }
        if(val > root.val) {
            return searchBST(root.right, val);
        }  else if (val < root.val) {
            return searchBST(root.left, val);
        } else {
            return root;
        }
    }
}
