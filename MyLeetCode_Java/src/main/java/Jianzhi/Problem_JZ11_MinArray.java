package Jianzhi;

public class Problem_JZ11_MinArray {

    // 二分, 找<= 最后一个数最左的位置
    public int minArray(int[] arr) {
        int N = arr.length;
        int L = 0;
        int R = N - 1;
        while (L < R) {
            int M = L + ((R - L) >> 1);
            if (arr[M] < arr[R]) {
                R = M;
            } else if (arr[M] > arr[R]) {
                L = M + 1;
            } else {
                R--;
            }
        }
        return arr[L];
    }

    public static void main(String[] args) {
        // int[] arr = {3, 1, 3};
        // int[] arr = {3, 3, 3};
        int[] arr = {1, 3, 5};
        var ans = new Problem_JZ11_MinArray().minArray(arr);
        System.out.println(ans);
    }
}
