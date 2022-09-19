package _Codility;


/**
 * MaxSliceSum
 * 最大子数组累加和
 * Find a maximum sum of a compact subsequence of array elements.
 *
 * https://app.codility.com/demo/results/trainingWHW3BJ-H44/
 */

public class Problem_TR902_MaxSliceSum {

    public int solution(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int N = A.length;
        int ans = A[0];
        int cur = A[0];
        for (int i = 1; i < N; i++) {
            cur = A[i] + Math.max(0, cur);
            ans = Math.max(ans, cur);
        }
        return ans;
    }
}
