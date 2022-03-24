package LeetCode;

public class Problem_344_ReverseString {
    public void reverseString(char[] s) {
        if (s == null || s.length == 0) {
            return;
        }
        int L = 0;
        int R = s.length - 1;
        while (L < R) {
            char tmp = s[L];
            s[L++] = s[R];
            s[R--] = tmp;
        }
    }
}
