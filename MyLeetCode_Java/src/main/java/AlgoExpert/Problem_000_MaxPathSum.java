package AlgoExpert;

public class Problem_000_MaxPathSum {

    public static int maxPathSum(BinaryTree x) {
        if(x == null) {
            return 0;
        }
        return process(x).maxPathSum;
    }

    private static Info process(BinaryTree x) {
        if(x == null) {
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        // x 无关
        int p1 = Integer.MIN_VALUE;
        if(leftInfo != null) {
            p1 = leftInfo.maxPathSum;
        }
        int p2 = Integer.MIN_VALUE;
        if(rightInfo != null) {
            p2 = rightInfo.maxPathSum;
        }
        // x 有关
        int p3 = x.value;
        int p4 = Integer.MIN_VALUE;
        if(leftInfo != null){
            p4 = x.value + leftInfo.maxPathSumFromHead;
        }
        int p5 = Integer.MIN_VALUE;
        if(rightInfo != null){
            p5 = x.value + rightInfo.maxPathSumFromHead;
        }
        int p6 = Integer.MIN_VALUE;
        if(leftInfo!=null && rightInfo!=null
                && leftInfo.maxPathSumFromHead > 0
                && rightInfo.maxPathSumFromHead > 0) {
            p6 = leftInfo.maxPathSumFromHead + x.value + rightInfo.maxPathSumFromHead;
        }
        int maxPathSum = Math.max(p1,p2);
        int maxPathSumFromHead = Math.max(p3, Math.max(p4, p5));
        maxPathSum = Math.max(maxPathSum, Math.max(maxPathSumFromHead, p6));
        return new Info(maxPathSum, maxPathSumFromHead);
    }

    static class Info {
        public int maxPathSum;
        public int maxPathSumFromHead;
        public Info(int pathSum, int head) {
            maxPathSum = pathSum;
            maxPathSumFromHead = head;
        }
    }

    static class BinaryTree {
        public int value;
        public BinaryTree left;
        public BinaryTree right;

        public BinaryTree(int value) {
            this.value = value;
        }
    }
}
