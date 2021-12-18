package AlgoExpert;

public class Problem_000_NodeDepths {
    public static int nodeDepths(BinaryTree root) {
        if (root == null) {
            return 0;
        }
        return process(root).sum;
    }

    public static class Info {
        public int num; // 以 x 为头的二叉树所有节点个数
        public int sum; // 所有子树的高度和

        public Info(int n, int s) {
            num = n;
            sum = s;
        }
    }

    // 收集每一个节点的高度, 然后累加
    // 二叉树的递归套路
    private static Info process(BinaryTree x) {
        if (x == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int num = leftInfo.num + rightInfo.num;
        int sum = leftInfo.sum + rightInfo.sum + num;
        return new Info(num + 1, sum);
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
}
