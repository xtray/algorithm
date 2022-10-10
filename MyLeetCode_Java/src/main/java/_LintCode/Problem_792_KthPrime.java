package _LintCode;

import java.util.Arrays;

public class Problem_792_KthPrime {

    public int kthPrime(int n) {

        // 1...n的所有质数, 第一个质数是2
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
}
