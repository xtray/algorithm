package LeetCode.JZOffer;

public class Problem_JZ46_translateNum {

    public int translateNum(int num) {
        if (num < 0) {
            return 0;
        }
        int len = getNumLen(num);
        return process(num, len, 0);
    }

    // num 从 i位置开始转换, 返回方法数
    private int process(int num, int len, int i) {
        if (i == len) {
            return 1;
        }
        // i没有到最后
        // 可能性1: 当前位置单独转换
        int cur = getIndexNum(num, len, i);
        int ways = process(num, len, i + 1);
        // 可能性2: i, i+1一起转换
        if (i + 1 < len &&
                (cur * 10 + getIndexNum(num, len, i + 1) <= 25) &&
                (cur * 10 + getIndexNum(num, len, i + 1) >= 10)) {
            ways += process(num, len, i + 2);
        }
        return ways;
    }

    // 得到数字i位置的值
    private int getIndexNum(int num, int len, int i) {
        return (num / (int) Math.pow(10, len - 1 - i)) % 10;
    }

    private int getNumLen(int num) {
        int len = 0;
        while (num > 0) {
            len++;
            num /= 10;
        }
        return len;
    }

    public int translateNum2(int num) {
        if (num < 0) {
            return 0;
        }
        char[] str = String.valueOf(num).toCharArray();
        int N = str.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            dp[i] = dp[i + 1];
            int val = (i + 1 < N) ? (str[i] - '0') * 10 + str[i + 1] - '0' : 0;
            if (val >= 10 && val <= 25) {
                dp[i] += dp[i + 2];
            }
        }
        return dp[0];
    }

    // TODO: 有bug, 需要修改
    public int translateNum3(int num) {
        if (num < 0) {
            return 0;
        }
        char[] str = String.valueOf(num).toCharArray();
        int N = str.length;
        int a = 1;
        int b = 1;
        for (int i = N - 1; i >= 0; i--) {
            int cur = a;
            int val = (i + 1 < N) ? (str[i] - '0') * 10 + str[i + 1] - '0' : 0;
            if (val >= 10 && val <= 25) {
                cur += b;
            }
            a = cur;
            b = a;
        }
        return a;
    }

    public static void main(String[] args) {
        int num = 12258;
        // int num = 506;
        var ans = new Problem_JZ46_translateNum().translateNum(num);
        System.out.println(ans);
        var ans2 = new Problem_JZ46_translateNum().translateNum2(num);
        System.out.println(ans2);
        var ans3 = new Problem_JZ46_translateNum().translateNum3(num);
        System.out.println(ans3);
    }
}
