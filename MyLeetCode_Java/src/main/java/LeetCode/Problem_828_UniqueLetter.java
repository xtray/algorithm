package LeetCode;

import java.util.Arrays;

public class Problem_828_UniqueLetter {


    // https://leetcode.cn/problems/count-unique-characters-of-all-substrings-of-a-given-string/solution/tong-ji-zi-chuan-zhong-de-wei-yi-zi-fu-b-ajio/
    public int uniqueLetterString(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] lastIdx = new int[26];
        int[] curIdx = new int[26];
        Arrays.fill(lastIdx, -1);
        Arrays.fill(curIdx, -1);
        char[] str = s.toCharArray();
        int ans = 0;
        for (int i = 0; i < str.length; i++) {
            int index = str[i] - 'A';
            if (curIdx[index] > -1) {
                ans += (i - curIdx[index]) * (curIdx[index] - lastIdx[index]);
            }
            lastIdx[index] = curIdx[index];
            curIdx[index] = i;
        }
        for (int i = 0; i < 26; i++) {
            if (curIdx[i] > -1) {
                ans += (curIdx[i] - lastIdx[i]) * (s.length() - curIdx[i]);
            }
        }
        return ans;
    }

}
