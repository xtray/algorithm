package _LintCode;

public class Problem_1283_ReverseString {

    public String reverseString(String s) {
        if(s == null || s.length() ==0) {
            return s;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int i = 0;
        int j = N - 1;
        while (i<j) {
            char tmp = str[i];
            str[i++] = str[j];
            str[j--] = tmp;
        }
        return String.valueOf(str);
    }
}
