package LeetCode.BINARY.EASY;

/**
 * Not Pass!
 */
public class Problem_409_LongestPalindrome_F {

    public int longestPalindrome(String s) {
        if(s == null || s.length() ==0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int[] cnt = new int[256];
        for(char ch : str) {
            cnt[ch]++;
        }
        int one = 0;
        int two = 0;
        for(int cn : cnt) {
            if (cn == 0) continue;
            if ((cn & 1) != 0) { // 奇数
                one = 1;
            }
            two += cn / 2;
        }
        return two*2 + one;
    }


    public static void main(String[] args) {
        String s= "abccccdd";
        var ans = new Problem_409_LongestPalindrome_F().longestPalindrome(s);
        System.out.println(ans);
    }
}
