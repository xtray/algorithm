package _Codility;

/**
 * MaxDoubleSliceSum
 *
 * Find the maximal sum of any double slice.
 * https://app.codility.com/demo/results/trainingK98QEY-DCP/
 */
public class Problem_TR903_MaxDoubleSliceSum {

    // https://rafal.io/posts/codility-max-double-slice-sum.html

    public int solution(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int N = A.length;
        int[] left = new int[N];
        int[] right = new int[N];

        for (int i = 1; i < N - 1; i++) {
            left[i] = Math.max(0, left[i - 1] + A[i]);
        }
        for (int i = N - 2; i > 0; i--) {
            right[i] = Math.max(0, right[i + 1] + A[i]);
        }

        int max = 0;
        // 枚举中间的每个Y
        for (int i = 1; i < N - 1; i++) {
            max = Math.max(max, left[i - 1] + right[i + 1]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 6, -1, 4, 5, -1, 2};
        var ans = new Problem_TR903_MaxDoubleSliceSum().solution(nums);
        System.out.println(ans);
    }
}
