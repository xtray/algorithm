package _Codility;

/**
 * MaxProfit
 *
 * Given a log of stock prices compute the maximum possible earning.
 * https://app.codility.com/demo/results/trainingGXYVNQ-D85/
 */
public class Problem_TR901_MaxProfit {

    public int solution(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int maxProfit = 0;
        int preMin = A[0];
        for(int num : A) {
            preMin = Math.min(preMin, num);
            maxProfit = Math.max(maxProfit, num - preMin);
        }
        return maxProfit;
    }
}
