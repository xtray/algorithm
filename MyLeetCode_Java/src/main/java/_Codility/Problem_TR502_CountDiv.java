package _Codility;

/**
 * CountDiv
 *
 * Compute number of integers divisible by k in range [a..b].
 * 计算A~B之间能被K整除的数字个数
 * 数据量 A,B,K 2*10^9
 * https://app.codility.com/demo/results/trainingTPJP87-5W9/
 */
public class Problem_TR502_CountDiv {

    public int solution(int A, int B, int K) {
        // NOTE: 0 模任何数等于0, 特例
        int start = A == 0 ? 0 : K; // K>=A
        if (A > K) {
            start = A + A % K;
        }
        int end = B - B % K;
        if (start > end) {
            return 0;
        }
        // start ... end 有多少个能被K整除
        return (end - start) / K + 1;
    }

    public static void main(String[] args) {
        // int A = 0;
        // int B = (int) 2e9;
        // int K = 1;
        int A = 0;
        int B = (int) 2e9;
        int K = (int) 2e9;
        var ans = new Problem_TR502_CountDiv().solution(A, B, K);
        System.out.println(ans);

    }
}
