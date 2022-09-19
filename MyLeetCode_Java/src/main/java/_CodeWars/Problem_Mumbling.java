package _CodeWars;

public class Problem_Mumbling {

    public static String accum(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        for (char ch : s.toCharArray()) {
            char upper = ch >= 'A' && ch <= 'Z' ? ch : (char) (ch - 32);
            char lower = (char) (upper + 32);
            sb.append(upper);
            sb.append(String.valueOf(lower).repeat(idx++)).append('-');
        }
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "ZpglnRxqenU";
        var ans = accum(s);
        System.out.println(ans);
    }
}
