package _Codility;

/**
 * MinAvgTwoSlice
 *
 * Find the minimal average of any slice containing at least two elements.
 *
 * https://app.codility.com/demo/results/training9P6GJK-96C/
 *
 * ref:
 * https://stackoverflow.com/questions/21635397/min-average-two-slice-codility
 */
public class Problem_TR504_MinAvgTwoSlice {

    //
    public int solution(int[] A) {
        if (A.length == 2) {
            return 0;
        }
        int N = A.length;
        int[] sum = new int[N];
        int pre = 0;
        for (int i = 0; i < N; i++) {
            sum[i] = A[i] + pre;
            pre = sum[i];
        }
        int start = 0;
        double minAvg = Double.MAX_VALUE;
        int minIdx = 0;
        for (int i = 1; i < N; i++) {
            double curAvg = (start == 0 ? sum[i] : (sum[i] - sum[start - 1])) / (double) (i - start + 1);
            if (curAvg < minAvg) {
                minAvg = curAvg;
                minIdx = start;
            }
            if (A[i] < minAvg) { // 比平均值小的数, 会让平均值降的更细
                start = i;
            }
        }
        return minIdx;
    }

    // https://funnelgarden.com/minavgtwoslice-codility-solution/
    // We know from the problem description that the slices are a minimum length of 2.
    // The trick to this problem is that the min average slice also cannot be longer
    // than 3. So we only have to calculate the avg of the slices of length 2 and 3.
    public int solution2(int[] A) {
        if (A.length == 2) {
            return 0;
        }
        int sum1 = 0;
        int sum2 = 0;
        double minAvg = Double.MAX_VALUE;
        double curAvg1 = Double.MAX_VALUE;
        double curAvg2 = Double.MAX_VALUE;
        int minIdx = 0; // 如果只有两个数, 答案就是0
        int N = A.length;
        // 2~3个一组检查
        for (int i = 0; i < N - 2; i++) {
            sum1 = A[i] + A[i + 1];
            curAvg1 = sum1 / 2.0d;
            if (curAvg1 < minAvg) {
                minAvg = curAvg1;
                minIdx = i;
            }
            sum2 = sum1 + A[i + 2];
            curAvg2 = sum2 / 3.0d;
            if (curAvg2 < minAvg) {
                minAvg = curAvg2;
                minIdx = i;
            }
        }
        // 检查最后剩下的两个
        curAvg1 = (A[N - 2] + A[N - 1]) / 2.0d;
        if (curAvg1 < minAvg) {
            minIdx = N - 2;
        }
        return minIdx;
    }

    public static void main(String[] args) {
        int[] A = {4, 2, 2, 5, 1, 5, 8}; // 1
        var ans = new Problem_TR504_MinAvgTwoSlice().solution(A);
        System.out.println(ans);
    }
}
