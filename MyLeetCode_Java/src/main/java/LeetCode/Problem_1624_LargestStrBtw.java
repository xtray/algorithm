package LeetCode;

import java.util.Arrays;

public class Problem_1624_LargestStrBtw {

    public int maxLengthBetweenEqualCharacters(String s) {
        if (s == null || s.length() <= 2) {
            return 0;
        }
        int[] map = new int[128];
        Arrays.fill(map, -1);
        char[] str = s.toCharArray();
        int N = str.length;
        int maxLen = -1;
        for (int i = 0; i < N; i++) {
            if (map[str[i]] == -1) {
                map[str[i]] = i;
            } else {
                maxLen = Math.max(maxLen, i - map[str[i]] - 1);
            }
        }
        return maxLen;
    }
}
