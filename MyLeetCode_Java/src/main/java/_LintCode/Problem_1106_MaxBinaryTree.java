package _LintCode;

import java.util.HashMap;
import java.util.Map;

public class Problem_1106_MaxBinaryTree {

    public static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    private static RMQ rmq;
    private static Map<Integer, Integer> map;

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        rmq = new RMQ(nums);
        map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        return process(nums, 0, nums.length - 1);
    }

    // 返回在L..R范围上构建最大二叉树
    private TreeNode process(int[] nums, int L, int R) {
        if (L == R) {
            return new TreeNode(nums[L]);
        }
        if (L > R) {
            return null;
        }
        int max = rmq.max(L + 1, R + 1);
        int maxPos = map.get(max);
        TreeNode head = new TreeNode(max);
        head.left = process(nums, L, maxPos - 1);
        head.right = process(nums, maxPos + 1, R);
        return head;
    }

    public static class RMQ {
        public int[][] max;

        // 下标一定要从1开始，没有道理！就是约定俗成！
        public RMQ(int[] arr) {
            // 长度！
            int n = arr.length;
            // 2的几次方，可以拿下n
            int k = power2(n);
            // n*logn
            max = new int[n + 1][k + 1];
            for (int i = 1; i <= n; i++) {
                // i 0：从下标i开始，往下连续的2的0次方个数，中，最大值
                // 1...1个
                // 2...1个
                // 3...1个
                max[i][0] = arr[i - 1];
            }
            for (int j = 1; (1 << j) <= n; j++) {
                // i...连续的、2的1次方个数，这个范围，最大值
                // i...连续的、2的2次方个数，这个范围，最大值
                // i...连续的、2的3次方个数，这个范围，最大值
                for (int i = 1; i + (1 << j) - 1 <= n; i++) {
                    // max[10][3]
                    // 下标10开始，连续的8个数，最大值是多少
                    // 1) max[10][2]
                    // 2) max[14][2]
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
}
