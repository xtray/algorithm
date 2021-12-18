package _BasicConcept;

public class InsertSort {

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr);
    }

    // 插入排序
    private static void process(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            // 0~i-1有序区
            for (int j = i - 1; arr[j + 1] < arr[j] && j >= 0; j--) {
                swap(arr, i, j + 1);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
