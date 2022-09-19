package _Contest.LC.W311;

public class Problem_6181_LongestSubstr {

    public int longestContinuousSubstring(String s) {
        int cnt = 1;
        int maxCnt = 1;
        int N = s.length();
        for (int i = 1; i < N; i++) {
            if (s.charAt(i) == s.charAt(i - 1) + 1) {
                cnt++;
                maxCnt = Math.max(maxCnt, cnt);
            } else {
                cnt = 1;
            }
        }
        return maxCnt;
    }

    public int longestContinuousSubstring2(String s) {
        int start = 0;
        int maxCnt = 1;
        char[] str = s.toCharArray();
        int N = s.length();
        for (int i = 1; i < N; i++) {
            if (str[i] != str[i - 1] + 1) {
                maxCnt = Math.max(maxCnt, i - start);
                start = i; // 新起点
            }
        }
        return Math.max(maxCnt, N - start);
    }

    public static void main(String[] args) {
        String s = "yrpjofyzubfsiypfre";
        var ans = new Problem_6181_LongestSubstr().longestContinuousSubstring(s);
        System.out.println(ans);
    }
}
