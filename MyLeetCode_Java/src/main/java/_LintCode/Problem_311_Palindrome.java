package _LintCode;

public class Problem_311_Palindrome {

    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        char[] str = s.toCharArray();
        int[] cnt = new int[256];
        for (char ch : str) {
            cnt[ch]++;
        }
        int oddCnt = 0;
        for (int num : cnt) {
            if ((num & 1) != 0) {
                oddCnt++;
            }
        }
        // 奇数的元素小于等于1个都可以构成回文
        return oddCnt <= 1;
    }
}
