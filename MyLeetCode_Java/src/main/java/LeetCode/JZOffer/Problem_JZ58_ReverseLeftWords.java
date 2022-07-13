package LeetCode.JZOffer;

public class Problem_JZ58_ReverseLeftWords {

    public String reverseLeftWords(String s, int n) {
        if (s == null || s.length() == 0 || n <= 0 || n >= s.length()) {
            return s;
        }
        char[] str = s.toCharArray();
        process(str, 0, n - 1);
        process(str, n, str.length - 1);
        process(str, 0, str.length - 1);
        return String.valueOf(str);
    }

    private void process(char[] str, int L, int R) {
        while (L < R) {
            char tmp = str[L];
            str[L] = str[R];
            str[R] = tmp;
            L++;
            R--;
        }
    }
}
