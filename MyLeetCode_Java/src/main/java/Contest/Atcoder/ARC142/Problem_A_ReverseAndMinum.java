package Contest.Atcoder.ARC142;

import java.util.*;

// https://atcoder.jp/contests/arc142

public class Problem_A_ReverseAndMinum {


    // 从k出发
    public static int getNumbers(long n, long k) {
        long fx = getFx(k);
        if (fx != k) {
            return 0;
        }
        Set<Long> set = new HashSet<>();
        long start = k;
        while (start <= n) {
            set.add(start);
            start *= 10;
        }
        start = getReverse(k);
        while (start <= n) {
            set.add(start);
            start *= 10;
        }
        return set.size();
    }

    private static long getFx(long k) {
        while (getReverse(k) < k) {
            k = getReverse(k);
        }
        return k;
    }

    private static long getReverse(long k) {
        long ans = 0;
        while (k != 0) {
            ans = ans * 10 + k % 10;
            k /= 10;
        }
        return ans;
    }


    public static void main(String[] args) {
        // int N = 1420;
        // int K = 142;
        // int N = 1419;
        // int K = 142;
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong();
        long K = sc.nextLong();
        sc.close();

        var ans = getNumbers(N, K);
        System.out.println(ans);

        // int times = 1000;
        // long N_MAX = (long) 1e12;
        // long K_MAX = (long) 1e12;
        //
        // for (int i = 0; i < times; i++) {
        //     long n = (long) (Math.random() * N_MAX) + 1;
        //     long k = (long) (Math.random() * K_MAX) + 1;
        //     var ans1 = getNumbers(n, k);
        //
        // }

    }
}
