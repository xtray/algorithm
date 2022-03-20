package LeetCode;

public class Problem_606_Tree2Str {
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

    public String tree2str(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        process(root, sb);
        return sb.toString();
    }

    private void process(TreeNode root, StringBuilder sb) {
        if(root == null) {
            return;
        }
        sb.append(root.val);
        if(root.left!=null || root.right != null) {
            sb.append("(");
            process(root.left, sb);
            sb.append(")");
        }
        if(root.right!=null) {
            sb.append("(");
            process(root.right, sb);
            sb.append(")");
        }
    }


}
