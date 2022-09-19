package LeetCode;

public class Problem_1979_GCD {

    public int findGCD(int[] nums) {
        int N = nums.length;
        int min = nums[0];
        int max = nums[0];

        for (int i = 1; i < N; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        return gcd(min, max);
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
