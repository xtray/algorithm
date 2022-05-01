package LeetCode;

public class Problem_357_NumOfUniqDigits {
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        int ans = 10; // 一位数的情况
        int lastChoice = 9; // 上次可以选择的数字情况数
        for (int i = 2; i <= n; i++) {
            int cur = lastChoice * (10 - i + 1); // 举例子
            ans += cur; // 加上当前i位数的情况
            lastChoice = cur;
        }
        return ans;
    }
}
