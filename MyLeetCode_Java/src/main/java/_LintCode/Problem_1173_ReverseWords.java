package _LintCode;

public class Problem_1173_ReverseWords {

    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        String[] strs = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : strs) {
            char[] str = word.toCharArray();
            int L = 0;
            int R = str.length - 1;
            while (L < R) {
                char tmp = str[L];
                str[L] = str[R];
                str[R] = tmp;
                L++;
                R--;
            }
            sb.append(String.valueOf(str)).append(" ");
        }
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }
}
