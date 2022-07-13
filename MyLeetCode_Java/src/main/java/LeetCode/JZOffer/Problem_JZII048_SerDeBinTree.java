package LeetCode.JZOffer;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// IMP: 二叉树的序列化, 反序列化, 非常非常重要!!

public class Problem_JZII048_SerDeBinTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 前序序列化
    public static class Codec1 {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            preS(root, sb);
            return sb.toString();
        }

        private void preS(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append("#,");
                return;
            }
            sb.append(root.val).append(",");
            preS(root.left, sb);
            preS(root.right, sb);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null || data.length() == 0) {
                return null;
            }
            String[] strArr = data.split(",");
            ArrayDeque<String> queue = new ArrayDeque<>();
            queue.addAll(Arrays.asList(strArr)); // 消费队列重建二叉树
            return preB(queue);
        }

        private TreeNode preB(ArrayDeque<String> queue) {
            String val = queue.poll();
            if ("#".equals(val)) {
                return null;
            }
            TreeNode head = new TreeNode(Integer.parseInt(val));
            head.left = preB(queue);
            head.right = preB(queue);
            return head;
        }
    }

    // IMP: 水平序列化, 多看多写!!!
    public static class Codec2 {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder ans = new StringBuilder();
            if (root == null) {
                ans.append("#,");
            } else {
                ans.append(root.val).append(",");
                Queue<TreeNode> queue = new LinkedList<>();
                queue.add(root);
                while (!queue.isEmpty()) {
                    TreeNode cur = queue.poll();
                    // 父节点去序列化子
                    // 非空: 既入队列, 又序列化
                    // 空: 只序列化
                    if (cur.left != null) {
                        queue.add(cur.left);
                        ans.append(cur.left.val).append(",");
                    } else {
                        ans.append("#,");
                    }
                    if (cur.right != null) {
                        queue.add(cur.right);
                        ans.append(cur.right.val).append(",");
                    } else {
                        ans.append("#,");
                    }
                }
            }
            return ans.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null || data.length() == 0) {
                return null;
            }
            String[] array = data.split(",");
            Queue<String> levelList = new LinkedList<>();
            levelList.addAll(Arrays.asList(array));// 消费队列重建二叉树
            TreeNode head = genNode(levelList.poll());
            Queue<TreeNode> queue = new LinkedList<>();
            if (head != null) { // NOTE: 生成的head有可能是空的
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

    // IMP: 后序序列化 利用stack调整顺序
    public static class Codec3 {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            postS(root, sb);
            return sb.toString();
        }

        private void postS(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append("#,");
                return;
            }
            postS(root.left, sb);
            postS(root.right, sb);
            sb.append(root.val).append(",");
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null || data.length() == 0) {
                return null;
            }
            // NOTE: 从 左右头 ---调整为-->  头右左(stack)
            String[] strArr = data.split(",");
            ArrayDeque<String> stack = new ArrayDeque<>();
            for (String s : strArr) {
                stack.push(s);
            }
            return postB(stack);
        }

        private TreeNode postB(ArrayDeque<String> postStack) {
            String val = postStack.pop();
            if ("#".equals(val)) {
                return null;
            }
            TreeNode head = new TreeNode(Integer.parseInt(val));
            head.right = postB(postStack);
            head.left = postB(postStack);
            return head;
        }
    }


    public static class Codec4 {

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

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.right.left = new TreeNode(4);
        node.right.right = new TreeNode(5);
        String ans = new Codec4().serialize(node);
        System.out.println(ans);
        var res = new Codec4().deserialize(ans);
        System.out.println();

    }


}
