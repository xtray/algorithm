package _LintCode;

public class Problem_445_CosineSimilarity {

    public double cosineSimilarity(int[] A, int[] B) {
        if (A == null || A.length == 0 || B == null || B.length == 0) {
            return 2.0D;
        }
        int N = A.length;
        long sum = 0;
        long sumA = 0;
        long sumB = 0;
        for (int i = 0; i < N; i++) {
            sum += (long) A[i] * B[i];
            sumA += Math.pow(A[i], 2);
            sumB += Math.pow(B[i], 2);
        }
        if (sumA == 0 || sumB == 0) {
            return 2.0D;
        }
        return (double) sum / (Math.sqrt(sumA) * Math.sqrt(sumB));
    }
}
