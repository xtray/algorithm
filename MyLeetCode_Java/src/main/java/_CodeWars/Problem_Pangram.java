package _CodeWars;

public class Problem_Pangram {

    public boolean check(String sentence) {
        if (sentence == null || sentence.length() == 0) {
            return false;
        }
        boolean[] set = new boolean[26];
        int cnt = 0;
        for (char ch : sentence.toLowerCase().toCharArray()) {
            if (ch < 'a' || ch > 'z') continue;
            if (!set[ch - 'a']) {
                cnt++;
                set[ch - 'a'] = true;
            }
        }
        return cnt == 26;
    }
}
