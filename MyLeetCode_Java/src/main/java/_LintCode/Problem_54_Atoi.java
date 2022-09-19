package _LintCode;

public class Problem_54_Atoi {

    public int atoi(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int N = s.length();
        char[] str = s.toCharArray();
        int i = 0;
        while (i < N && str[i] == ' ') {
            i++;
        }
        if (i == N) return 0;
        boolean negative = str[i] == '-';
        i = negative ? i + 1 : (str[i] == '+' ? i + 1 : 0);
        long cur = 0;
        for (; i < N; i++) {
            if (str[i] == ' ') continue;
            if (str[i] >= '0' && str[i] <= '9') {
                if (!negative && (cur * 10 + str[i] - '0') > (long) Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                } else if (negative && (cur * 10 + str[i] - '0') > (long) Integer.MAX_VALUE + 1) {
                    return Integer.MIN_VALUE;
                } else {
                    cur = cur * 10 + str[i] - '0';
                }
            } else {
                break;
            }
        }
        if (negative && cur == (long) Integer.MAX_VALUE + 1) {
            return Integer.MIN_VALUE;
        }
        return (int) (negative ? -cur : cur);
    }

    public int atoi1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        s = s.trim();
        if (s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int i = 0;
        int minus = 1;
        if (str[i] == '+' || str[i] == '-') {
            minus = str[i] == '-' ? -1 : 1;
            i++;
        }
        long cur = 0;
        while (i < N) {
            if (str[i] == ' ') continue;
            if (str[i] < '0' || str[i] > '9') break;
            cur = cur * 10 + str[i] - '0';
            if (cur > Integer.MAX_VALUE) break;
            i++;
        }
        if (cur * minus >= Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (cur * minus <= Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) (cur * minus);
    }

    public static void main(String[] args) {
        String s = " ";
        var ans = new Problem_54_Atoi().atoi(s);
        System.out.println(ans);
    }
}
