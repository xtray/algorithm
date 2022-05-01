package LeetCode;

public class Problem_806_NumberOfLines {

    public int[] numberOfLines(int[] widths, String s) {
        if (s == null || s.length() == 0) {
            return new int[0];
        }
        char[] str = s.toCharArray();
        int lines = 0;
        int limit = 100;
        int curLen = 0;
        for (char ch : str) {
            int len = widths[ch - 'a'];
            if (curLen + len < limit) {
                curLen += len;
            } else if (curLen + len == limit) {
                lines++;
                curLen = 0;
            } else {  // > 100
                lines++;
                curLen = len;
            }
        }
        if (curLen > 0) {
            lines++;
        }
        return new int[]{lines, curLen == 0 ? 100 : curLen};
    }

    public static void main(String[] args) {
        int[] widths = {3, 4, 10, 4, 8, 7, 3, 3, 4, 9, 8, 2, 9, 6, 2, 8, 4, 9, 9, 10, 2, 4, 9, 10, 8, 2};
        String s = "mqblbtpvicqhbrejb";
        var ans = new Problem_806_NumberOfLines().numberOfLines(widths, s);
        System.out.println(ans[0] + ", " + ans[1]);
    }
}
