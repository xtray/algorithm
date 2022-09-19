package LeetCode;

public class Problem_204_CountPrimes {

    public static boolean isPrime(int n) {
        if (n < 3) return n > 1; // 1不是质数, 2是质数
        for (int i = 2; i <= (int) Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // TLE
    public int countPrimes(int n) {
        int cnt = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) cnt++;
        }
        return cnt;
    }

    // 埃氏筛法, 返回<n的素数
    public int countPrimes1(int n) {
        if (n < 3) { // 即: <2的质数
            return 0;
        }
        // j已经不是素数了，f[j] = true;
        boolean[] notPrime = new boolean[n];
        int count = n / 2; // 所有偶数都不要，还剩几个数
        // 跳过了1、2    3、5、7、
        for (int i = 3; i * i < n; i += 2) { // 只看奇数, 质数除2以外都是奇数
            if (notPrime[i]) continue; // 跳过标记过的
            // 3 -> 3 * 3 = 9   3 * 5 = 15   3 * 7 = 21
            // 7 -> 7 * 7 = 49  7 * 9 = 63
            // 13 -> 13 * 13  13 * 15
            // i是素数, 标记i的所有n以下的倍数为非素数, 跳过偶数倍的
            // j = i * i --> j = i*i + i --> j = i*i + 2*i = j + 2*i
            //      奇数         偶数(跳过)          奇数(下一个)
            // i是奇数, 奇数^2 还是奇数
            for (int j = i * i; j < n; j += 2 * i) {
                if (!notPrime[j]) { // 没有标记过, 标记为非素数, 总个数-1
                    --count;
                    notPrime[j] = true;
                }
            }
        }
        return count;
    }

}
