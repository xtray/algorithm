package LeetCode;

public class Problem_1221_BalancedStrSplit {

    public int balancedStringSplit(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int cnt = 0;
        int ans = 0;
        char[] str = s.toCharArray();
        for (char ch : str) {
            if (ch == 'R') {
                cnt++;
            } else {
                cnt--;
            }
            if (cnt == 0) {
                ans++;
            }
        }
        return ans;
    }
}
