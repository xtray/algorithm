package _Contest.LCP.LCP2022_FALL;

public class Problem_A {

    public int temperatureTrend(int[] A, int[] B) {
        int N = A.length;
        int cnt = 0;
        int maxCnt = 0;

        for (int i = 1; i < N; i++) {
            int curA = A[i] > A[i - 1] ? 1 : (A[i] == A[i - 1] ? 0 : -1);
            int curB = B[i] > B[i - 1] ? 1 : (B[i] == B[i - 1] ? 0 : -1);
            if (curA == curB) {
                cnt++;
                maxCnt = Math.max(maxCnt, cnt);
            } else {
                cnt = 0;
            }
        }
        return maxCnt;
    }

}
