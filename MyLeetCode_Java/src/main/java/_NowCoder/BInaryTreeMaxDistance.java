package _NowCoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;


// https://www.nowcoder.com/questionTerminal/88331be6da0d40749b068586dc0a2a8b


// IMP: 二叉树节点的最大距离, 非常非常重要


public class BInaryTreeMaxDistance {


    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static int maxDistance(TreeNode head) {
        return process(head).maxDistance;
    }

    public static class Info {
        public int maxDistance;
        public int height;

        public Info(int m, int h) {
            maxDistance = m;
            height = h;
        }

    }

    public static Info process(TreeNode x) {
        if (x == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int p1 = leftInfo.maxDistance;
        int p2 = rightInfo.maxDistance;
        int p3 = leftInfo.height + rightInfo.height + 1;
        int maxDistance = Math.max(Math.max(p1, p2), p3);
        return new Info(maxDistance, height);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().split(" ");
        int n = Integer.parseInt(params[0]), rootVal = Integer.parseInt(params[1]);
        // 构建树
        HashMap<Integer, TreeNode> map = new HashMap<>();
        TreeNode root = new TreeNode(rootVal);
        map.put(rootVal, root);
        for (int i = 0; i < n; i++) {
            params = br.readLine().split(" ");
            int val = Integer.parseInt(params[0]);
            int leftVal = Integer.parseInt(params[1]);
            int rightVal = Integer.parseInt(params[2]);
            TreeNode node = map.get(val);
            if (leftVal != 0) {
                node.left = new TreeNode(leftVal);
                map.put(leftVal, node.left);
            }
            if (rightVal != 0) {
                node.right = new TreeNode(rightVal);
                map.put(rightVal, node.right);
            }
        }
        System.out.println(maxDistance(root));
    }
}