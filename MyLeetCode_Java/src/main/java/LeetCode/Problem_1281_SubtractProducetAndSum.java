package LeetCode;

public class Problem_1281_SubtractProducetAndSum {

    public int subtractProductAndSum(int n) {
        if (n == 0) {
            return 0;
        }
        int sum = 0;
        int product = 1;
        while (n != 0) {
            int cur = n % 10;
            sum += cur;
            product *= cur;
            n /= 10;
        }
        return product - sum;
    }
}
