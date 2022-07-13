package LeetCode.JZOffer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Problem_JZ37_SerDeBinTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 前序序列化
    public static class Codec {

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
            sb.append(root.val + ",");
            preSerial(root.left, sb);
            preSerial(root.right, sb);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if(data == null || data.length()==0) {
                return null;
            }
            String[] array = data.split(",");
            Queue<String> queue = new LinkedList<>();
            queue.addAll(Arrays.asList(array));
            return preBuild(queue);
        }

        private TreeNode preBuild(Queue<String> preQueue) {
            String val = preQueue.poll();
            if(val.equals("#")) {
                return null;
            }
            TreeNode head = new TreeNode(Integer.parseInt(val));
            head.left = preBuild(preQueue);
            head.right = preBuild(preQueue);
            return head;
        }
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.right.left = new TreeNode(4);
        node.right.right = new TreeNode(5);
        String ans = new Codec().serialize(node);
        System.out.println(ans);
        var res = new Codec().deserialize(ans);
        System.out.println();

    }


}
