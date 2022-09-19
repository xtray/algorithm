package LeetCode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Problem_173_BSTIterator {
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

    class BSTIterator1 {
        private TreeNode cur; // 要弹出的下一个
        private ArrayDeque<TreeNode> stack;

        public BSTIterator1(TreeNode root) {
            cur = root;
            stack = new ArrayDeque<>();
        }

        // 中序遍历 左 -- 头  -- 右
        // IMP: 用栈保存左树最左的节点(第一个), 然后再右树
        public int next() {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            int ret = cur.val;
            cur = cur.right;
            return ret;
        }

        public boolean hasNext() {
            return cur != null || !stack.isEmpty();
        }
    }

}
