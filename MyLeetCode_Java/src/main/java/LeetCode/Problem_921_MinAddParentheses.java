package LeetCode;

public class Problem_921_MinAddParentheses {

    public int minAddToMakeValid(String s) {
        char[] str = s.toCharArray();
        int N = str.length;
        int cnt = 0;
        int add = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                cnt++;
            } else {
                cnt--;
                if (cnt < 0) {
                    add++;
                    cnt = 0;
                }
            }
        }
        return add + cnt;
    }
}
