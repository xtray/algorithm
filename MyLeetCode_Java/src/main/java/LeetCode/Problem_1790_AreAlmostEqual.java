package LeetCode;

public class Problem_1790_AreAlmostEqual {

    public boolean areAlmostEqual(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return true;
        }
        if (s1 != null && s2 != null && s1.length() == 0 && s2.length() == 0) {
            return true;
        }
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0 || s1.length() != s2.length()) {
            return false;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] count = new int[26];
        int diff = 0;
        for (char ch : str1) {
            count[ch - 'a']++;
        }
        for (int i = 0; i < str2.length; i++) {
            if (str1[i] != str2[i]) {
                diff++;
            }
            if (count[str2[i] - 'a'] > 0) {
                count[str2[i] - 'a']--;
            } else {
                return false;
            }
        }
        return diff <= 2;
    }
}
