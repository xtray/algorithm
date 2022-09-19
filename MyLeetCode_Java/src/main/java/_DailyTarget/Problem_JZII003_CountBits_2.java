package _DailyTarget;

public class Problem_JZII003_CountBits_2 {

    public int[] countBits(int n) {
        if (n <= 0) {
            return new int[]{0};
        }
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ans[i] = getCnt(i);
        }
        return ans;
    }

    private int getCnt(int num) {
        int cnt = 0;
        while (num != 0) {
            cnt++;
            num -= num & (-num); // 减掉最右侧的1
        }
        return cnt;
    }

    private int getCnt2(int num) {
        int cnt = 0;
        while (num != 0) { // NOTE: 如果有负数, 要小心
            if ((num & 1) != 0) {
                cnt++;
            }
            num >>>= 1; // NOTE: 如果有负数, 要小心
        }
        return cnt;
    }

    // IMP: 动态规划的解法, 每次减掉最右侧的1
    public int[] countBits1(int n) {
        if (n <= 0) {
            return new int[]{0};
        }
        int[] dp = new int[n + 1]; // 0~n
        for (int i = 1; i <= n; i++) {
            dp[i] = 1 + dp[i - (i & (-i))];
        }
        return dp;
    }
}
