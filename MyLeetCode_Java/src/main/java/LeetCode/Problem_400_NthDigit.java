package LeetCode;


import java.util.HashMap;
import java.util.Map;

public class Problem_400_NthDigit {


    // 长度为len的数字范围是 10^{len-1} ~ 10^len - 1, 一共有 9*10^len - 1个数, 总长度为
    // len * (9*10^len - 1)
    public int findNthDigit(int n) {
        int len = 1;
        // 1. 找到第N位数应该在哪个长度上
        while (len * 9 * Math.pow(10, len - 1) < n) { // <n就不会越界溢出
            n -= len * 9 * Math.pow(10, len - 1);
            len++;
        }
        // len++跳出循环, 此时这个len可能会越界了, 用long
        long start = (long) Math.pow(10, len - 1); // len长度开始的第一个数
        // 2. 直接定位到第N位这个数在哪个数(x)上
        long x = start + n / len - 1; // n / len 一共几个数, 从start开始, 一共 x - start + 1个数
        n -= (x - start + 1) * len; // 剩下的数, 也就是 模数
        // 能整除, 说明是x的最后一位, 不能整除即n>0, 即在x+1这个数上
        // 不能整除, 就到x+1这个数找从左往右第k位, 左边n个, 右边 len - n
        //    3 2 7 6 1, 找从左往右第2个, 长度len =5
        //      ^        len - 2 + 1是右边第4个, 但是下标是 len - n, 对齐的mask 是 10^(len-n)
        return n == 0 ? (int) (x % 10) : (int) ((x + 1) / Math.pow(10, len - n) % 10);
    }

    // 二分数的位数, 最少1位数, 最大9位数, 如果没找到, 说明当前数在9位上
    public int findNthDigit1(int n) {
        int low = 1, high = 9;
        int bit = -1; //
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            // 找到位数>=n最左的
            if (getTotalDigitCnt(mid) >= n) {
                bit = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        int d = bit == -1 ? 9 : bit; // 如果没找到>=n的位数, 说明n很大, 在最高位长度, 取d == 9
        int prevDigits = getTotalDigitCnt(d - 1);
        int index = n - prevDigits - 1;
        int start = (int) Math.pow(10, d - 1);
        int num = start + index / d;
        int digitIndex = index % d;
        int digit = (num / (int) (Math.pow(10, d - digitIndex - 1))) % 10;
        return digit;
    }

    private int getTotalDigitCnt(int len) {
        int ans = 0;
        while (len >= 1) {
            ans += (int) (len * 9 * Math.pow(10, len - 1));
            len--;
        }
        return ans;
    }


    public static void main(String[] args) {
        // int n = 11;
        // int n = 1004;
        // int n = 19; // 4
        // int n = (Integer.MAX_VALUE >> 1) + 114748364; //2
        int n = 1000000000; //1

        var ans = new Problem_400_NthDigit().findNthDigit(n);
        System.out.println(ans);

        var ans1 = new Problem_400_NthDigit().findNthDigit1(n);
        System.out.println(ans1);

    }
}
