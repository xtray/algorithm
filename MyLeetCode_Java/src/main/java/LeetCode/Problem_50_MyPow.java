package LeetCode;

public class Problem_50_MyPow {

    // x的n次方，n可能是负数
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1D;
        }
        // 注意负数最小值转正数会溢出
        int pow = Math.abs(n == Integer.MIN_VALUE ? n + 1 : n);
        double ans = 1.0;
        double t = x;
        while (pow > 0) {
            if ((pow & 1) != 0) {
                ans *= t;
            }
            t *= t;
            pow >>= 1;
        }
        if (n == Integer.MIN_VALUE) { // 补上少算的一个
            ans *= x;
        }
        return n < 0 ? 1D / ans : ans;
    }

    public static void main(String[] args) {
        double x = -1;
        int n = Integer.MIN_VALUE;
        var ans = new Problem_50_MyPow().myPow(x, n);
        System.out.println(ans);
    }
}
