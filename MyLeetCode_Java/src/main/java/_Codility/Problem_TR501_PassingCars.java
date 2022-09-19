package _Codility;


/**
 * PassingCars
 * Count the number of passing cars on the road.
 * https://app.codility.com/demo/results/trainingWW9QJ4-ZZF/
 */
public class Problem_TR501_PassingCars {

    // 统计每个0后面有几个1
    public int solution(int[] A) {
        final int limit = (int) 1e9;
        int N = A.length;
        int[] right = new int[N];
        right[N - 1] = A[N - 1] == 1 ? 1 : 0;
        for (int i = N - 2; i >= 0; i--) {
            right[i] = right[i + 1];
            if (A[i] == 1) {
                right[i]++;
            }
        }
        int ans = 0;
        for (int i = 0; i < N - 1; i++) {
            if (A[i] == 0) {
                ans += right[i + 1];
                if (ans > limit) {
                    return -1;
                }
            }
        }
        return ans;
    }
}
