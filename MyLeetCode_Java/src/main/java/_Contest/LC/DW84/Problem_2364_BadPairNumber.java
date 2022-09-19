package _Contest.LC.DW84;

import java.util.HashMap;
import java.util.Map;

public class Problem_2364_BadPairNumber {

    public long countBadPairs(int[] nums) {
        int N = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            map.put(nums[i] - i, map.getOrDefault(nums[i] - i, 0) + 1);
        }
        long best = 0;
        for (int num : map.values()) {
            if (num > 1) {
                best += c(num, 2);
            }
        }
        long total = (long) N * (N - 1) / 2;
        return total - best;
    }

    public static long c(int a, int b) {
        if (a == b) {
            return 1;
        }
        long x = 1;
        long y = 1;
        for (int i = b + 1, j = 1; i <= a; i++, j++) {
            x *= i;
            y *= j;
            long gcd = gcd(x, y);
            x /= gcd;
            y /= gcd;
        }
        return x / y;
    }

    private static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
