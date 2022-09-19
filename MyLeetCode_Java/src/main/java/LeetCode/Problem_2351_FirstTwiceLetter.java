package LeetCode;

public class Problem_2351_FirstTwiceLetter {

    public char repeatedCharacter(String s) {
        char[] str = s.toCharArray();
        char[] cnt = new char[256];
        for(char ch : str) {
            cnt[ch]++;
            if(cnt[ch] == 2) {
                return ch;
            }
        }
        return '0';
    }
}
