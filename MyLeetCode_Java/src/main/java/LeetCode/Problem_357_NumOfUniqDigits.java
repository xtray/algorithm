package LeetCode;

public class Problem_357_NumOfUniqDigits {

    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        int ans = 10; // 一位数的情况
        int lastChoice = 9; // 上次可以选择的数字情况数
        for (int i = 2; i <= n; i++) {
            // 一共10个数可选, 当前处理的第i个, 已经用的是i-1个, 还剩的就是 10 - i + 1
            int cur = lastChoice * (10 - i + 1);
            ans += cur; // 加上当前i位数的情况
            lastChoice = cur;
        }
        return ans;
    }

    // 0~10^n
    public static int countNumbersWithUniqueDigits1(int n) {
        if (n == 0) {
            return 1;
        }
        // len 最大也就是n长度
        int ans = 1;
        for (int i = 1; i <= n; i++) {
            ans += getFixLenNumber(i);
        }
        return ans;
    }

    // 10^i
    private static int getFixLenNumber(int i) {
        return process(0, i, 0);
    }

    // 当前处理到的长度
    // 总长度: len
    private static int process(int curLen, int len, int mask) {
        if (curLen == len) {
            return 1;
        }
        int cnt = 0;

        for (int i = (curLen == 0 ? 1 : 0); i <= 9; i++) {
            if (((1 << i) & mask) == 0) {
                cnt += process(curLen + 1, len, mask | (1 << i));
            }
        }
        return cnt;
    }
}
