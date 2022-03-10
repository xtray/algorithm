package _Test;

import java.util.PriorityQueue;

public class MergeSort {

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] nums, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(nums, L, mid);
        process(nums, mid + 1, R);
        merge(nums, L, mid, R);
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int p1 = l;
        int p2 = m + 1;
        int[] help = new int[r - l + 1];
        int i = 0;
        while (p1 <= m && p2 <= r) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for ( i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
    }

    // 非递归方法实现
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        int mergeSize = 1;
        while (mergeSize < N) {
            int L = 0;
            while (L < N) {
                if(mergeSize > N - L) { // 第一块都不够, 这样写为了防止溢出
                    break;
                }
                int mid = L + mergeSize - 1;

                int R = Math.min(mid + mergeSize, N-1);
                merge(arr, L, mid, R);
                L = R + 1;
            }
            if(mergeSize > N /2) {
                break;
            }
            mergeSize <<= 1;
        }

    }

    public static void main(String[] args) {
        int[] arr = {3, 7, 6, 8, 2};
        mergeSort2(arr);
        for (var num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
