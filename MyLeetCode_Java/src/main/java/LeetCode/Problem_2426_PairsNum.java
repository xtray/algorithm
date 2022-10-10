package LeetCode;

public class Problem_2426_PairsNum {

    public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
        int N = nums1.length;
        for (int i = 0; i < N; i++) {
            nums1[i] -= nums2[i];
        }

        return reverPairNumber(nums1, diff);
    }

    public static long reverPairNumber(int[] arr, int diff) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1, diff);
    }


    public static long process(int[] arr, int l, int r, int diff) {
        if (l == r) {
            return 0;
        }
        // l < r
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid, diff) + process(arr, mid + 1, r, diff) + merge(arr, l, mid, r, diff);
    }

    public static long merge(int[] arr, int l, int m, int r, int diff) {
        int[] help = new int[r - l + 1];
        int i = help.length - 1;
        int p1 = l;
        int p2 = m+1;
        long res = 0;
        while (p1 <= m && p2 <= r) {
            if (arr[p1] <= arr[p2] + diff) {
                res += r - p2 + 1;
                p1++;
            } else {
                p2++;
            }
        }

        p1 = m;
        p2 = r;
        while (p1 >= l && p2 > m) {
            help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p1 >= l) {
            help[i--] = arr[p1--];
        }
        while (p2 > m) {
            help[i--] = arr[p2--];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return res;
    }
}
