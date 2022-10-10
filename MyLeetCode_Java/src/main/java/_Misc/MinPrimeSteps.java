package _Misc;

import java.util.*;

/**
 * ”#小红的取素因子操作“，给定一个数n，有两种操作，
 * 操作一，将这个数减去1，操作二，用这个数的素数因子整除。
 * 只能先使用操作一，再使用操作二，求将这个数变为1的最小操作次数。n的范围小于1e5
 */

public class MinPrimeSteps {

    private static final int limit = (int) 1e5;
    boolean[] isPrim = new boolean[limit + 1]; // 1~num

    {
        Arrays.fill(isPrim, true);
        // 先假设所有数都是质数, 然后把不是的划掉
        isPrim[0] = false;
        isPrim[1] = false;
        // 从 2 开始枚举到 sqrt(n)。
        for (int i = 2; i * i <= limit; i++) {
            // 如果当前是素数
            if (isPrim[i]) {
                for (int j = i * i; j <= limit; j += i) { // 1个i, 两个i, 3个i...
                    isPrim[j] = false;
                }
            }
        }
    }

    private int getMinSteps(int n) {
        return process(n, 0);
    }

    private int process(int n, int cnt) {
        if (n == 1) {
            return 0;
        }
        if (cnt > 16) {
            return Integer.MAX_VALUE;
        }
        // 可能性1:
        int p1 = Integer.MAX_VALUE;
        int next = process(n - 1, cnt + 1);
        if (next != Integer.MAX_VALUE) {
            p1 = Math.min(p1, 2 + next);
        }
        // 可能性2:
        n--;
        // 找n所有的素数因子
        Set<Integer> list = getAllPrimes(n);
        int p2 = Integer.MAX_VALUE;
        for (int prime : list) {
            next = process(n / prime, cnt + 1);
            if (next != Integer.MAX_VALUE) {
                p2 = Math.min(p2, 2 + next);
            }
        }
        return Math.min(p1, p2);
    }

    private Set<Integer> getAllPrimes(int num) {
        Set<Integer> res = new HashSet<>();
        for (int j = 1; j * j <= num; j++) { // j是现在试的因子
            if (num % j == 0) { // num含有j的因子
                if (j != 1 && isPrim[j]) { // 这个因子不是1
                    // j, 当前数是含有j因子的第一个数
                    res.add(j);
                }
                int other = num / j; // other * j == num
                if (other != 1 && isPrim[j]) { // num含有other的因子
                    res.add(other);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 100000;
        var ans = new MinPrimeSteps().getMinSteps(n);
        System.out.println(ans);
    }
}
