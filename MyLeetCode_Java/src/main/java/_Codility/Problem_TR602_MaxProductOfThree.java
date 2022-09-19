package _Codility;

import java.util.Arrays;

/**
 * MaxProductOfThree
 *
 * Maximize A[P] * A[Q] * A[R] for any triplet (P, Q, R).
 *
 * https://app.codility.com/demo/results/trainingVN63P5-6ES/
 */
public class Problem_TR602_MaxProductOfThree {
    public int solution(int[] A) {
        Arrays.sort(A);
        int N = A.length;
        return Math.max(A[N - 1] * A[N - 2] * A[N - 3], A[N - 1] * A[0] * A[1]);
    }
}
