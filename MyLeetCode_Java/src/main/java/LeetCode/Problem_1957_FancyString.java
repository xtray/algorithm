package LeetCode;

public class Problem_1957_FancyString {

    public String makeFancyString(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int cnt = 1;
        StringBuilder sb = new StringBuilder();
        sb.append(str[0]);
        for (int i = 1; i < N; i++) {
            if (str[i] == str[i - 1]) {
                cnt++;
                if (cnt == 3) {
                    continue;
                }
            } else {
                cnt = 1;
            }
            sb.append(str[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "aaabaaaa";
        var ans = new Problem_1957_FancyString().makeFancyString(s);
        System.out.println(ans);
    }
}
