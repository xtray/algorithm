package _aTemplate;

import java.util.Arrays;

public class PrimeNumber {
    public static boolean isPrime(int n) {
        // if (n < 3) return n > 1;
        if (n <= 1) return false;
        // for (int i = 2; i <= (int) Math.sqrt(n); i++) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // 返回<=n的素数
    public static int countPrimes1(int n) {
        int ans = 0;
        for (int i = 2; i <= n; ++i) {
            ans += isPrime(i) ? 1 : 0;
        }
        return ans;
    }


    /**
     * 为什么从x*x开始 为什么到 sqrt（x）就停止。
     *
     * 假如我们要判断9是不是质数。
     * 如果它不是质数，一定有两个数相乘等于它。这两个因数一定有一个因数小于等于根号9。也就是3。
     * 那么反过来，我们已经把比3小的数的所有的倍数都排除了。
     * 那么对于3到9之间的数，比如7 它如果不是质数，它的一个因子应该是小于3。
     * 但是小于3的数的所有倍数都被我们排除了。所以当我们验证到3的倍数的时候，最小的倍数就是3的平方。
     */
    // 埃氏筛法, 返回<=n的素数
    public static int countPrimes2(int n) {
        boolean[] isPrim = new boolean[n + 1]; // 1~n
        Arrays.fill(isPrim, true);
        // 先假设所有数都是质数, 然后把不是的划掉
        isPrim[0] = false;
        isPrim[1] = false;
        // 从 2 开始枚举到 sqrt(n)。
        for (int i = 2; i * i <= n; i++) {
            // 如果当前是素数
            if (isPrim[i]) {
                /*
                 * 任意素数x的倍数有：2x, 3x, 4x, ..., x*x, (x+1)*x, ...
                 * 任意小于x*x的倍数都被之前的素数筛过滤过，如：2 过滤 2x, 4x, ...，3 过滤 3x, ...
                 * 所以从x*x开始过滤之后的倍数，所以x只需遍历到sqrt(N)
                 * 就把从 i*i 开始，i 的所有倍数都设置为 false。
                 */
                for (int j = i * i; j <= n; j += i) { // 1个i, 两个i, 3个i...
                    isPrim[j] = false;
                }
            }
        }

        // 计数
        int cnt = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrim[i]) {
                cnt++;
            }
        }
        return cnt;
    }

    // IMP:课上讲的方法
    // ref: Problem_204_CountPrimes
    // 这里是 < n的质数个数
    public static int countPrimes3(int n) {
        if (n < 3) { // n 如果 ==2, 求<2的质数就是0个
            return 0;
        }
        // j已经不是素数了，f[j] = true;
        boolean[] notPrime = new boolean[n];
        // 因为是求<n的质数个数, 这里 n/2 相当于向上取整了, 并没有抠除2这个质数
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

    public static void main(String[] args) {
        int n = 2;
        var ans = isPrime(n);
        System.out.println(ans);

        var ans1 = countPrimes1(1000);
        System.out.println(ans1);

        var ans2 = countPrimes2(1000);
        System.out.println(ans2);

        var ans3 = countPrimes3(1000);
        System.out.println(ans3);
    }
}
