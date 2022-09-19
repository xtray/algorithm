package LeetCode;

public class Problem_266_CanPanlindrome {

    // 字符个数是奇数的种数<=1
    public boolean canPermutePalindrome(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        char[] str = s.toCharArray();
        int N = str.length;
        char[] cnt = new char[256];
        int oddCnt = 0;
        for (char ch : str) {
            cnt[ch]++;
        }
        for (char ch : cnt) {
            if (((ch - '0') & 1) != 0) {
                oddCnt++;
            }
        }
        return oddCnt <= 1;
    }
}
