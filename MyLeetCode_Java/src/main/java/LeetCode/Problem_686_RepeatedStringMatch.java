package LeetCode;

public class Problem_686_RepeatedStringMatch {

    // 如果n个a拼接起来的字符串s可以包含b，那么b在s中出现的位置一定不会超过a的范围。
    // 所以我们只需要拼接到a的大小刚刚超过b的大小+1得到s，就可以保证b一定出现在s中。
    // https://leetcode-cn.com/problems/repeated-string-match/solution/wei-rao-li-lun-qiu-zui-shao-duo-shao-ge-jaj90/
    public int repeatedStringMatch0(String a, String b) {
        StringBuilder sb = new StringBuilder();
        while (sb.length()<=b.length()){
            sb.append(a);
        }
        sb.append(a);
        int idx = sb.indexOf(b);
        return idx == -1? -1: (idx + b.length() + a.length() -1)/a.length(); // 向上取整
    }

    public int repeatedStringMatch(String a, String b) {
        StringBuilder sb = new StringBuilder();
        while (sb.length()<=b.length()){
            sb.append(a);
        }
        sb.append(a);
        int idx = getIndexOf(sb.toString(), b);
        return idx == -1? -1: (idx + b.length() + a.length() -1)/a.length(); // 向上取整
    }

    // KMP
    public static int getIndexOf(String s1, String s2) {
        if (s1 == null || s2 == null || s2.length() < 1 || s1.length() < s2.length()) {
            return -1;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int x = 0;
        int y = 0;
        // O(M) m <= n
        int[] next = getNextArray(str2);
        // O(N)
        while (x < str1.length && y < str2.length) {
            if (str1[x] == str2[y]) {
                x++;
                y++;
            } else if (next[y] == -1) { // y == 0
                x++;
            } else {
                y = next[y];
            }
        }
        return y == str2.length ? x - y : -1;
    }

    public static int[] getNextArray(char[] str2) {
        if (str2.length == 1) {
            return new int[] { -1 };
        }
        int[] next = new int[str2.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2; // 目前在哪个位置上求next数组的值
        int cn = 0; // 当前是哪个位置的值再和i-1位置的字符比较
        while (i < next.length) {
            if (str2[i - 1] == str2[cn]) { // 配成功的时候
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }
}
