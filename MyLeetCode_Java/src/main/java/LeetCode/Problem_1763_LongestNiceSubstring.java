package LeetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Problem_1763_LongestNiceSubstring {

    public String longestNiceSubstring(String s) {
        if (s == null || s.length() <= 1) {
            return "";
        }
        String ans = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) { // 枚举所有子串
                if (j - i + 1 > ans.length() && check(s.substring(i, j + 1))) {
                    ans = s.substring(i, j + 1);
                }
            }
        }
        return ans;
    }

    // 检查子串是否符合要求
    private boolean check(String s) {
        int low = 0;
        int high = 0;
        char[] str = s.toCharArray();
        int N = str.length;
        for (int i = 0; i < N; i++) {
            if (Character.isLowerCase(str[i])) {
                low |= 1 << (str[i] - 'a');
            } else {
                high |= 1 << (str[i] - 'A');
            }
        }
        return low == high;
    }


    public String longestNiceSubstring1(String s) {
        int n = s.length();
        int[][] cnt = new int[n + 1][128];
        // 因为要前后相减, 所以从 1 开始
        for (int i = 1; i <= n; i++) {
            char c = s.charAt(i - 1);
            cnt[i] = cnt[i - 1].clone();
            cnt[i][c - 'A']++;
        }
        int idx = -1, len = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (j - i + 1 <= len) continue;
                int[] a = cnt[i], b = cnt[j + 1];
                if (check(a, b)) {
                    idx = i;
                    len = j - i + 1;
                }
            }
        }
        return idx == -1 ? "" : s.substring(idx, idx + len);
    }

    // 检查 26 个字母对应的大小写词频（ASCII 相差 32），是否同时为 0 或者同时不为 0 即可
    // 即: 同时不出现, 或者同时出现
    boolean check(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) {
            int low = b[i] - a[i], up = b[i + 32] - a[i + 32]; // 'A' = 65、'a' = 97
            if (low != 0 && up == 0) return false;
            if (low == 0 && up != 0) return false;
        }
        return true;
    }


    public static void main(String[] args) {
        String s = "YazaAay";
        var ans = new Problem_1763_LongestNiceSubstring().longestNiceSubstring(s);
        System.out.println(ans);
    }
}
