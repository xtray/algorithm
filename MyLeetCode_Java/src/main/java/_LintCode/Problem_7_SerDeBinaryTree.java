package _LintCode;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Problem_7_SerDeBinaryTree {

    public static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    // 前序序列化
    public String serialize(TreeNode root) {
        if (root == null) {
            return "#";
        }
        StringBuilder sb = new StringBuilder();
        dfs(root, sb);
        return sb.toString();
    }

    private void dfs(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#,");
            return;
        }
        sb.append(root.val).append(",");
        dfs(root.left, sb);
        dfs(root.right, sb);
    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] ss = data.split(",");
        ArrayDeque<String> queue = new ArrayDeque<>(Arrays.asList(ss));
        return build(queue);
    }

    private TreeNode build(ArrayDeque<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        String str = queue.pollFirst();
        if ("#".equals(str)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(str));
        root.left = build(queue);
        root.right = build(queue);
        return root;
    }

    public static void main(String[] args) {

    }
}
