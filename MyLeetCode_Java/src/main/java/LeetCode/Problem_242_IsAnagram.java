package LeetCode;

public class Problem_242_IsAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        int[] cnt = new int[26];
        for (int i = 0; i < str1.length; i++) {
            cnt[str1[i] - 'a']++;
            cnt[str2[i] - 'a']--;
        }
        for (int cn : cnt) {
            if (cn != 0) {
                return false;
            }
        }
        return true;
    }


    public static boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        int[] cnt = new int[256];
        for (char cha : str1) {
            cnt[cha]++;
        }
        for (char cha : str2) {
            if (--cnt[cha] < 0) {
                return false;
            }
        }
        return true;
    }

    // 如果输入字符串包含 unicode 字符
    // Unicode 中可能存在一个字符对应多个字节的问题
}
