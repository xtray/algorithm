package LeetCode;

// https://leetcode-cn.com/problems/super-pow/solution/372-chao-ji-ci-fang-by-jue-qiang-zha-zha-m0lj/
public class Problem_372_SuperPow {
    public int superPow(int a, int[] b) {
        if (b == null || b.length == 0) {
            return -1;
        }
        return dfs(a, b, b.length - 1, 1337);
    }

    // a ^ k = a ^{(k/10)*10 + k%10} = (a^{k/10})^{10} * a^{k%10}
    // k%10 -->最后一位
    // 当前处理到 index 位置
    private int dfs(int a, int[] b, int index, int MOD) {
        if (index == -1) {
            return 1;
        }
        return (myPow(a, b[index], MOD) * myPow(dfs(a, b, --index, MOD), 10, MOD)) % MOD;
    }

    // (a^k) % MOD
    // 求模运算：(a * b) % k = (a % k)(b % k) % k，其中a*b可能溢出，用此公式转换后可以避免溢出风险。
    private int myPow(int a, int k, int MOD) {
        if (k == 0) {
            return 1;
        }
        if ((k & 1) != 0) { // 奇数, -1 变成偶数, a 要先 mod 一下, 否则溢出
            return ((a % MOD) * myPow(a, k - 1, MOD)) % MOD;
        }
        // 偶数
        int half = myPow(a, k >> 1, MOD) % MOD;
        return (half * half) % MOD;
    }


    public int superPow2(int a, int[] b) {
        if (b == null || b.length == 0) {
            return -1;
        }
        return dfs2(a, b, b.length - 1, 1337);
    }

    // a ^ k = a ^{(k/10)*10 + k%10} = (a^{k/10})^{10} * a^{k%10}
    // k%10 -->最后一位
    // 当前处理到 index 位置
    private int dfs2(int a, int[] b, int index, int MOD) {
        if (index == -1) {
            return 1;
        }
        return (myPow2(a, b[index], MOD) * myPow2(dfs2(a, b, --index, MOD), 10, MOD)) % MOD;
    }

    // 二进制处理幂级展开
    public int myPow2(int a, int n, int MOD) {
        int ans = 1;
        int t = a;
        while (n != 0) {
            if ((n & 1) != 0) {
                ans = ((ans % MOD) * (t % MOD)) % MOD;
            }
            t = ((t % MOD) * (t % MOD)) % MOD;
            n >>= 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int a = 1931497;
        int[] b = new int[]{9};
        var res = new Problem_372_SuperPow().superPow(a, b);
        System.out.println(res);
    }
}
