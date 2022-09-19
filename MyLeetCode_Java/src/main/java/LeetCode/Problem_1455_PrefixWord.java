package LeetCode;

public class Problem_1455_PrefixWord {

    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] ss = sentence.split(" ");
        int N = ss.length;
        for (int i = 0; i < N; i++) {
            if (ss[i].startsWith(searchWord)) {
                return i + 1;
            }
        }
        return -1;
    }

    public int isPrefixOfWord1(String s, String t) {
        String[] ss = s.split(" ");
        int n = ss.length;
        int m = t.length();
        for (int i = 0; i < n; i++) {
            if (ss[i].length() < m) continue;
            int j = 0;
            while (j < m && ss[i].charAt(j) == t.charAt(j)) {
                j++;
            }
            if (j == m) {
                return i + 1;
            }
        }
        return -1;
    }

}
