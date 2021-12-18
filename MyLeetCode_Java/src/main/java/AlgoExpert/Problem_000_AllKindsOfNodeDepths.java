package AlgoExpert;

import java.util.LinkedList;


public class Problem_000_AllKindsOfNodeDepths {

    // 基本的 DFS 实现
    // O(n*logn), Space: O(h)
    public static int allKindsOfNodeDepths(BinaryTree root) {
        if (root == null) {
            return 0;
        }
        LinkedList<BinaryTree> stack = new LinkedList<>();
        stack.add(root);
        int sumAll = 0;
        while (!stack.isEmpty()) {
            BinaryTree node = stack.poll();
            sumAll += nodeDepth(node, 0);
            if(node.left!=null) {
                stack.add(node.left);
            }
            if(node.right!=null) {
                stack.add(node.right);
            }
        }
        return sumAll;
    }

    // 基本的递归方法
    // O(n*logn), Space: O(h)
    public static int allKindsOfNodeDepths1(BinaryTree root) {
        if (root == null) {
            return 0;
        }
        return allKindsOfNodeDepths1(root.left) + allKindsOfNodeDepths1(root.right) + nodeDepth(root, 0);
    }

    public static int nodeDepth(BinaryTree node, int depth) {
        if (node == null) {
            return 0;
        }
        return depth + nodeDepth(node.left, depth + 1) + nodeDepth(node.right, depth + 1);
    }

    public static class Info {
        public int num; // 以 x 为头的二叉树所有节点个数
        public int sum; // 以 x 为头的子树的深度累加和
        public int allSum;// 所有节点为头的子树的深度累加和

        public Info(int n, int s, int a) {
            num = n;
            sum = s;
            allSum = a;
        }
    }

    // 收集每一个节点的高度, 然后累加
    // 二叉树的递归套路
    private static Info process(BinaryTree x) {
        if (x == null) {
            return new Info(0, 0, 0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int num = leftInfo.num + rightInfo.num;
        int sum = leftInfo.sum + rightInfo.sum + num;
        int allSum = leftInfo.allSum + rightInfo.allSum + sum;
        return new Info(num + 1, sum, allSum);
    }

    // 用二叉树的递归套路
    public static int allKindsOfNodeDepths3(BinaryTree root) {
        if (root == null) {
            return 0;
        }
        return process(root).allSum;
    }

    static class BinaryTree {
        int value;
        BinaryTree left;
        BinaryTree right;

        public BinaryTree(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }

    public static void main(String[] args) {
        var root = new BinaryTree(1);
        root.left = new BinaryTree(2);
        root.left.left = new BinaryTree(4);
        root.left.left.left = new BinaryTree(8);
        root.left.left.right = new BinaryTree(9);
        root.left.right = new BinaryTree(5);
        root.right = new BinaryTree(3);
        root.right.left = new BinaryTree(6);
        root.right.right = new BinaryTree(7);
        int ans = allKindsOfNodeDepths(root);
        System.out.println(ans);
    }
}
