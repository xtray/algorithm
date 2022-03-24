package LeetCode.BINARY.MEDIUM;

public class Problem_007_ReverseInteger {

    // NOTE: 转成负数取解决, 不用long
    public int reverse(int x) {
        boolean isNeg = ((1 << 31) & x) != 0;
        int minD = Integer.MIN_VALUE / 10;
        int minM = Integer.MIN_VALUE % 10;
        x = isNeg ? x : -x;
        int res = 0;
        while (x != 0) {
            if (res < minD || (res == minD && x % 10 < minM)) {
                return 0;
            }
            res = res * 10 + x % 10;
            x /= 10;
        }
        return isNeg ? res : -res;
    }
}
