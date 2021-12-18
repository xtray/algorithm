package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/longest-arithmetic-subsequence-of-given-difference/

public class Problem_1218_LongestSubsequence {
    public int longestSubsequence(int[] arr, int diff) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        // dp[i] : 0~i, 以 i 位置结尾的最长等差子序列长度
        // 可能性 1: 最长子序列与 i 位置无关, dp[i] = dp[i-1]
        // 可能性 2: 与 i 位置有关, 最后一个数是 arr[i], 上一个数 arr[i] - diff 的最近出现位置, 假设 j
        //          如果能连接跟当前连到一起, 需要满足 arr[i] - 某个值 == diff
        //          某个值是 dp[j]取到最大值时的子序列的最后一个数
        //          定义 ends 数组, ends[i]: 所有长度为 i+1 的子序列中的最小结尾
        //          条件变为: 如果 arr[i] - ends[j] = diff
        //          dp[i] = dp[j] + 1
        // 可能性 1,2 取最大
        // 为了加速查找, 建立哈希表存储每一个数的位置
        // map<key, value>: key: 数, value: list, 数出现的所有位置
        // 来到 i 位置, 找<i 的上一个位置
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.get(arr[i]) == null) {
                map.put(arr[i], new ArrayList<>());
            }
            List<Integer> list = map.get(arr[i]);
            list.add(i);
        }
        int N = arr.length;
        int[] dp = new int[N];
        int[] ends = new int[N];

        dp[0] = 1;
        ends[0] = arr[0];
        int end = arr[0];
        for (int i = 1; i < N; i++) {
            int p1 = dp[i - 1];
            int p2 = 1;
            // 二分找 arr[i] - diff 上次最近出现位置
            int pos = getPos(map.get(arr[i] - diff), i);
            if (pos != -1 && arr[i] -  ends[pos] == diff) {
                p2 += dp[pos];
                ends[i] = arr[i];
            }
            dp[i] = Math.max(p1, p2);
        }
        return dp[N - 1];
    }

    private int getPos(List<Integer> list, int limit) {
        if (list == null || list.size() == 0) {
            return -1;
        }
        int L = 0;
        int R = list.size() - 1;
        int ans = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (list.get(mid) >= limit) {
                R = mid - 1;
            } else {
                // <
                ans = list.get(mid);
                L = mid + 1;
            }
        }
        return ans;
    }

    public int longestSubsequence2(int[] arr, int diff) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // dp[i] : 0~i, 必须以 i 位置结尾的最长等差子序列长度
        // 可能性 : 最后一个数是 arr[i], 上一个数 arr[i] - diff 的最近出现位置 --> j
        //           dp[i] = dp[j] + 1, 如果没找到, 长度 1
        // 在所有 dp 值求最大即为答案

        // 遍历一遍数组,记录每个值出现的位置
        // Key: 每个数组值, value: list, 出现的位置列表
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.get(arr[i]) == null) {
                map.put(arr[i], new ArrayList<>());
            }
            List<Integer> list = map.get(arr[i]);
            list.add(i);
        }
        int N = arr.length;
        int[] dp = new int[N];

        dp[0] = 1;
        int ans = 0;
        for (int i = 1; i < N; i++) {
            dp[i] = 1; // 至少有一个
            // 二分找 arr[i] - diff 上次最近出现位置
            int pos = getPos(map.get(arr[i] - diff), i);
            if (pos != -1 ) {
                dp[i] += dp[pos];
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public int longestSubsequence3(int[] arr, int diff) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int ans = 1;
        Map<Integer, Integer> dp = new HashMap<>();
        //dp: key: 一个具体的数值, 比如 arr[i]
        //    value:以 arr[i]这个值结尾的子序列最大长度
        for (int i = 0; i < arr.length; i++) {
            dp.put(arr[i], dp.getOrDefault(arr[i] -diff, 0) + 1);
            ans = Math.max(ans, dp.get(arr[i]));
        }

        return ans;
    }

    // 测试
    int[] randomArray(int maxSize, int maxVal) {
        int size = (int) (Math.random() * maxSize) + 1;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * maxVal) - (int) (Math.random() * maxVal);
        }
        return arr;
    }

    public static void main(String[] args) {
        Problem_1218_LongestSubsequence sl = new Problem_1218_LongestSubsequence();
//        int[] arr = new int[]{1,1,1,2,2};
        int[] arr = new int[]{5,7,5,3};
        int diff = -2;
        int ans = sl.longestSubsequence(arr, diff);
        int ans2 = sl.longestSubsequence2(arr, diff);
        System.out.println(ans);
        System.out.println(ans2);

//        int times = 1000000;
//        int maxSize = 10;
//        int maxVal = 10;
//        for (int i=0;i<times;i++) {
//            int[] arr1= sl.randomArray(maxSize, maxVal);
//            int diff1 = (int)(Math.random()*maxVal) - (int)(Math.random()*maxVal);
//            int ans1 = sl.longestSubsequence(arr1, diff1);
//        }
    }

}
