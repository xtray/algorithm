package LeetCode;

public class Problem_1332_RemovePalindromeSub {

    public int removePalindromeSub(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else {
                return 2;
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        String s = "abaaba";
        var ans = new Problem_1332_RemovePalindromeSub().removePalindromeSub(s);
        System.out.println(ans);
    }
}
