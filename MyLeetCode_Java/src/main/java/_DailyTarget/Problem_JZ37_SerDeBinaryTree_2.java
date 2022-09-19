package _DailyTarget;

import java.util.ArrayDeque;
import java.util.Arrays;

public class Problem_JZ37_SerDeBinaryTree_2 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    // 按层序列化
    // 空用#号, 逗号分割
    public class Codec {


        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "#";
            }
            StringBuilder sb = new StringBuilder();
            ArrayDeque<TreeNode> queue = new ArrayDeque<>();
            sb.append(root.val).append(",");
            queue.addLast(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = queue.pollFirst();
                    if (cur.left != null) {
                        sb.append(cur.left.val).append(",");
                        queue.add(cur.left);
                    } else {
                        sb.append("#,");
                    }
                    if (cur.right != null) {
                        sb.append(cur.right.val).append(",");
                        queue.add(cur.right);
                    } else {
                        sb.append("#,");
                    }
                }
            }
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null || data.length() == 0) {
                return null;
            }
            String[] ss = data.split(",");
            if (ss[0].equals("#")) {
                return null;
            }
            TreeNode root = new TreeNode(Integer.parseInt(ss[0]));
            ArrayDeque<TreeNode> queue = new ArrayDeque<>();
            queue.addLast(root);
            int idx = 1;
            int N = ss.length;
            while (!queue.isEmpty()) {
                TreeNode cur = queue.pollFirst();
                cur.left = (idx < N) ? getNode(ss[idx++]) : null;
                cur.right = (idx < N) ? getNode(ss[idx++]) : null;
                if (cur.left != null) {
                    queue.addLast(cur.left);
                }
                if (cur.right != null) {
                    queue.addLast(cur.right);
                }
            }
            return root;
        }

        private TreeNode getNode(String s) {
            if ("#".equals(s)) {
                return null;
            } else {
                return new TreeNode(Integer.parseInt(s));
            }
        }
    }

    public class Codec1 {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            preSerial(root, sb);
            return sb.toString();
        }

        private void preSerial(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append("#,"); // NOTE: 必须加逗号, 分割
                return;
            }
            sb.append(root.val).append(",");
            preSerial(root.left, sb);
            preSerial(root.right, sb);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null || data.length() == 0) {
                return null;
            }
            String[] ss = data.split(",");
            ArrayDeque<String> queue = new ArrayDeque<>(Arrays.asList(ss));
            return preBuild(queue);
        }

        private TreeNode preBuild(ArrayDeque<String> preQueue) {
            String val = preQueue.pollFirst();
            if ("#".equals(val)) {
                return null;
            }
            TreeNode head = new TreeNode(Integer.parseInt(val));
            head.left = preBuild(preQueue);
            head.right = preBuild(preQueue);
            return head;
        }
    }

}
