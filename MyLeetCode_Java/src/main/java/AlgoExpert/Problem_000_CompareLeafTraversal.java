package AlgoExpert;

import java.util.*;

public class Problem_000_CompareLeafTraversal {
    // This is an input class. Do not edit.
    static class BinaryTree {
        public int value;
        public BinaryTree left = null;
        public BinaryTree right = null;

        public BinaryTree(int value) {
            this.value = value;
        }
    }

    // BFS遍历二叉树收集叶子节点
    public boolean compareLeafTraversal(BinaryTree tree1, BinaryTree tree2) {
        if (tree1 == null && tree2 == null) {
            return true;
        }
        if (tree1 == null || tree2 == null) {
            return false;
        }
        String str1 = getLeafValue(tree1);
        String str2 = getLeafValue(tree2);
        return str1.equals(str2);
    }

    // 根据输入的二叉树做 DFS 前序遍历收集叶子节点转换为String 返回
    private String getLeafValue(BinaryTree root) {
        LinkedList<BinaryTree> stack = new LinkedList<>();
        stack.add(root);
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            BinaryTree node = stack.pop();
            if (node.left == null && node.right == null) {
                sb.append(node.value);
                continue;
            }
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Problem_000_CompareLeafTraversal sl = new Problem_000_CompareLeafTraversal();
        BinaryTree tree1 = new BinaryTree(1);
        tree1.left = new BinaryTree(2);
        tree1.right = new BinaryTree(3);
        tree1.left.left = new BinaryTree(4);
        tree1.left.right = new BinaryTree(5);
        tree1.right.right = new BinaryTree(6);
        tree1.left.right.left = new BinaryTree(7);
        tree1.left.right.right = new BinaryTree(8);

        BinaryTree tree2 = new BinaryTree(1);
        tree2.left = new BinaryTree(2);
        tree2.right = new BinaryTree(3);
        tree2.left.left = new BinaryTree(4);
        tree2.left.right = new BinaryTree(7);
        tree2.right.right = new BinaryTree(5);
        tree2.right.right.left = new BinaryTree(8);
        tree2.right.right.right = new BinaryTree(6);

        var res = sl.compareLeafTraversal(tree1, tree2);
        System.out.println(res);
    }
}
