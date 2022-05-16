package LeetCode.Contest.DW78;

// https://leetcode.cn/problems/find-the-k-beauty-of-a-number/

public class Problem_5299_KBeautyNumber {

    public int divisorSubstrings(int num, int k) {
        String s = String.valueOf(num);
        char[] str = s.toCharArray();
        int L = 0;
        int R = 0;
        int curVal = 0;
        int ans = 0;
        int N = str.length;
        while (R < N) {
            curVal = curVal * 10 + str[R] - '0';
            if (R - L + 1 >= k) {
                // 结算
                if (curVal != 0 && num % curVal == 0) {
                    ans++;
                }
                curVal = curVal - (int) (Math.pow(10, k - 1)) * (str[L] - '0');
                L++;
            }
            R++;
        }
        return ans;
    }

    public static void main(String[] args) {
        int num = 430043;
        int k = 2;
        var ans = new Problem_5299_KBeautyNumber().divisorSubstrings(num, k);
        System.out.println(ans);
    }
}
