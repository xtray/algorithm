package LeetCode;

import java.util.*;

// IMP: 分治, 回看!!

public class Problem_1755_MinAbsDifference {

    public static int minAbsDifference(int[] nums, int goal) {
        if (nums == null || nums.length == 0) {
            return goal;
        }
        TreeSet<Integer> leftMap = new TreeSet<>();
        TreeSet<Integer> rightMap = new TreeSet<>();
        int mid = nums.length >> 1;
        process(nums, 0, mid, 0, leftMap);
        process(nums, mid, nums.length, 0, rightMap);
        int ans = Math.abs(goal); // NOTE: goal有可能是负数
        for (int num : leftMap) { // 整合逻辑
            int gap = goal - num;
            if (rightMap.ceiling(gap) != null) {
                int ceil = rightMap.ceiling(gap); // 找一个>=最接近的
                ans = Math.min(ans, Math.abs(ceil - gap));
            }
            if (rightMap.floor(gap) != null) {
                int floor = rightMap.floor(gap); // 找一个<=最接近的
                ans = Math.min(ans, Math.abs(gap - floor));
            }
        }
        return ans;
    }

    private static void process(int[] nums, int index, int end, int sum, Set<Integer> map) {
        if (index == end) { // 一个都不要的时候就是 0
            map.add(sum);
            return;
        }
        // 可能 1, 不要index 位置的数
        process(nums, index + 1, end, sum, map);
        // 可能 2, 要 index 位置的数
        process(nums, index + 1, end, sum + nums[index], map);
    }


    public static int[] l = new int[1 << 20]; // 所有的情况共 2^20 这么多
    public static int[] r = new int[1 << 20];

    public static int minAbsDifference1(int[] nums, int goal) {
        if (nums == null || nums.length == 0) {
            return goal;
        }
        int N = nums.length;
        // 数组拆分为左右两半
        int le = process(nums, 0, N >> 1, 0, 0, l);
        int re = process(nums, N >> 1, N, 0, 0, r);
        Arrays.sort(l, 0, le);
        Arrays.sort(r, 0, re--); // 最后77行那个++, 多加了一个, 需要减去
        int ans = Math.abs(goal);
        // le 数组从 0 开始
        // re 数组从最大开始, 相当于双指针了
        for (int i = 0; i < le; i++) { // 从 le 数组里取出每一个数, 到 re 里找最匹配的数
            int rest = goal - l[i];
            // 从最后一个开始, 最后一个是最大值, 必然 gap 最大
            while (re > 0 && Math.abs(rest - r[re - 1]) <= Math.abs(rest - r[re])) {
                re--; // NOTE: 前一个更接近,就往左移动
            }
            ans = Math.min(ans, Math.abs(rest - r[re]));
        }
        return ans;
    }

    // end 是到不了的位置
    // index...end 所有子序列累加和
    // fill 是填充到的位置, 结果最后填充到 arr数组里
    public static int process(int[] nums, int index, int end, int sum, int fill, int[] arr) {
        if (index == end) {
            arr[fill++] = sum;
        } else {
            // 返回 fill 是为了记录已经填充到的位置
            fill = process(nums, index + 1, end, sum, fill, arr);
            fill = process(nums, index + 1, end, sum + nums[index], fill, arr);
        }
        return fill;
    }

    // https://leetcode-cn.com/problems/closest-subsequence-sum/solution/shu-zu-chai-fen-zhuang-tai-ya-suo-dp-shu-538p/
    // 枚举所有子集的和，使用状态压缩的DP
    // 关于枚举所有子集的和，可以使用状态压缩的DP完成。对于n个数的数组nums，显然它的所有子集有1 << n个(包括0)，
    // 这些子集刚好和集合[0,1,2...(1 << n) - 1]一一对应。所以我们可以用这些整数来表示这个集合，
    // 如果该整数的二进制位中某一位i为1，就表示该子集选中了nums[i]，如果为0就表示没有选中它。
    //
    // 对于某个集合i，假设它任意一个为1的位置是j，那么sums[i]=sums[i ^ j] + nums[j]
    // 也就是说，对于集合i，在加入nums[j]以前，它的和是sums[i ^ j]，i ^ j刚好将i的第j位置为0，
    // 表示没有选择nums[j]，所以第j个数加进来之后就是sums[i ^ j] + nums[j]。
    // 这样我们从小到大遍历所有的i即可求出所有sums，该顺序保证了求到sums[i]时，
    // sums[i ^ j]一定是已经计算过的，因为i ^ j一定小于i。
    // i   :  100010001
    // j   :  000010000
    // i^j :  100000001
    // 对数组 index..end 范围求和所有值

    public static int minAbsDifference2(int[] nums, int goal) {
        if (nums == null || nums.length == 0) {
            return goal;
        }
        int half = nums.length >> 1;
        int[] l = new int[1 << half];
        int[] r = new int[1 << (nums.length - half)];
        getAllSum(nums, 0, l);
        getAllSum(nums, half, r);
        Arrays.sort(l);
        Arrays.sort(r);
        int ans = Math.abs(goal);
        // le 数组从 0 开始
        // re 数组从最大开始, 相当于双指针了
        int re = r.length - 1;
        for (int i = 0; i < l.length; i++) { // 从 le 数组里取出每一个数, 到 re 里找最匹配的数
            int rest = goal - l[i];
            // 从最后一个开始, 最后一个是最大值, 必然 gap 最大
            while (re > 0 && Math.abs(rest - r[re - 1]) <= Math.abs(rest - r[re])) {
                re--; // NOTE: 前一个更接近,就往左移动
            }
            ans = Math.min(ans, Math.abs(rest - r[re]));
        }
        return ans;
    }


    private static void getAllSum(int[] nums, int startIndex, int[] sums) {
        int N = sums.length;
        // sums[0]: 一个数也没有的, 就是 0
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) { // 枚举sum数组里每一个位置
                if ((i & (1 << j)) != 0) {
                    sums[i] = sums[i ^ (1 << j)] + nums[j + startIndex];
                    break; // NOTE: 重要加速!!
                }
            }
        }
    }

    // 用数组0~N-1的下标代表 N = 2^K 个数组(K为原数组长度)
    // 求解每一个下标为 i 的数组累加和 相当于把i位置上每一个为1位置的原数组位置相加
    private static void getAllSum2(int[] nums, int index, int[] arr) {
        int N = arr.length;
        // 提取每一个位上的 1
        for (int i = 0; i < N; i++) {
            // 提取i下标每一个1
            for (int j = 0; j < 32; j++) {
                if ((i & (1 << j)) != 0) { // i 在j位置上有1
                    arr[i] += nums[j + index];
                }
            }
        }
    }

    public static int minAbsDifference3(int[] nums, int goal) {
        if (nums == null || nums.length == 0) {
            return goal;
        }
        int half = nums.length >> 1;
        int[] l = new int[1 << half];
        int[] r = new int[1 << (nums.length - half)];
        getAllSum2(nums, 0, l);
        getAllSum2(nums, half, r);
        Arrays.sort(l);
        Arrays.sort(r);
        int ans = Math.abs(goal);
        // le 数组从 0 开始
        // re 数组从最大开始, 相当于双指针了
        int re = r.length - 1;
        for (int i = 0; i < l.length; i++) { // 从 le 数组里取出每一个数, 到 re 里找最匹配的数
            int rest = goal - l[i];
            // 从最后一个开始, 最后一个是最大值, 必然 gap 最大
            while (re > 0 && Math.abs(rest - r[re - 1]) <= Math.abs(rest - r[re])) {
                re--;
            }
            ans = Math.min(ans, Math.abs(rest - r[re]));
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5, -7, 3, 5, 4};
        int goal = 6;
        int ans = minAbsDifference(arr, goal);
        int ans3 = minAbsDifference1(arr, goal);
        int ans4 = minAbsDifference2(arr, goal);
        int ans5 = minAbsDifference3(arr, goal);
        System.out.println(ans);
        System.out.println(ans3);
        System.out.println(ans4);
        System.out.println(ans5);
    }
}
