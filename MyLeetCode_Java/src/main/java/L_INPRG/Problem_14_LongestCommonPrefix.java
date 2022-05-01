package L_INPRG;

public class Problem_14_LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return null;
        }
        char[] cstr = strs[0].toCharArray();
        int commonLen = cstr.length;
        for (String word : strs) {
            char[] str = word.toCharArray();
            int idx = 0;
            int len = Math.min(commonLen, str.length);
            commonLen = len;
            while (idx < len && cstr[idx] == str[idx]) {
                idx++;
            }
            if (idx != len) {
                commonLen = idx;
            }
        }
        return commonLen == 0 ? "" : String.valueOf(cstr, 0, commonLen);
    }

    public static void main(String[] args) {
        // String[] strs = {"flower","flow","flight"};
        // String[] strs = {"dog","racecar","car"};
        String[] strs = {"ab", "a"};
        var ans = new Problem_14_LongestCommonPrefix().longestCommonPrefix(strs);
        System.out.println(ans);
    }
}
