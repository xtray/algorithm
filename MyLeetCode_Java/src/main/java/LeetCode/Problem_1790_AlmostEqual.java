package LeetCode;

public class Problem_1790_AlmostEqual {

    public boolean areAlmostEqual(String s1, String s2) {
        int cnt = 0;
        int pos1 = -1;
        int pos2 = -1;
        int N = s1.length();
        for (int i = 0; i < N; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                cnt++;
                if (pos1 == -1) {
                    pos1 = i;
                } else if (pos2 == -1) {
                    pos2 = i;
                }
                if (cnt > 2) {
                    return false;
                }
            }
        }
        if (cnt == 0) {
            return true;
        }
        if (cnt == 1) {
            return false;
        }
        return s1.charAt(pos1) == s2.charAt(pos2) && s2.charAt(pos1) == s1.charAt(pos2);
    }
}
