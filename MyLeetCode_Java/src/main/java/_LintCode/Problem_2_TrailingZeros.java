package _LintCode;

public class Problem_2_TrailingZeros {

    public long trailingZeros(long n) {
        // write your code here
        long ans = 0;
        while (n != 0) {
            n /= 5;
            ans += n; // n含有5的个数
        }
        return ans;
    }
}
