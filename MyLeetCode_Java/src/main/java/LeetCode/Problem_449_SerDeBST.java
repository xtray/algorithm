package LeetCode;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * ref: https://leetcode-cn.com/problems/serialize-and-deserialize-bst/solution/li-yong-liao-er-cha-sou-suo-shu-xing-zhi-2wno/
 * 普通二叉树的序列化我们需要加上空结点才能保证最后反序列化得到的树是唯一的。
 * 但是这里是二叉搜索树，它的中序遍历是有序的。
 * 有了中序遍历之后，任意给我们前序遍历或者后序遍历，
 * 我们都可以构造一个唯一的二叉树。所以二叉搜索树不需要序列化空结点。
 *
 */

public class Problem_449_SerDeBST {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 利用BST性质的做法
    public static class Codec1 {

        // // Encodes a tree to a single string.
        // public String serialize(TreeNode root) {
        //
        // }
        //
        // // Decodes your encoded data to tree.
        // public TreeNode deserialize(String data) {
        //
        // }
    }


    // 使用普通的二叉树序列化, 反序列化的方法
    public static class Codec2 {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            if (root == null) {
                sb.append("#,");
            } else { // root!=null
                sb.append(root.val).append(",");
                ArrayDeque<TreeNode> queue = new ArrayDeque<>();
                queue.add(root);
                while (!queue.isEmpty()) {
                    TreeNode cur = queue.poll();
                    // 节点不为空, 既序列化又入队列
                    // 节点为空, 只做序列化
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
            }
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null || data.length() == 0) {
                return null;
            }
            String[] strArr = data.split(",");
            ArrayDeque<String> levelList = new ArrayDeque<>();
            levelList.addAll(Arrays.asList(strArr));
            ArrayDeque<TreeNode> queue = new ArrayDeque<>();
            TreeNode head = genNode(levelList.poll());
            if (head != null) {
                queue.add(head);
            }
            while (!queue.isEmpty()) {
                TreeNode cur = queue.poll();
                cur.left = genNode(levelList.poll());
                cur.right = genNode(levelList.poll());
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            return head;
        }

        private TreeNode genNode(String val) {
            if ("#".equals(val)) {
                return null;
            } else {
                return new TreeNode(Integer.parseInt(val));
            }
        }
    }
}
