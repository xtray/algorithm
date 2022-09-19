package LeetCode;

public class Problem_263_UglyNumber {

    public boolean isUgly(int n) {
        if (n <= 0) return false;
        int[] factors = {2, 3, 5};
        for (int num : factors) {
            while (n % num == 0) {
                n /= num;
            }
        }
        return n == 1;
    }

}
