package LeetCode;

import java.util.Arrays;

// IMP: 二分+双指针(不回退) O(logN * N) 难理解, 多看!!

public class Problem_719_KSmallDistance {

    public int smallestDistancePair(int[] arr, int k) {
        int N = arr.length;
        if (N < 2 || k < 1 || k > ((N * (N - 1)) >> 1)) {
            return -1; // 无效情况
        }
        Arrays.sort(arr);
        // 0 ~ 大-小 二分
        // l  ~  r
        int left = 0;
        int right = arr[N - 1] - arr[0]; // 最大差值
        int mid = 0;
        int rightest = -1;
        while (left <= right) {
            mid = (left + right) / 2;
            // 数字对差值的绝对值<=mid的数字对个数，是不是 < k个的！
            if (valid(arr, mid, k)) {
                rightest = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return rightest + 1;
    }

    // 假设arr中的所有数字对，差值绝对值<=limit的个数为x
    // 如果 x < k，达标，返回true
    // 如果 x >= k，不达标，返回false
    public static boolean valid(int[] arr, int limit, int k) {
        int x = 0;
        for (int l = 0, r = 1; l < arr.length; r = Math.max(r, ++l)) {
            while (r < arr.length && arr[r] - arr[l] <= limit) {
                r++;
            }
            x += r - l - 1;
        }
        return x < k;
    }
}
