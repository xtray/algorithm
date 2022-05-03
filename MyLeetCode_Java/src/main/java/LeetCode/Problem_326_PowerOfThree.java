package LeetCode;

public class Problem_326_PowerOfThree {

    // 1162261467
    public static int maxPowOfThree() {
        long n = 1;
        while (n * 3 < Integer.MAX_VALUE) {
            n *= 3;
        }
        return (int) n;
    }


    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }

    // 1是3的0次方
    public boolean isPowerOfThree2(int n) {
        if (n <= 0) {
            return false;
        }
        while ( n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }
}
