package LeetCode.MSJD;

public class Problem_0102_Permutation {

    public boolean CheckPermutation(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0 || s1.length() != s2.length()) {
            return false;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] cnt = new int[256];
        for (char ch : str1) {
            cnt[ch]++;
        }
        for (char ch : str2) {
            cnt[ch]--;
            if (cnt[ch] < 0) {
                return false;
            }
        }
        return true;
    }
}
