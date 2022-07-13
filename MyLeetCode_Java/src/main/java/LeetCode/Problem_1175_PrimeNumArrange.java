package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_1175_PrimeNumArrange {

    private static final int mod = (int) 1e9 + 7;

    private static final List<Integer> list = new ArrayList<>();

    static {
        for (int i = 2; i <= 100; i++) {
            if (isPrim(i)) {
                list.add(i);
            }
        }
    }

    // 判断一个数是不是质数
    public static boolean isPrim(int n) {
        if (n < 3) return n > 1;
        int limit = (int) Math.sqrt(n);
        for (int i = 2; i <= limit; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int numPrimeArrangements(int n) {
        int L = 0;
        int R = list.size() - 1;
        // 找到第一个满足「值小于等于n」的位置, 确定n以内的质数个数
        while (L < R) {
            int mid = L + ((R - L + 1) >> 1);
            if (list.get(mid) > n) {
                R = mid - 1;
            } else { // <= n
                L = mid;
            }
        }
        int a = R + 1;
        int b = n - a;
        long ans = 1;
        for (int i = b; i > 1; i--) {
            ans = ans * i % mod;
        }
        for (int i = a; i > 1; i--) {
            ans = ans * i % mod;
        }
        return (int) ans;
    }
}
