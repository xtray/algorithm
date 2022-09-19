package LeetCode;

import java.util.Arrays;

public class Problem_2399_LetterDistances {

    public boolean checkDistances(String s, int[] distance) {
        char[] str = s.toCharArray();
        int N = str.length;
        int[] last = new int[26];
        Arrays.fill(last, -1);
        for (int i = 0; i < N; i++) {
            char ch = str[i];
            if (last[ch - 'a'] == -1) {
                last[ch - 'a'] = i;
            } else {
                last[ch - 'a'] = i - last[ch - 'a'] -1;
            }
        }
        for (int i = 0; i < 26; i++) {
            if(last[i] != -1 && last[i] != distance[i])  {
                return false;
            }
        }
        return true;
    }
}
