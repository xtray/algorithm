package _DailyTarget;

import java.util.ArrayDeque;
import java.util.Arrays;

public class Problem_JZ37_SerDeBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 水平序列化
    public class Codec0 {
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "#";
            }
            StringBuilder sb = new StringBuilder();
            ArrayDeque<TreeNode> queue = new ArrayDeque<>();
            queue.add(root);
            sb.append(root.val).append(",");
            while (!queue.isEmpty()) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.add(cur.left);
                    sb.append(cur.left.val).append(",");
                } else {
                    sb.append("#,");
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                    sb.append(cur.right.val).append(",");
                } else {
                    sb.append("#,");
                }
            }
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null || data.length() == 0) {
                return null;
            }
            String[] strs = data.split(",");
            if (strs[0].equals("#")) {
                return null;
            }
            TreeNode root = new TreeNode(Integer.parseInt(strs[0]));
            ArrayDeque<TreeNode> queue = new ArrayDeque<>();
            queue.add(root);
            int idx = 1;
            while (!queue.isEmpty()) {
                TreeNode cur = queue.poll();
                cur.left = strs[idx].equals("#") ? null : new TreeNode(Integer.parseInt(strs[idx]));
                ++idx;
                cur.right = strs[idx].equals("#") ? null : new TreeNode(Integer.parseInt(strs[idx]));
                ++idx;
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            return root;
        }
    }

    // 前序序列化
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            preSerial(root, sb);
            return sb.toString();
        }

        private void preSerial(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append("#,");
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
            String[] array = data.split(",");
            ArrayDeque<String> queue = new ArrayDeque<>(Arrays.asList(array));
            return preBuild(queue);
        }

        private TreeNode preBuild(ArrayDeque<String> preQueue) {
            String val = preQueue.poll();
            if (val.equals("#")) {
                return null;
            }
            TreeNode head = new TreeNode(Integer.parseInt(val));
            head.left = preBuild(preQueue);
            head.right = preBuild(preQueue);
            return head;
        }
    }
}
