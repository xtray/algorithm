package LeetCode;

public class Problem_76_MinWindow {

    public String minWindow(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0 || t.length() > s.length()) {
            return "";
        }
        int[] map = new int[128]; // 欠账表
        int all = t.length();
        for (char ch : t.toCharArray()) map[ch]++;
        char[] str = s.toCharArray();
        int N = str.length;
        int L = 0;
        int R = 0;
        int ansL = -1;
        int ansR = -1;
        int minLen = Integer.MAX_VALUE;
        // 目标里没有的字符--后会变成负数, 再移出窗口L++时最多恢复到0
        while (R < N) {
            map[str[R]]--;
            if (map[str[R]] >= 0) { // --之后>=0 是有效还款
                all--;
            }
            if (all == 0) { // 找到一个合法区间, 缩窗口
                while (map[str[L]] < 0) {
                    map[str[L++]]++;
                }
                // 此时 map[str[L]] = 0
                int len = R - L + 1;
                if (len < minLen) {
                    minLen = len;
                    ansL = L;
                    ansR = R;
                }
                // map[str[L]] = 0, 移出L, 重新欠账
                all++;
                map[str[L++]]++;
            }
            R++;
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(ansL, ansR + 1);
    }
}
