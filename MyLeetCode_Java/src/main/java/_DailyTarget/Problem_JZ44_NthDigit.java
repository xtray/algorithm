package _DailyTarget;

public class Problem_JZ44_NthDigit {

    private static final long[] count = {
            0,
            9,
            90 * 2,
            900 * 3,
            9000 * 4,
            90000 * 5,
            900000 * 6,
            9000000 * 7,
            90000000 * 8,
            900000000 * 9L
    };
    private static final long[] sum = new long[10];

    static {
        for (int i = 1; i < 10; i++) {
            sum[i] = sum[i - 1] + count[i];
        }
    }

    // 二分: 第N位出现位置一定小于数字N, 在1~N之间二分
    public int findNthDigit(int n) {
        if (n >= 1 && n <= 9) {
            return n;
        }
        int L = 1;
        int R = n;
        int num = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            long total = getDigitCnt(mid);
            if ( total >= n) {
                num = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        // 第n的位在num里
        long cnt = getDigitCnt(num - 1);
        return getKdigit(num, n - cnt);
    }

    // 数字中第k位的数
    private int getKdigit(int num, long k) {
        int len = getLen(num);
        int mask = (int) Math.pow(10, len - k);
        return (num / mask) % 10;
    }

    // 1~num有多少位
    private static long getDigitCnt(int num) {
        int len = getLen(num);
        int mask = (int) Math.pow(10, len - 1);
        int first = num / mask;
        // 如果第一位 == 1
        long firstPart = 0;
        if (first == 1) {
            firstPart = (long) (num % mask + 1) * len;
        } else {
            firstPart = (long) (first - 1) * mask * len + (long) (num % mask + 1) * len;
        }
        long secondPart = sum[len - 1];
        return firstPart + secondPart;
    }

    private static int getLen(int num) {
        int cnt = 0;
        while (num > 0) {
            cnt++;
            num /= 10;
        }
        return cnt;
    }

    ///

    // len长度的数字一共的个数
    long getCnt(int len) {
        // return (long)(Math.pow(10, len) - Math.pow(10, len - 1)) * len;
        return (long)(len * 9 * Math.pow(10, len - 1));
    }

    public int findNthDigit2(int n) {
        int len = 1;
        // while (getCnt(len) < n) {
        while (len * 9 * Math.pow(10, len - 1) < n) { // <n就不会越界溢出
            n -= len * 9 * Math.pow(10, len - 1);
            len++;
        }
        // len++跳出循环, 此时这个len可能会越界了, 用long
        long start = (long) Math.pow(10, len - 1); // len长度开始的第一个数
        long x = start + n / len - 1 ; // 向下取整到的数
        n -= (x - start + 1) * len; // 剩下的数, 也就是 模数
        // 不能整除, 就到x+1这个数找从左往右第k位, 左边n个, 右边 len - n
        //    3 2 7 6 1, 找从左往右第2个, 长度len =5
        //      ^        len - 2 + 1是右边第4个, 但是下标是 len - n, 对齐的mask 是 10^(len-n)
        return n == 0 ? (int) (x % 10) : (int) ((x + 1) / Math.pow(10, len - n) % 10);
    }


    public static void main(String[] args) {
        // int n = 19; // 4
        int n = (Integer.MAX_VALUE>>1) +114748364 ; //2
        var ans = new Problem_JZ44_NthDigit().findNthDigit(n);
        System.out.println(ans);

        var ans2 = new Problem_JZ44_NthDigit().findNthDigit2(n);
        System.out.println(ans2);
    }
}
