package _Contest.LCP.LCP2022_FALL_TEAM;

public class Problem_A {

    public int minNumBooths(String[] de) {
        int[] cnt = new int[26];
        for (String s : de) {
            int[] cur = new int[26];
            for (char ch : s.toCharArray()) {
                cur[ch - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                cnt[i] = Math.max(cnt[i], cur[i]);
            }
        }
        int sum = 0;
        for (int i = 0; i < 26; i++) {
            sum += cnt[i];
        }
        return sum;
    }
}
