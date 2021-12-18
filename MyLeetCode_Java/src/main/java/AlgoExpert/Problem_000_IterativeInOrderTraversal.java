package AlgoExpert;

import java.util.function.Function;

public class Problem_000_IterativeInOrderTraversal {

    // 中序遍历
    public static void iterativeInOrderTraversal(
            BinaryTree tree, Function<BinaryTree, Void> callback) {
        if (tree == null) {
            return;
        }
        BinaryTree cur = tree;
        while (cur != null) {
            BinaryTree mostRight = cur.left;
            if (mostRight != null) { // 有左树
                // 去左树最右节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            callback.apply(cur);
            cur = cur.right;
        }
    }

    static class BinaryTree {
        public int value;
        public BinaryTree left;
        public BinaryTree right;
        public BinaryTree parent;

        public BinaryTree(int value) {
            this.value = value;
        }

        public BinaryTree(int value, BinaryTree parent) {
            this.value = value;
            this.parent = parent;
        }
    }
}
