package LeetCode;

public class Problem_409_LongestPalindrome {

    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int[] cnt = new int[256];
        for (char ch : str) {
            cnt[ch]++;
        }
        int odd = 0; // 奇数有没有出现, 出现计1, 作为中心
        int even = 0; // 偶数的最大对数
        for (int cn : cnt) {
            if (cn == 0) continue;
            if ((cn & 1) != 0) { // 奇数
                odd = 1; // 只要有一个奇数次, 就计1
            }
            even += cn / 2; // 偶数的最大对数, 对所有>=2的数向下取整
        }
        return even * 2 + odd;
    }


    public static void main(String[] args) {
        String s = "abcccdd";
        var ans = new Problem_409_LongestPalindrome().longestPalindrome(s);
        System.out.println(ans);
    }
}
