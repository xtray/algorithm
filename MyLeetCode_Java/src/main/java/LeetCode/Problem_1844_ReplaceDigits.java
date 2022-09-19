package LeetCode;

public class Problem_1844_ReplaceDigits {

    public String replaceDigits(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        for (int i = 1; i < N; i += 2) {
            str[i] = (char) (str[i - 1] + (str[i] - '0'));
        }
        return String.valueOf(str);
    }
}
