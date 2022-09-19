package _Misc;

import java.util.Arrays;

public class BasicArraySort {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 5, 7, 1, 3, 4, 2};
        Arrays.sort(arr);
        int i = 0, j = arr.length - 1;
        while (i < j) {
            swap(arr, i++, j--);
        }
        System.out.println(Arrays.toString(arr));
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
