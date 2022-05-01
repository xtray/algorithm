package _LintCode;

public class Problem_213_ZipWords {

    public String compress(String s) {
        if (s == null || s.length() <= 2) {
            return s;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        StringBuilder sb = new StringBuilder();
        int cnt = 1; // 第0个字符单独
        for (int i = 1; i < N; i++) {
            if (str[i] == str[i - 1]) {
                cnt++;
            } else { // 不相等开始结算前面的
                sb.append(str[i - 1]);
                sb.append(cnt);
                cnt = 1;
            }
        }
        // 不要忘了结算最后一部分
        sb.append(str[N - 1]);
        sb.append(cnt);
        return sb.length() >= N ? s : sb.toString();
    }

    public static void main(String[] args) {
        String str = "abccccc";
        var ans = new Problem_213_ZipWords().compress(str);
        System.out.println(ans);
    }
}
