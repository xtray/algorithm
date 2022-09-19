package LeetCode;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

public class Problem_654_MaxBinaryTree {
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

    public TreeNode constructMaximumBinaryTree0(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return dfs(nums, 0, nums.length - 1);
    }

    private TreeNode dfs(int[] nums, int L, int R) {
        if (R < L) {
            return null;
        }
        // 找到L...R上的最大值，构造根节点，并以此位置分割成左右子树
        int maxPos = L;
        for (int i = L + 1; i <= R; i++) {
            if (nums[i] > nums[maxPos]) {
                maxPos = i;
            }
        }
        TreeNode node = new TreeNode(nums[maxPos]);
        node.left = dfs(nums, L, maxPos - 1);
        node.right = dfs(nums, maxPos + 1, R);
        return node;
    }


    private Map<Integer, Integer> map;
    private RMQ rmq;

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        map = new HashMap<>();
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            map.put(nums[i], i + 1);
        }
        rmq = new RMQ(nums);

        return process(nums, 1, N);

    }

    private TreeNode process(int[] nums, int L, int R) {
        if (L == R) {
            return new TreeNode(nums[L - 1]);
        }
        if (L > R) {
            return null;
        }
        int maxVal = rmq.max(L, R);
        TreeNode root = new TreeNode(maxVal);
        int pos = map.get(maxVal);
        root.left = process(nums, L, pos - 1);
        root.right = process(nums, pos + 1, R);
        return root;
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

    // https://leetcode.cn/problems/maximum-binary-tree/solution/by-tong-zhu-btly/
    // 单调栈 - 每次处理一段区间的最大值
    // 栈底元素是当前节点为头的二叉树的最大值
    // 从栈底到栈顶 从大到小
    // 从前往后处理所有的nums[i], 若存在栈顶元素, 且栈顶元素比当前元素要小, 则栈顶元素应为当前元素的左侧节点
    // 同时为了确保最终nums[i]的左节点为[0;
    // ,i−1] 范围的最大值，需要确保在构建nums[i]节点与其左节点的关系时，
    // [0,i−1] 中的最大值最后出队，此时可知容器栈具有「单调递减」特性。
    // 当处理完nums[i]节点与其左节点关系后，可明确nums[i]可作为未出栈的栈顶元素的右节点。
    // 3, 2, 1, 6, 0, 5
    // 对于节点3, 在碰到比他大的元素6之前都是他的发挥空间
    public TreeNode constructMaximumBinaryTree1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        for (int num : nums) {
            TreeNode node = new TreeNode(num);
            while (!stack.isEmpty() && num > stack.peekFirst().val) {
                // 当前值比栈顶大, 栈顶是当前节点的左侧节点
                node.left = stack.pollFirst(); // 不断刷新, 用最大的覆盖
            }

            if (!stack.isEmpty()) {
                stack.peekFirst().right = node;
            }
            stack.addFirst(node);
        }
        // 返回栈底的元素，也就是最大值
        // 此方法为双端队列的方法，严格意义并不是栈的方法
        return stack.peekLast();
    }

    // 数组做栈
    public TreeNode constructMaximumBinaryTree2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int N = nums.length;
        TreeNode[] stack = new TreeNode[N]; // 数组做栈
        int index = 0; // 待存入的栈顶
        for (int num : nums) {
            TreeNode node = new TreeNode(num);
            while (index != 0 && num > stack[index - 1].val) {
                node.left = stack[--index];
            }
            if (index != 0) {
                stack[index - 1].right = node;
            }
            stack[index++] = node;
        }
        // 返回栈底的元素，也就是最大值
        return stack[0];
    }


    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 6, 0, 5};
        var ans = new Problem_654_MaxBinaryTree().constructMaximumBinaryTree(nums);
        System.out.println(ans);
    }

}
