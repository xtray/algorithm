package LeetCode.MianshiJindian;

public class Problem_1001_MergeSortedArray {

    public static void merge(int[] A, int m, int[] B, int n) {
        int idx = m + n - 1;
        m--;
        n--;
        while (m >= 0 && n >= 0) {
            A[idx--] = A[m] >= B[n] ? A[m--] : B[n--];
        }
        while (m >= 0) {
            A[idx--] = A[m--];
        }
        while (n >= 0) {
            A[idx--] = B[n--];
        }
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] B = {2, 5, 6};
        int n = 3;
        merge(A, m, B, n);
    }
}
