package LeetCode;

public class Problem_2256_MinAverageDiff {

    public int minimumAverageDifference(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        long[] sum = new long[N + 1];
        for (int i = 0; i < N; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        long minAverage = Long.MAX_VALUE;
        int minPos = -1;
        for (int i = 0; i < N; i++) {
            long left = getRangeSum(sum, 0, i);
            long right = getRangeSum(sum, i + 1, N - 1);
            int averageLeft = (int) (left / (i + 1));
            int averageRight = i + 1 > N - 1 ? 0 : (int) (right / (N - 1 - i));
            int average = Math.abs(averageLeft - averageRight);
            if (average < minAverage) {
                minAverage = average;
                minPos = i;
            }
        }
        return minPos;
    }

    private long getRangeSum(long[] sum, int L, int R) {
        return L > R ? 0 : sum[R + 1] - sum[L];
    }

}
