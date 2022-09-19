package _Codility;

/**
 * TapeEquilibrium
 * Minimize the value |(A[0] + ... + A[P-1]) - (A[P] + ... + A[N-1])|.
 * https://app.codility.com/demo/results/training73E9XH-YJZ/
 */

public class Problem_TR303_TapeEquilibrium {

    public int solution(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int num : A) sum += num;
        int left = 0;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < A.length - 1; i++) {
            left += A[i];
            ans = Math.min(ans, Math.abs(sum - 2 * left));
        }
        return ans;
    }
}
