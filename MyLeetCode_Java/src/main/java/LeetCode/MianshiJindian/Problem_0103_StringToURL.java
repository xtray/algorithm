package LeetCode.MianshiJindian;

public class Problem_0103_StringToURL {

    public String replaceSpaces(String S, int length) {
        if (S == null || S.length() == 0) {
            return S;
        }
        char[] str = S.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (i < str.length && str[i] != ' ') {
                sb.append(str[i]);
            } else {
                sb.append("%20");
            }
        }
        return sb.toString();
    }

}
