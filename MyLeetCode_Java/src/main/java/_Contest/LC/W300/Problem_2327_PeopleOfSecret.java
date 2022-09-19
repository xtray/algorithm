package _Contest.LC.W300;

// week 300

// IMP: 注意跟斐波那契数列的区别, 本题不是

public class Problem_2327_PeopleOfSecret {


    public static int peopleAwareOfSecret(int n, int delay, int forget) {
        long mod = (long) (1e9 + 7);
        // dpKnow[i], 第i天知道秘密的人
        long[] dpKnow = new long[n + 1];
        // dpForget[i], 第i天将要忘记秘密的人
        long[] dpForget = new long[n + 1];
        // dpShare[i], 第i天可以分享秘密的人
        long[] dpShare = new long[n + 1];
        dpKnow[1] = 1;
        if (1 + forget <= n) {
            dpForget[1 + forget] = 1;
        }
        if (1 + delay <= n) {
            dpShare[1 + delay] = 1;
        }
        for (int i = 2; i <= n; i++) {
            dpKnow[i] = (mod + dpKnow[i - 1] - dpForget[i] + dpShare[i]) % mod;
            if (i + forget <= n) {
                dpForget[i + forget] = dpShare[i];
            }
            if (i + delay <= n) {
                dpShare[i + delay] = (mod + dpShare[i + delay - 1] - dpForget[i + delay] + dpShare[i] ) % mod;
            }
        }
        return (int) dpKnow[n];
    }
}
