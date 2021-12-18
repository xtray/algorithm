package AlgoExpert;

import java.util.LinkedList;
import java.util.Queue;

public class Problem_000_RightSiblingTree {

    public static BinaryTree rightSiblingTree(BinaryTree root) {
        if (root == null) {
            return null;
        }
        BinaryTree node = process(root);
        return node;
    }

    // 二叉树按层遍历
    private static BinaryTree process(BinaryTree root) {
        if (root == null) {
            return null;
        }
        Queue<BinaryTree> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            BinaryTree pre = null;
            for (int i = 0; i < size; i++) {
                BinaryTree bt = queue.poll();
                // 1. 先把子节点填充队列
                if (bt != null) {  // 弹出节点有可能是空, 因为用 null 占位
                    queue.add(bt.left);
                    queue.add(bt.right);
                }
                // 2. 上个节点的右指针指向当前 (可能空, 可能不空)
                if (pre != null) {
                    pre.right = bt;
                }
                // 3. 更新 pre 为当前  (可能空, 可能不空)
                pre = bt;
            }
            // 4. 设置最右一个节点的右指针为空
            if(pre!=null) {
                pre.right = null;
            }
        }
        return root;
    }

    // This is the class of the input root. Do not edit it.
    static class BinaryTree {
        int value;
        BinaryTree left = null;
        BinaryTree right = null;

        public BinaryTree(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Problem_000_RightSiblingTree sl = new Problem_000_RightSiblingTree();
        BinaryTree root = new BinaryTree(1);
        root.left = new BinaryTree(2);
        root.right = new BinaryTree(3);
        root.left.left = new BinaryTree(4);
        root.left.right = new BinaryTree(5);
        root.left.left.left = new BinaryTree(8);
        root.left.left.right = new BinaryTree(9);
        root.left.right.right = new BinaryTree(10);

        root.right.left = new BinaryTree(6);
        root.right.right = new BinaryTree(7);
        root.right.left.left = new BinaryTree(11);
        root.right.left.left.left = new BinaryTree(14);
        root.right.right.left = new BinaryTree(12);
        root.right.right.right = new BinaryTree(13);

        BinaryTree res = rightSiblingTree(root);
        System.out.println();

    }
}
