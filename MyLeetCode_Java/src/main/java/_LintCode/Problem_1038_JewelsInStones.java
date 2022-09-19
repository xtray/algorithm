package _LintCode;

public class Problem_1038_JewelsInStones {

    public int numJewelsInStones(String j, String s) {
        if (j == null || j.length() == 0 || s == null || s.length() == 0) {
            return 0;
        }
        boolean[] jbool = new boolean[256];
        for (char ch : j.toCharArray()) {
            jbool[ch] = true;
        }
        int cnt = 0;
        for (char ch : s.toCharArray()) {
            cnt += jbool[ch] ? 1 : 0;
        }
        return cnt;
    }

}
