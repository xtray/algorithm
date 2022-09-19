package _Codility;

/**
 * BinaryGap
 * Find longest sequence of zeros in binary representation of an integer.
 * https://app.codility.com/demo/results/trainingNCPE23-XVD/
 */

public class Problem_TR101_BinaryGap {

    public int solution(int N) {
        // write your code in Java SE 8
        int i = 0;
        int ans = 0;
        int cnt = 0;
        boolean flag = false;

        while (i < 32) {
            if ((N & (1 << i)) != 0) {
                // get result
                if (!flag) {
                    flag = true;
                    i++;
                    continue;
                }
                // flag = true
                if (cnt != 0) {
                    ans = Math.max(ans, cnt);
                    cnt = 0;
                }
            } else {
                if (flag) {
                    cnt++;
                }
            }
            i++;
        }
        return ans;
    }
}
