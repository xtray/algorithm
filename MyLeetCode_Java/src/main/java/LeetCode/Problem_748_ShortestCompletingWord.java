package LeetCode;

import java.util.Arrays;

public class Problem_748_ShortestCompletingWord {

    public String shortestCompletingWord(String licensePlate, String[] words) {

        // 统计词频
        char[] lic = licensePlate.toLowerCase().toCharArray();
        int[] count = new int[26];
        int total = 0;
        for (char ch : lic) {
            if (ch >= 'a' && ch <= 'z') {
                count[ch - 'a']++;
                total++;
            }
        }
        int minLen = Integer.MAX_VALUE;
        int resIdx = -1;
        for (int i = words.length - 1; i >= 0; i--) {
            char[] str = words[i].toCharArray();
            if (str.length < total) {
                continue;
            }
            int[] dup = Arrays.copyOf(count, count.length);
            int len = total;
            for (int ch : str) {
                if (dup[ch - 'a'] > 0) {
                    dup[ch - 'a']--;
                    len--;
                }
            }
            if (len == 0 && str.length <= minLen) {
                minLen = str.length;
                resIdx = i;
            }
        }
        return words[resIdx];
    }

    public static void main(String[] args) {
        String licensePlate = "1s3 PSt";
        String[] words = new String[]{"step", "steps", "stripe", "stepple"};
        var res = new Problem_748_ShortestCompletingWord().shortestCompletingWord(licensePlate, words);
        System.out.println(res);

    }
}
