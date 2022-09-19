package _LintCode;

public class Problem_927_ReverseWordsII {

    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        String[] ss = s.split(" ");
        int N = ss.length;
        StringBuilder sb = new StringBuilder();
        for (int i = N - 1; i >= 0; i--) {
            sb.append(ss[i]).append(" ");
        }
        return sb.delete(sb.length() - 1, sb.length()).toString();
    }
}
