package _Codility;


// https://app.codility.com/demo/results/training75968V-JWZ/

public class Problem_TR302_MissingElement {

    public int solution(int[] A) {
        if(A == null || A.length ==0) {
            return 1;
        }
        int N = A.length;
        int i = 0;
        while (i < N) {
            if (A[i] - 1 == N) {
                i++;
            } else if (A[i] - 1 != i) {
                swap(A, A[i] - 1, i);
            } else {
                i++;
            }
        }
        for(i = 0; i< N; i++) {
            if(A[i] -1 != i) {
                return i+1;
            }
        }
        return N+1;
    }

    private static void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}
