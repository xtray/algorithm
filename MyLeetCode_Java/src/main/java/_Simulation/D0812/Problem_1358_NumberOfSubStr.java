package _Simulation.D0812;

public class Problem_1358_NumberOfSubStr {

    public int numberOfSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        boolean[] set = new boolean[3];
        int[] cnt = new int[3];
        int size = 0;
        int L = 0;
        int R = 0;
        int ans = 0;
        // 计算以每一个L位置字符开头的子字符串个数
        boolean found = false;
        while (R < N) {
            cnt[str[R] - 'a']++;
            if (!set[str[R] - 'a']) {
                set[str[R] - 'a'] = true;
                size++;
            }
            while (size == 3) { // 结算
                found = true;
                // 算每个R位置结尾的, 看L缩到哪个位置
                cnt[str[L] - 'a']--;
                if (cnt[str[L] - 'a'] == 0) {
                    set[str[L] - 'a'] = false;
                    size--;
                }
                L++;
            }
            if (L > 0 && !set[str[L - 1] - 'a']) {
                ans += L;
            }
            R++;
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "abcabc"; // 19
        // String s = "aabcabcc"; // 19
        // String s = "aabcca"; // 7
        var ans = new Problem_1358_NumberOfSubStr().numberOfSubstrings(s);
        System.out.println(ans);
    }
}
