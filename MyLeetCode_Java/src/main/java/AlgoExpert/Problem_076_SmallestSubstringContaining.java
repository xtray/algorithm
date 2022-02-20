package AlgoExpert;

public class Problem_076_SmallestSubstringContaining {

    // 76. 最小覆盖子串
    // https://leetcode-cn.com/problems/minimum-window-substring/
    public static String smallestSubstringContaining(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() < s2.length()) {
            return "";
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] map = new int[256]; // map[37] = 4 37 4次
        for (int i = 0; i != str2.length; i++) {
            map[str2[i]]++;
        }
        int all = str2.length;

        // [L,R-1] R
        // [L,R) -> [0,0)
        int L = 0;
        int R = 0;
        int lpos = -1;
        int rpos = -1;
        int minLen = Integer.MAX_VALUE;
        while (R != str1.length) {
            map[str1[R]]--;
            if (map[str1[R]] >= 0) {
                all--;
            }
            if (all == 0) { // 还完了
                while (map[str1[L]] < 0) {
                    map[str1[L++]]++;
                }
                // [L..R]
                // minLen = Math.min(minLen, R - L + 1);
                if (R - L + 1 < minLen) {
                    minLen = R - L + 1;
                    lpos = L;
                    rpos = R;
                }
                all++;
                map[str1[L++]]++;
            }
            R++;
        }
        return minLen == Integer.MAX_VALUE ? "" : s1.substring(lpos, rpos+1);
    }

    public static void main(String[] args) {
        String bigString = "abcd$ef$axb$c$";
        String smallString = "$$abf";
        var ans = smallestSubstringContaining(bigString, smallString);
        System.out.println(ans);
    }
}
