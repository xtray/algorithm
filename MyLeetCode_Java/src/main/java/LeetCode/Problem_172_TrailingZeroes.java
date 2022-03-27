package LeetCode;

public class Problem_172_TrailingZeroes {

    // n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
    // 把所有5的因子数出来，就是后面有多少个零
    public int trailingZeroes(int n) {
        int ans = 0;
        while (n!=0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }
}
