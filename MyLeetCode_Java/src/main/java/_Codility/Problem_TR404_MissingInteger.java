package _Codility;

/**
 * MissingInteger
 * Find the smallest positive integer that does not occur in a given sequence.
 * https://app.codility.com/demo/results/training96JM5C-FKZ/
 */

public class Problem_TR404_MissingInteger {

    public int solution(int[] A) {
        // write your code in Java SE 8
        if (A == null || A.length == 0) {
            return 1;
        }
        int N = A.length; // 1...N
        int R = N; // 无效区
        for (int i = 0; i < R;) { // num --> index: num-1
            if (A[i] == i + 1) {
                i++;
                continue;
            }
            if (A[i] <= 0 || A[i] > N || A[i] == A[A[i]-1]) {
                swap(A, --R, i);
            } else {
                swap(A, i, A[i]-1);
            }
        }
        return R + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] num = {1,3,6,4,1,2};
        var ans = new Problem_TR404_MissingInteger().solution(num);
        System.out.println(ans);
    }


}
