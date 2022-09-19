package LeetCode;

import java.util.Arrays;

public class Problem_2376_CountSpecialNumber {

    public static int countSpecialNumbers(int n) {
        char[] str = String.valueOf(n).toCharArray();
        int N = str.length;
        int[][] dp = new int[N][1 << 10];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
        return process(str, 0, 0, true, false, dp);
    }

    private static int process(
            char[] str,
            int i, // 当前处理到的位置
            int mask, // 已经用过的数, 位信息表示
            boolean isLimit, // 是否跟限制齐平
            boolean isNum, // 前面填过数没有
            int[][] dp) {
        if (i == str.length) {
            return isNum ? 1 : 0;
        }
        if (!isLimit && isNum && dp[i][mask] != -1) {
            return dp[i][mask];
        }
        int res = 0;
        // 可以跳过当前数位
        if (!isNum) {
            res = process(str, i + 1, mask, false, false, dp);
        }
        // 枚举要填入的数字 d
        for (int d = isNum ? 0 : 1, up = isLimit ? str[i] - '0' : 9; d <= up; ++d)
            if ((mask >> d & 1) == 0) { // d 不在 mask 中
                res += process(
                        str,
                        i + 1,
                        mask | (1 << d),
                        isLimit && d == up,
                        true,
                        dp);
            }
        if (!isLimit && isNum) {
            dp[i][mask] = res;
        }
        return res;
    }


    // 0~10^n
    public static int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        // len 最大也就是n长度
        int limit = (int) (Math.pow(10, n)) - 1;
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

    public static void main(String[] args) {
        int n = 2;
        var ans = countSpecialNumbers(n);
        System.out.println(ans);

    }
}
