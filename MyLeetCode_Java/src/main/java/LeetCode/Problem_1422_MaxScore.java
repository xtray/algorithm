package LeetCode;

public class Problem_1422_MaxScore {

    public int maxScore(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int ones = 0;
        // 先统计1的个数
        for (char ch : str) {
            if (ch == '1') {
                ones++;
            }
        }
        int left = str[0] == '0' ? 1 : 0;
        int cnt = str[0] == '1' ? 1 : 0; // 左侧1的个数
        int maxScore = left + ones - cnt;
        // 枚举每一个分割点
        for (int i = 1; i < N - 1; i++) {
            left += str[i] == '0' ? 1 : 0;
            cnt += str[i] == '1' ? 1 : 0;
            maxScore = Math.max(maxScore, left + ones - cnt);
        }
        return maxScore;
    }

    public int maxScore1(String s) {
        int n = s.length();
        int cur = s.charAt(0) == '0' ? 1 : 0;
        for (int i = 1; i < n; i++) {
            cur += s.charAt(i) - '0';
        }
        int ans = cur;
        for (int i = 1; i < n - 1; i++) {
            // 是0的话, 左边+1分, 右边不变, 当前总分+1
            // 是1的话, 左边不变, 右边-1分, 当前总分-1
            cur += s.charAt(i) == '0' ? 1 : -1;
            ans = Math.max(ans, cur);
        }
        return ans;
    }


    public static void main(String[] args) {
        String s = "011101"; // 5
        // String s = "1111"; // 5
        // String s = "00"; // 1
        var ans = new Problem_1422_MaxScore().maxScore(s);
        System.out.println(ans);
    }
}
