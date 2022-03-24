package LeetCode;

public class Problem_1768_MergeAlternately {

    public String mergeAlternately(String word1, String word2) {
        if (word1 == null || word1.length() == 0) {
            return word2;
        }
        if (word2 == null || word2.length() == 0) {
            return word1;
        }
        char[] str1 = word1.toCharArray();
        char[] str2 = word2.toCharArray();
        int N = str1.length;
        int M = str2.length;
        int p1 = 0;
        int p2 = 0;
        StringBuilder sb = new StringBuilder();
        while (p1 < N && p2 < M) {
            sb.append(str1[p1++]);
            sb.append(str2[p2++]);
        }
        while (p1 < N) {
            sb.append(str1[p1++]);
        }
        while (p2 < M) {
            sb.append(str2[p2++]);
        }
        return sb.toString();
    }
}
