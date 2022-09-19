package _aTemplate;

public class Log2 {

    private static final int MAX_SIZE = (int) 1e9;
    private static final int[] Log2 = new int[MAX_SIZE + 1];

    // log1 = 0, log2 =1
    // log2^i = log2^i-1 + 1
    static {
        for (int i = 2; i <= MAX_SIZE; ++i) {
            Log2[i] = Log2[i >> 1] + 1;
        }
    }

    public static int log2N(int n) {
        int res = -1;
        while (n != 0) {
            res++;
            n >>>= 1;
        }
        return res;
    }

    // 离n最近的2的某次幂
    private static int power2(int n) {
        int ans = 0;
        // m >> 1 : m先减半为了退出循环时 ans 不用--
        while ((1 << ans) <= (n >> 1)) {
            ans++;
        }
        return ans;
    }

    private static int power20(int n) {
        int ans = 0;
        // m >> 1 : m先减半为了退出循环时 ans 不用--
        while ((1 << ans) <= n) {
            ans++;
        }
        return --ans;
    }

    // 已知n是正数
    // 返回>=num离num最近的2的数值
    public static int log2Upper(int n) {
        n--; // 如果只有1个1的情况, 先把最高位的1卸下来
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16; // 从最高位的1的位置到0位置全部填充为1
        // 如果全是1, 即-1 离的最近是2^0 = 1
        // n+1: 如果n正好是2的整数次幂, +1 还回去
        //      如果不是比如110, 移动后变为111, 那么>= num 的就需要+1
        return (n < 0) ? 1 : n + 1;
    }

    public static void main(String[] args) {
        // for (int i = 1; i <= 64; i++) {
        //     System.out.printf("num: %d, log2: %d. \n", i, Log2[i]);
        // }
        System.out.printf("num: %d, log2: %d. \n", 1024, Log2[1024]);
        System.out.printf("num: %d, log2: %d. \n", MAX_SIZE, Log2[MAX_SIZE]);

        // 快速的到一个数最高位上的1所在的位置
        int num = 1700; // 2^10 = 1024
        System.out.println((int) (Math.log10(num) / Math.log10(2)));
        System.out.println(Log2[num]);
        System.out.println(log2N(num));
        System.out.println(power2(num));
        System.out.println(power20(num));
        System.out.println(log2Upper(num));
    }
}
