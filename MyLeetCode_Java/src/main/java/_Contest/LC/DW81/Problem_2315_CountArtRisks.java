package _Contest.LC.DW81;

public class Problem_2315_CountArtRisks {

    public int countAsterisks(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int cnt = 0;
        char[] str = s.toCharArray();
        int N = str.length;
        boolean inRange = false;
        for (int i = 0; i < N; i++) {
            if(str[i] == '|') {
                inRange = !inRange;
            } else if(str[i] == '*') {
                if(!inRange) { // 不在||之间
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        // String s = "l|*e*et|c**o|*de|";
        // String s = "iamprogrammer";
        String s = "yo|uar|e**|b|e***au|tifu|l";
        var ans = new Problem_2315_CountArtRisks().countAsterisks(s);
        System.out.println(ans);
    }
}
