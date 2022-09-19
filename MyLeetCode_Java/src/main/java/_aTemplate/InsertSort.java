package _aTemplate;

public class InsertSort {

    // 插入排序
    private static void insertSort(int[] arr) {
        // 0~0已经有序
        // i 从 1 到 N-1 插入到前面的有序区的合适位置
        for (int i = 1; i < arr.length - 1; i++) {
            // 0~i-1是有序区
            for (int j = i - 1; j >= 0 && arr[j + 1] < arr[j]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
