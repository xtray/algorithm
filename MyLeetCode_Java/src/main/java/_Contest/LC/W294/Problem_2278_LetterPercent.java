package _Contest.LC.W294;

public class Problem_2278_LetterPercent {

    public int percentageLetter(String s, char letter) {
        int cnt = 0;
        for(char ch : s.toCharArray()) {
            if (ch == letter) {
                cnt++;
            }
        }
        return (int)((double)100*cnt/(double)s.length());
    }

    public static void main(String[] args) {
        // String s = "foobar";
        // char letter = 'o';
        String s = "jjjj";
        char letter = 'k';
        var ans = new Problem_2278_LetterPercent().percentageLetter(s, letter);
        System.out.println(ans);
    }
}
