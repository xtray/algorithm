package LeetCode;

public class Problem_231_PowerOfTwo {

    public boolean isPowerOfTwo(int n) {
        if(n <= 0) return false;
        // return n == (n & (-n));
        return n == (n & (~n + 1));
    }
}
