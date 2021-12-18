package _BasicConcept;

public class ReversePair {

    public static int reverPairNumber(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    // arr[L..R]既要排好序, 也要返回逆序对数量
    // merge 操作时, 统计所有逆序对数量返回
    private static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    // merge 过程既要排序, 又统计逆序对数量
    private static int merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int p1 = r;
        int p2 = mid + 1;
        int i = help.length - 1;
        int res = 0;
        while (p1 >= l && p2 > mid) {
            res += arr[p1] > arr[p2] ? p2 - mid : 0; // 相等的时候
            help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p1 >= l) {
            help[i--] = arr[p1--];
        }
        while (p2 > mid) {
            help[i--] = arr[p2--];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return res;
    }
}
