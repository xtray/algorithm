package LeetCode;

public class Problem_2124_CheckString {

    public boolean checkString(String s) {
        int a = -1;
        int b = s.length();
        int idx = 0;
        for (char ch : s.toCharArray()) {
            if (ch == 'a') a = idx;
            if (ch == 'b' && b == s.length()) b = idx;
            idx++;
        }
        return a < b;
    }
}
