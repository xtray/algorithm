package _LintCode;

public class Problem_627_LongestPalindrome {

    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] cnt = new char[256];
        for (char ch : s.toCharArray()) cnt[ch]++;
        int ans = 0;
        boolean odd = false;
        for (int c : cnt) {
            if ((c & 1) != 0) odd = true;
            ans += (c / 2) * 2;
        }
        return odd ? ans + 1 : ans;
    }

}
