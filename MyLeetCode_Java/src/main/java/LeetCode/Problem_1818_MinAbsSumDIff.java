package LeetCode;

import java.util.Arrays;

public class Problem_1818_MinAbsSumDIff {

    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int mod = (int) 1e9 + 7;
        int N = nums1.length;
        int[] copy = Arrays.copyOf(nums1, N);
        Arrays.sort(copy);
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum = (sum + Math.abs(nums1[i] - nums2[i])) % mod;
        }
        // 尝试替换每一个i
        int diff = 0;
        for (int i = 0; i < N; i++) {
            int cur = nums2[i];
            int pos1 = getLessEqual(copy, cur);
            int pos2 = getMoreEqual(copy, cur);
            int orgGap = Math.abs(nums1[i] - nums2[i]);
            int gap = Integer.MAX_VALUE;
            if (pos1 != -1) {
                gap = Math.abs(nums2[i] - copy[pos1]);
            }
            if (pos2 != -1) {
                gap = Math.min(gap, Math.abs(nums2[i] - copy[pos2]));
            }
            if (gap < orgGap) {
                diff = Math.max(diff, orgGap - gap);
            }
        }
        return (sum - diff + mod) % mod;
    }

    // 有序数组中找到>= k, 离k最近的
    private int getMoreEqual(int[] arr, int k) {
        int L = 0;
        int R = arr.length - 1;
        int ans = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= k) {
                ans = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return ans;
    }

    // 有序数组中找到<= k, 离k最近的
    private int getLessEqual(int[] arr, int k) {
        int L = 0;
        int R = arr.length - 1;
        int ans = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] <= k) {
                ans = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 10, 4, 4, 2, 7};
        int[] nums2 = {9, 3, 5, 1, 7, 4};
        var ans = new Problem_1818_MinAbsSumDIff().minAbsoluteSumDiff(nums1, nums2);
        System.out.println(ans);
    }
}
