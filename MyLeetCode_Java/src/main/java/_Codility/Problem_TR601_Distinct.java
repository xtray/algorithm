package _Codility;

/**
 * Distinct
 *
 * Compute number of distinct values in an array.
 * https://app.codility.com/demo/results/trainingRQKUF9-PJ9/
 */

public class Problem_TR601_Distinct {

    public int solution(int[] A) {
        boolean[] set = new boolean[(int) 2e6 + 1];
        int base = (int) 1e6;
        int size = 0;
        for (int num : A) {
            if (!set[num + base]) {
                set[num + base] = true;
                size++;
            }
        }
        return size;
    }
}
