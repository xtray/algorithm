package LeetCode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Problem_1586_BSTIteratorII {
    public static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    public class BSTIterator {

        private List<TreeNode> list;
        private int curPos;
        private int size;

        public BSTIterator(TreeNode root) {
            list = morrisIn(root);
            size = list.size();
        }


        public boolean hasNext() {
            return curPos < size;
        }


        public int next() {
            return list.get(curPos++).val;
        }

        public boolean hasPrev() {
            // NOTE: curPos指向的是当前的下一个, pre要-2;
            return curPos - 2 >= 0;
        }

        public int prev() {
            curPos--;
            return list.get(curPos - 1).val;
        }

        // Morris中序遍历, 一个节点在第二次来到自己时候打印
        // 1.没有左树, 直接打印
        // 2.有左树, 第二次来到自己打印
        private List<TreeNode> morrisIn(TreeNode root) {
            List<TreeNode> ans = new ArrayList<>();
            if (root == null) {
                return ans;
            }
            TreeNode cur = root;
            while (cur != null) {
                TreeNode mostRight = cur.left; // 左树最右节点
                if (mostRight != null) {
                    while (mostRight.right != null && mostRight.right != cur) {
                        mostRight = mostRight.right;
                    }
                    // 1. mostRight.right == null
                    if (mostRight.right == null) {
                        mostRight.right = cur;
                        cur = cur.left;
                        continue;
                    }
                    // 2. mostRight.right == cur
                    mostRight.right = null;
                }
                ans.add(cur);
                cur = cur.right;
            }
            return ans;
        }
    }


    // TODO: 两个栈, 一个当做缓存
    class BSTIterator2 {

        private TreeNode pre; // 前一个
        private TreeNode cur; // 要弹出的下一个

        private ArrayDeque<TreeNode> stack;

        public BSTIterator2(TreeNode root) {
            stack = new ArrayDeque<>();
            pre = null;
            cur = root;

        }

        public boolean hasNext() {
            return cur != null || !stack.isEmpty();
        }

        public int next() {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop(); // 当前节点左树最左
            int ret = cur.val;
            pre = cur;
            cur = cur.right;
            return ret;
        }

        public boolean hasPrev() {
            return pre != null;
        }

        public int prev() {
            return 0;
        }
    }

    class BSTIterator3 {
        TreeNode head;
        TreeNode cur;

        public BSTIterator3(TreeNode root) {
            // 先把这颗树变成一个双向链表
            dfs(root);
            cur = new TreeNode(-1);
            cur.right = head;
        }

        public void dfs(TreeNode node) {
            if (node == null) return;
            dfs(node.left);
            if (head == null) {
                head = node;
                head.left = null;
                cur = head;
            } else {
                node.left = cur;
                cur.right = node;
                cur = cur.right;
            }
            dfs(node.right);
        }

        public boolean hasNext() {
            return cur.right != null;
        }

        public int next() {
            cur = cur.right;
            return cur.val;
        }

        public boolean hasPrev() {
            return cur.left != null;
        }

        public int prev() {
            cur = cur.left;
            return cur.val;
        }
    }


}
