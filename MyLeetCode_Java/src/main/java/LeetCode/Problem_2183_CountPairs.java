package LeetCode;

public class Problem_2183_CountPairs {
    public long countPairs(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        int N = nums.length;
        int[] count = new int[N];
        for(int i = N -1; i>=0; i--) {
            int cur = nums[i];
            cur %= k;

        }

        return 0;

    }

    private static int gcd(int a, int b) {
        return b == 0? a : gcd(b, a%b);
    }
}
