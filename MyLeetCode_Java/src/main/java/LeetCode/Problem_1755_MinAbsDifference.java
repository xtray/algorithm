package LeetCode;

import java.util.*;

public class Problem_1755_MinAbsDifference {

    public static int minAbsDifference(int[] nums, int goal) {
        if (nums == null || nums.length == 0) {
            return goal;
        }
        TreeSet<Integer> leftMap = new TreeSet<>();
        TreeSet<Integer> rightMap = new TreeSet<>();
        int mid = nums.length >> 1;
//        leftMap.add(0);
//        rightMap.add(0);
        // 一个都不要的时候就是 0
        process(nums, 0, mid, 0, leftMap);
        process(nums, mid, nums.length, 0, rightMap);
        // 整合逻辑
        int ans = Math.abs(goal);
        for (int num : leftMap) {
            int gap = goal - num;
            // 在 right 找一个最接近的
            if (rightMap.ceiling(gap) != null) {
                int ceil = rightMap.ceiling(gap);
                ans = Math.min(ans, Math.abs(ceil - gap));
            }
            if (rightMap.floor(gap) != null) {
                int floor = rightMap.floor(gap);
                ans = Math.min(ans, Math.abs(gap - floor));
            }
        }
        return ans;
    }

    private static void process(int[] nums, int index, int end, int sum, Set<Integer> map) {
        if (index == end) {
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

    public static int minAbsDifference2(int[] nums, int goal) {
        if (nums == null || nums.length == 0) {
            return goal;
        }
        int le = process(nums, 0, nums.length >> 1, 0, 0, l);
        int re = process(nums, nums.length >> 1, nums.length, 0, 0, r);
        Arrays.sort(l, 0, le);
        Arrays.sort(r, 0, re--);
        int ans = Math.abs(goal);
        // le 数组从 0 开始
        // re 数组从最大开始, 相当于双指针了
        for (int i = 0; i < le; i++) { // 从 le 数组里取出每一个数, 到 re 里找最匹配的数
            int rest = goal - l[i];
            // 从最后一个开始, 最后一个是最大值, 必然 gap 最大
            while (re > 0 && Math.abs(rest - r[re - 1]) <= Math.abs(rest - r[re])) {
                re--;
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
    // 关于枚举所有子集的和，可以使用状态压缩的DP完成。对于n个数的数组nums，显然它的所有子集有1 << n个，
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

    private static void getAllSum(int[] nums, int index, int end, int[] sums) {
        // sums[0]: 一个数也没有的, 就是 0
        for (int i = 1; i < sums.length; ++i) {
            for (int j = 0, bit = 1; bit < sums.length; j++, bit <<= 1) {
                if ((i & bit) != 0) {
                    sums[i] = sums[i ^ bit] + nums[j + index];
                    break;
                }
            }
        }
    }



    public static int minAbsDifference3(int[] nums, int goal) {
        if (nums == null || nums.length == 0) {
            return goal;
        }
        int half = nums.length/2;
        int[] l = new int[1 << half];
        int[] r = new int[1 << (nums.length -half)];
        getAllSum(nums, 0, nums.length >> 1, l);
        getAllSum(nums, nums.length >> 1, nums.length, r);
        Arrays.sort(l);
        Arrays.sort(r);
        int ans = Math.abs(goal);
        // le 数组从 0 开始
        // re 数组从最大开始, 相当于双指针了
        int re = r.length-1;
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

    private static void getAllSum4(int[] nums, int index, int len, int[] arr) {
        // arr[0]: 一个数也没有的, 就是 0
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < len; j++) {
                if ((i & (1<<j)) !=0 ) { // 依次考察状态 i 上的每一位上的 1
                    arr[i] = arr[i ^ (1<<j)] + nums[j + index];
                    break; // 这个 break 很重要, 提速了!!!
                }
            }
        }
    }

    // 把 i 这个状态,每一位上有 1 就找对应 arr 加起来
    private static void getAllSum5(int[] nums, int index, int len, int[] arr) {
        // 提取每一个位上的 1
        for(int i = 0; i < 1<<len; i++) { // 枚举每一个状态
            for(int j = 0; j < len; j++) { // 枚举每一个 bit 位
                if((i & (1<<j))!=0){ // 必须是不等于 0, 因为那个值是 000010000 这种!!!
                    arr[i]+=nums[j + index];
                }
            }
        }
    }

    public static int minAbsDifference4(int[] nums, int goal) {
        if (nums == null || nums.length == 0) {
            return goal;
        }
        int half = nums.length/2;
        int[] l = new int[1 << half];
        int[] r = new int[1 << (nums.length -half)];
        getAllSum4(nums, 0, half, l);
        getAllSum4(nums, nums.length >> 1, nums.length - half, r);
        Arrays.sort(l);
        Arrays.sort(r);
        int ans = Math.abs(goal);
        // le 数组从 0 开始
        // re 数组从最大开始, 相当于双指针了
        int re = r.length-1;
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

    public static int minAbsDifference5(int[] nums, int goal) {
        if (nums == null || nums.length == 0) {
            return goal;
        }
        int half = nums.length/2;
        int[] l = new int[1 << half];
        int[] r = new int[1 << (nums.length -half)];
        getAllSum5(nums, 0, half, l);
        getAllSum5(nums, nums.length >> 1, nums.length - half, r);
        Arrays.sort(l);
        Arrays.sort(r);
        int ans = Math.abs(goal);
        // le 数组从 0 开始
        // re 数组从最大开始, 相当于双指针了
        int re = r.length-1;
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
        int[] arr = new int[]{5, -7, 3, 5,4};
        int goal = 6;
        int ans = minAbsDifference(arr, goal);
        int ans3 = minAbsDifference3(arr, goal);
        int ans4 = minAbsDifference4(arr, goal);
        int ans5 = minAbsDifference5(arr, goal);
        System.out.println(ans);
        System.out.println(ans3);
        System.out.println(ans4);
        System.out.println(ans5);
    }
}
