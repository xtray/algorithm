package LeetCode;

public class Problem_172_TrailingZeroes {

    // n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
    // 把所有5的因子数出来，就是后面有多少个零
    // 5的倍数会产生一个5的因子，比如5、10、15、20
    // 5^2的倍数会产生两个5的因子，比如25、50、75、100
    // 5^3的倍数会产生三个5的因子，比如125、250、375、500
    public static int trailingZeroes(int n) {
        int ans = 0;
        while (n!=0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 27;
        // 27...25...20...15...10...5...
        //      5*5 5*4   5*3  5*2 5*1
        var ans = trailingZeroes(n);
        System.out.println(ans);
    }
}
