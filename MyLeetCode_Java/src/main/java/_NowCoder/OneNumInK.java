package _NowCoder;

// https://www.nowcoder.com/practice/304aafab7eb04856bb1d91f95f215062

public class OneNumInK {

    // F(m,k)≥n，最小的m是多少
    public long minM(int n, int k) {
        // 要求最少 n 个 1, 以十进制为例子
        // 如果 n = 100, 那么 m 不会大于 1000
        // 在 1~1000 内二分即可
        // 即, 题目要求 n 个 1, n 位数是比如 xxx, 则最大求到 1000 即可
        int len = getLenOfNum(n, k);
        long l = 1;
        long r = powerBaseOfK(k, len + 1);
        // 二分
        long res = -1;
        while (l <= r) {
            long mid = l + ((r - l) >> 1);
            long count = countOneNumInK(mid, k);
            if (count >= n) {
                res = mid;
                r = mid - 1;
            } else if (count < n) {
                l = mid + 1;
            }
        }
        return res;
    }

    // 求 k 进制下, 1~N 有几个 1
    public long countOneNumInK(long num, int k) {
        if (num < 1) {
            return 0;
        }
        // 得到 num 的位数, 10 进制为例子:
        // num -> 13625
        // len = 5位数
        int len = getLenOfNum(num, k);
        if (len == 1) {
            return 1;
        }
        // 跟 num 对齐的最高位为 1 的数, 10 进制为例子:
        // 3421 --> 1000
        long mask = powerBaseOfK(k, len - 1);
        // 分情况计算, 10 进制为例子:
        // X345 为例子, 计算 346~X345 区间 1 的个数
        // 看 num 最高位是不是 1
        int firstDigit = (int) (num / mask);
        // 最高位的数贡献的 1 的个数
        // 最高位是 1: num%tmp1 + 1个
        // 最高位不是 1: tmp1 个
        long firstOneNum = firstDigit == 1 ? num % mask + 1 : mask;
        // 除去最高位之外，剩下1的数量
        // 最高位1 K^(len-2次方) * (len-1) * 1
        // 最高位是firstDigit K^(len-2次方) * (len-1) * first
        // 1364: 365~1364: 10进制, (k-1) * 10^{k-2}
        long otherOneNum = firstDigit * (len - 1) * (mask / k);
        // 10 进制为例子, X345 为例子, 剥掉最高位的 X,
        // 递归计算剩下的 1~345
        return firstOneNum + otherOneNum + countOneNumInK(num % mask, k);
    }

    // 按power二进制算
    private long powerBaseOfK(long base, int power) {
        long ans = 1;
        while (power != 0) {
            if ((power & 1) != 0) {
                ans *= base;
            }
            base *= base;
            power >>= 1;
        }
        return ans;
    }
    // 得到 K 进制下 num 的位数
    private int getLenOfNum(long num, int k) {
        int len = 0;
        while (num != 0) {
            len++;
            num /= k;
        }
        return len;
    }

    public static void main(String[] args) {
        int n = 93339400;
        int k = 48356; // 2431495139
        var ans = new OneNumInK().minM(n, k);
        System.out.println(ans);
    }
}
