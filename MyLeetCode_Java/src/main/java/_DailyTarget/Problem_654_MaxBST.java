package _DailyTarget;

import java.util.HashMap;
import java.util.Map;

public class Problem_654_MaxBST {

    public static class TreeNode {
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

    public static class RMQ {
        public int[][] max;

        public RMQ(int[] arr) {
            int n = arr.length;
            int k = power2(n);
            max = new int[n + 1][k + 1]; // 下标从1开始
            for (int i = 1; i <= n; i++) {
                max[i][0] = arr[i - 1];
            }
            // max[i][j]: 从下标i开始连续2^j个数的最大值
            for (int j = 1; (1 << j) <= n; j++) {
                for (int i = 1; i + (1 << j) - 1 <= n; i++) {
                    max[i][j] = Math.max(
                            max[i][j - 1],
                            max[i + (1 << (j - 1))][j - 1]);
                }
            }
        }

        public int max(int l, int r) {
            // l...r -> r - l + 1 -> 2的哪个次方最接近它！
            int k = power2(r - l + 1);
            return Math.max(max[l][k], max[r - (1 << k) + 1][k]);
        }

        private int power2(int m) {
            int ans = 0;
            // m >> 1 : m先减半为了退出循环时 ans 不用--
            while ((1 << ans) <= (m >> 1)) {
                ans++;
            }
            return ans;
        }

    }


    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        RMQ rmq = new RMQ(nums);
        int N = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            map.put(nums[i], i + 1);
        }
        return process(1, nums.length, rmq, map);

    }

    private TreeNode process(int L, int R, RMQ rmq, Map<Integer, Integer> map) {
        if (L > R) return null;
        int maxVal = rmq.max(L, R);
        int maxPos = map.get(maxVal);
        TreeNode root = new TreeNode(maxVal);
        root.left = process(L, maxPos - 1, rmq, map);
        root.right = process(maxPos + 1, R, rmq, map);
        return root;
    }


}
