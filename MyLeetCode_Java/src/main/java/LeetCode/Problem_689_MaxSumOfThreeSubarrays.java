package LeetCode;

// 本题测试链接 : https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/


public class Problem_689_MaxSumOfThreeSubarrays {

    public static int[] maxSumArray1(int[] arr) {
        int N = arr.length;
        int[] help = new int[N];
        // help[i] 子数组必须以i位置结尾的情况下，累加和最大是多少？
        help[0] = arr[0];
        for (int i = 1; i < N; i++) {
            int p1 = arr[i];
            int p2 = arr[i] + help[i - 1];
            help[i] = Math.max(p1, p2);
        }
        // dp[i] 在0~i范围上，随意选一个子数组，累加和最大是多少？
        int[] dp = new int[N];
        dp[0] = help[0];
        for (int i = 1; i < N; i++) {
            int p1 = help[i];
            int p2 = dp[i - 1];
            dp[i] = Math.max(p1, p2);
        }
        return dp;
    }

    public static int maxSumLenK(int[] arr, int k) {
        int N = arr.length;
        // help[i] 子数组必须以i位置的数结尾，长度一定要是K，累加和最大是多少？
        // help[0] help[k-2] 不够k 个, 省略 ...
        // help 数组从 k-1 位置开始有意义
        int sum = 0;
        for (int i = 0; i < k - 1; i++) {
            sum += arr[i];
        }
        // 0...k-2 k-1 sum
        int[] help = new int[N];
        for (int i = k - 1; i < N; i++) {
            // 0..k-2
            // 01..k-1
            sum += arr[i];
            help[i] = sum;
            // i == k-1
            sum -= arr[i - k + 1];
        }
        // help[i] - > dp[i]  0-..i  K
        // dp[i] 在0~i范围上，随意选一个子数组长度必须为 K，累加和最大是多少？
        int[] dp = new int[N];
        dp[k - 1] = help[k - 1];
        for (int i = k; i < N; i++) {
            int p1 = help[i];
            int p2 = dp[i - 1];
            dp[i] = Math.max(p1, p2);
        }
        return dp[N - 1];
    }


    // dp[i] : 0~i 的范围上随意选, 只选一个子数组怎么最好: 从左往右
    //  可能性: 以 i 位置结尾, 不以 i 位置结尾
    // help[i]: 子数组必须以 i 位置结尾的情况下的最好收益, 根据 help 数组 求 dp
    // dp'[i] : i~N-1 的范围上只选一个子数组怎么最好: 从右往左
    // 复杂度 O(N)
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        int N = nums.length;
        int[] range = new int[N]; // range[i]: 以i 开头的 k 个数累加和数组
        int[] left = new int[N]; // left[i]: 0~i范围上选子数组, 长度必须为 k, 随意选, 累加和最大值的 开始位置
        int[] right = new int[N]; // left[i]: i~N-1范围上选子数组, 长度必须为 k, 随意选, 累加和最大值的 开始位置
        int sum = 0;
        for (int i = 0; i < k; i++) { // 0~k-1, k 个, 第一组
            sum += nums[i];
        }
        range[0] = sum; // 0--> 0~k-1
        left[k - 1] = 0; // 0~k-2 长度不够 k, 0~k-1, 长度够 k, 最大值的开始位置是 0
        int max = sum; // 0~i 上的最大值-->对应 dp[0]
        for (int i = k; i < N; i++) { // i 是结尾位置
            sum += nums[i] - nums[i - k];
            range[i - k + 1] = sum;
            left[i] = left[i - 1]; // 可能性 1: 跟 i 位置无关, 这个最大值就是 max
            if (sum > max) { // 可能新 2: 必须以 i 位置结尾, 就是sum 值, 两个可能取最大
                // max 是 0~i-1 上的最大值
                max = sum;
                left[i] = i - k + 1; // 保存得到最大值的开始位置
            }
        }
        // 从右往左求
        sum = 0;
        for (int i = N - 1; i >= N - k; i--) {
            sum += nums[i];
        }
        right[N - k] = N - k; // i~N-1 得到最大值的开始位置
        max = sum; // right 数组得到的最大值
        for (int i = N - k - 1; i >= 0; i--) {
            sum += nums[i] - nums[i + k];
            right[i] = right[i + 1]; // 可能性 1: 不要 i 位置, 即 max
            if (sum >= max) { // 可能性 2: 一定要 i 位置开头 k 个, 就是 sum
                // 要下标小, 所以这里要带上等号
                max = sum;
                right[i] = i;
            }
        }
        int a = 0, b = 0, c = 0;
        max = 0;
        for (int i = k; i <= N - 2 * k; i++) { // 枚举中间一块的起始点变化范围
            int part1 = range[left[i - 1]]; // 0~i-1范围上的最大
            int part2 = range[i];
            int part3 = range[right[i + k]];//第三块的开始位置是 i+k
            if (part1 + part2 + part3 > max) {
                max = part1 + part2 + part3;
                a = left[i - 1];
                b = i;
                c = right[i + k];
            }
        }
        return new int[]{a, b, c};
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 1, 2, 6, 7, 5, 1};
        int k = 2;
        var res = new Problem_689_MaxSumOfThreeSubarrays().maxSumOfThreeSubarrays(nums, k);
        for (int num : res) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
