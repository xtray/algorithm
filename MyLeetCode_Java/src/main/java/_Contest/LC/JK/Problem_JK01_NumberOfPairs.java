package _Contest.LC.JK;

import java.util.HashMap;
import java.util.Map;

public class Problem_JK01_NumberOfPairs {

    public int numberOfPairs(int[] nums) {
        int mod = (int) 1e9 + 7;
        Map<Long, Integer> map = new HashMap<>();
        for (int num : nums) {
            long reverse = getReverseNum(num);
            long digit = num - reverse;
            map.put(digit, map.getOrDefault(digit, 0) + 1);
        }
        long ans = 0;
        for (int val : map.values()) {
            if(val >=2) {
                ans = (ans + c(val, 2)) % mod;
            }
        }
        return (int) ans;
    }

    private long getReverseNum(int num) {
        long cur = 0;
        while (num != 0) {
            cur = cur * 10 + num % 10;
            num /= 10;
        }
        return cur;
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

    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        // int[] nums = {17, 28, 39, 71}; // 3
        int[] nums = {0, 4, 5, 0, 2, 1, 6, 4, 3, 2, 2, 4, 3, 7, 6, 1, 4, 3, 4, 2}; // 190
        var ans = new Problem_JK01_NumberOfPairs().numberOfPairs(nums);
        System.out.println(ans);
    }
}
