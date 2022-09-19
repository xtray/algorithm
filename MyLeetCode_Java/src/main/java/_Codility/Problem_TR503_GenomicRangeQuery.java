package _Codility;

import java.util.Arrays;

/**
 * GenomicRangeQuery
 *
 * Find the minimal nucleotide from a range of sequence DNA.
 * 查找区间上的最小值
 * https://app.codility.com/demo/results/trainingTC773T-2XN/
 */

public class Problem_TR503_GenomicRangeQuery {

    public int[] solution(String S, int[] P, int[] Q) {
        char[] str = S.toCharArray();
        int N = str.length;
        int[][] sum = new int[N][4];
        if (str[0] == 'A') sum[0][0] = 1;
        if (str[0] == 'C') sum[0][1] = 1;
        if (str[0] == 'G') sum[0][2] = 1;
        if (str[0] == 'T') sum[0][3] = 1;
        for (int i = 1; i < N; i++) {
            switch (str[i]) {
                case 'A':
                    sum[i][0] = sum[i - 1][0] + 1;
                    sum[i][1] = sum[i - 1][1];
                    sum[i][2] = sum[i - 1][1];
                    sum[i][3] = sum[i - 1][1];
                    break;
                case 'C':
                    sum[i][1] = sum[i - 1][1] + 1;
                    sum[i][2] = sum[i - 1][1];
                    sum[i][3] = sum[i - 1][1];
                    sum[i][0] = sum[i - 1][0];
                    break;
                case 'G':
                    sum[i][2] = sum[i - 1][2] + 1;
                    sum[i][3] = sum[i - 1][1];
                    sum[i][0] = sum[i - 1][0];
                    sum[i][1] = sum[i - 1][1];
                    break;
                case 'T':
                    sum[i][3] = sum[i - 1][3] + 1;
                    sum[i][0] = sum[i - 1][0];
                    sum[i][1] = sum[i - 1][1];
                    sum[i][2] = sum[i - 1][2];
                    break;
                default:
                    break;
            }
        }
        int M = P.length;
        int[] ans = new int[M];
        for (int i = 0; i < M; i++) {
            int low = P[i];
            int hi = Q[i];
            for (int j = 0; j < 4; j++) {
                int cur = low == 0 ? sum[hi][j] : sum[hi][j] - sum[low - 1][j];
                if (cur > 0) {
                    ans[i] = j + 1;
                    break;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "CAGCCTA";
        int[] p = new int[]{2, 5, 0};
        int[] q = new int[]{4, 5, 6};
        var ans = new Problem_TR503_GenomicRangeQuery().solution(s, p, q);
        System.out.println(Arrays.toString(ans));
    }
}
