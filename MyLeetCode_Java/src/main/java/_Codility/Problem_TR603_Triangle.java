package _Codility;

import java.util.Arrays;

/**
 * Triangle
 *
 * Determine whether a triangle can be built from a given set of edges
 *
 * https://app.codility.com/demo/results/trainingJAWASY-YWH/
 */

public class Problem_TR603_Triangle {

    public int solution(int[] A) {
        if (A == null || A.length < 3) {
            return 0;
        }
        Arrays.sort(A);
        int N = A.length;
        for (int i = N - 3; i >= 0; i--) {
            if (A[i] > A[i + 2] - A[i + 1]) { // 防止溢出
                return 1;
            }
        }
        return 0;
    }
}
