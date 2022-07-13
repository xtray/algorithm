package LeetCode.Jianzhi;

public class Problem_JZII032_iIsAnagram {
    public boolean isAnagram(String s, String t) {
        // NOTE: 题目要求s,t不能相等
        if (s == null || s.length() == 0 || t == null || t.length() == 0 ||
                s.length() != t.length() ||
                s.equals(t)) return false;
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
}
