package _BasicConcept;

import java.util.Arrays;

public class RadixSort {

    // only for no-negative value
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxbits(arr));
    }

    // arr[L..R]排序
    // digit: 最大值的十进制位数
    private static void radixSort(int[] arr, int L, int R, int digit) {
        int radix = 10;
        int[] help = new int[R - L + 1];
        for (int d = 1; d <= digit; d++) { // 按最大位数一共循环多少次
            int[] count = new int[radix];
            for (int i = L; i <= R; i++) {
                int curDigit = getDigit(arr[i], d); // 获取当前数字d位上的数
                count[curDigit]++;
            }
            // 生成count'前缀和数组 0~9
            for (int i = 1; i < radix; i++) {
                count[i] += count[i - 1];
            }
            // 从右往左遍历数组, 填help数组
            for(int i = R; i>=L; i--) {
                int curDigit = getDigit(arr[i], d);
                help[count[curDigit] - 1] = arr[i];
                count[curDigit]--;
            }
            // help-->arr
            for(int i = L; i<=R; i++) {
                arr[i] = help[i];
            }
        }
    }

    private static int getDigit(int num, int d) {
        int mask = (int) Math.pow(10, d - 1);
        return (num / mask) % 10;
    }

    private static int maxbits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int num : arr) {
            max = Math.max(max, num);
        }
        int len = 0;
        while (max > 0) {
            len++;
            max /= 10;
        }
        return len;
    }


    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            radixSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        radixSort(arr);
        printArray(arr);

    }
}
