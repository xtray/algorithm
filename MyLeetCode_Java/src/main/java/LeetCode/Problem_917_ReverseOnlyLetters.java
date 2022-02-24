package LeetCode;

public class Problem_917_ReverseOnlyLetters {

    public String reverseOnlyLetters(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int L = 0;
        int R = N - 1;
        while (L < R) {
            while (L < N && !Character.isLetter(str[L])) {
                L++;
            }
            while (R >= 0 && !Character.isLetter(str[R])) {
                R--;
            }
            if (L >= R) break;
            char tmp = str[L];
            str[L] = str[R];
            str[R] = tmp;
            L++;
            R--;
        }
        return String.valueOf(str);
    }

    public String reverseOnlyLetters2(String s) {
        char[] str = s.toCharArray();
        int N = str.length;
        for (int i = 0, j = N - 1; i < j; ) {
            while (i <= j && !Character.isLetter(str[i])) i++;
            while (i <= j && !Character.isLetter(str[j])) j--;
            if (i < j) {
                char c = str[i];
                str[i++] = str[j];
                str[j--] = c;
            }
        }
        return String.valueOf(str);
    }


    public static void main(String[] args) {
        // String s = "Test1ng-Leet=code-Q!";
        String s = "7_28]";
        var ans = new Problem_917_ReverseOnlyLetters().reverseOnlyLetters(s);
        System.out.println(ans);
    }
}
