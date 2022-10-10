package _DailyTarget;


public class Problem_788_RotateDIgits {

    // 数位DP
    // 0, 1, 8
    // 2 - 5
    // 6 - 9
    // 2,5,6,9
    // 两位: 第一个: 1,2,5,6,9
    //      第二个:
    // 好数中不能有 3,4,7，且至少包含 2,5,6,9 中的一个。
    public int rotatedDigits0(int n) {

        int len = getDigitLen(n);
        int mask = (int) (Math.pow(10, len - 1));
        int firstDigit = n / mask;
        if (firstDigit == 1) {
            // 1. 两部分 1000~1XXX
            // 2.        0~999
        }

        return 0;

    }

    private int getDigitLen(int n) {
        int cnt = 0;
        while (n != 0) {
            cnt++;
            n /= 10;
        }
        return cnt;
    }

    // 枚举每一个数
    public int rotatedDigits(int n) {
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (check(i)) {
                ans++;
            }
        }
        return ans;
    }

    private boolean check(int num) {
        // 用0,1,-1区分三种状态
        int[] valid = new int[]{0, 0, 1, -1, -1, 1, 1, -1, 0, 1};
        char[] str = String.valueOf(num).toCharArray();
        boolean ok = false;
        for (char ch : str) {
            if (valid[ch - '0'] == -1) {
                return false;
            } else if (valid[ch - '0'] == 1) {
                ok = true;
            }
        }
        return ok;
    }


}
