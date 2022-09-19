package _Codility;

/**
 * PermCheck
 * Check whether array A is a permutation.
 * https://app.codility.com/demo/results/trainingAWGKRP-XKF/
 */

public class Problem_TR402_PermCheck {

    public int solution(int[] A) {
        int N = A.length;
        // 数组做set
        boolean[] set = new boolean[N + 1];
        int size = 0; // 只统计1~N范围的数
        for (int num : A) {
            if (num >= 1 && num <= N && !set[num]) {
                set[num] = true;
                size++;
            }
        }
        return size == N ? 1 : 0;
    }

}
