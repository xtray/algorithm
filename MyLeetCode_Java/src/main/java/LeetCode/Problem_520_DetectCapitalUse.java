package LeetCode;

public class Problem_520_DetectCapitalUse {

    public boolean detectCapitalUse(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        char[] str = word.toCharArray();
        int N = str.length;
        boolean first = str[0] >= 'a' && str[0] <= 'z';
        int lowerCnt = 0;
        int upperCnt = 0;
        for (int i = 1; i < str.length; i++) {
            if (str[i] >= 'A' && str[i] <= 'Z') {
                upperCnt++;
            } else {
                lowerCnt++;
            }
        }
        // 情况 1:
        if (!first && (lowerCnt + 1 == N || upperCnt + 1 == N)) return true;
        // 情况 2:
        if (first && lowerCnt + 1 == N) return true;
        return false;
    }

    // 无论第一个字母是大写还是小写，其他字母必须与第2个字母的大小写相同。
    // 需要排除第一个字母是小写，其余字母都是大写的特殊情况。
    public boolean detectCapitalUse2(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        char[] str = word.toCharArray();
        int N = str.length;
        if (N >= 2 && isLower(str[0]) && !isLower(str[1])) return false;
        for (int i = 2; i < N; i++) {
            if (isLower(str[1]) != isLower(str[i])) {
                return false;
            }
        }
        return true;
    }

    boolean isLower(char ch) {
        return ch >= 'a' && ch <= 'z';
    }


    public static void main(String[] args) {
        Problem_520_DetectCapitalUse sl = new Problem_520_DetectCapitalUse();
//        String word = "USA";
        String word = "g";
        boolean res = sl.detectCapitalUse2(word);
        System.out.println(res);
    }
}
