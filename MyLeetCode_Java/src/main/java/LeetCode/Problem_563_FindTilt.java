package LeetCode;

public class Problem_563_FindTilt {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public class NodeInfo{
        int leftSum; // 节点左子树的和值
        int rightSum; // 节点右子树的和值
        int tilt;//节点子树的梯度和
        public NodeInfo(int l, int r, int t){
            leftSum = l;
            rightSum = r;
            tilt = t;
        }
    }

    public int findTilt(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int[] tiltSum = new int[1];
        process(root, tiltSum);
        return tiltSum[0];
    }

    // 返回以 node 为头的节点的信息
    private NodeInfo process(TreeNode node, int[] sum) {
        if(node ==null) {
            return new NodeInfo(0,0, 0);
        }
        NodeInfo left = process(node.left, sum);
        NodeInfo right = process(node.right, sum);
        int leftSum = left.leftSum + left.rightSum ;
        leftSum += (node.left == null) ? 0 : node.left.val;
        int rightSum = right.leftSum + right.rightSum;
        rightSum += (node.right == null) ? 0 : node.right.val;
        int tilt = Math.abs(leftSum - rightSum);
        sum[0] += tilt;
        return new NodeInfo(leftSum, rightSum, tilt);
    }


    public int findTilt2(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int[] tiltSum = new int[1];
        process2(root, tiltSum);
        return tiltSum[0];
    }

    private int process2(TreeNode root, int[] tiltSum) {
        if(root == null) {
            return 0;
        }
        int leftSum = process2(root.left, tiltSum);
        int rightSum = process2(root.right, tiltSum);
        tiltSum[0] += Math.abs(leftSum -rightSum);
        return leftSum + rightSum + root.val;
    }

}
