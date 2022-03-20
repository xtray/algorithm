package LeetCode.Jianzhi;

public class Problem_Jz026_IsSubTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(A == null || B == null) {
            return false;
        }
        String aStr = preSerialize(A);
        String bStr = preSerialize(B);
        return aStr.contains(bStr);
    }

    // 先序序列化
    private String preSerialize(TreeNode node) {
        if (node == null) {
            return "#";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(node.val);
        sb.append("_");
        sb.append(preSerialize(node.left));
        sb.append("_");
        sb.append(preSerialize(node.right));
        sb.append("_");
        return sb.toString();
    }
}
