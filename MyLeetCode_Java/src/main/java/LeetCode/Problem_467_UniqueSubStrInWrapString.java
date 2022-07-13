package LeetCode;

public class Problem_467_UniqueSubStrInWrapString {
    public int findSubstringInWraproundString(String p) {
        if (p == null || p.length() == 0) {
            return 0;
        }
        char[] str = p.toCharArray();
        int N = str.length;
        int ans = 0;
        int len = 1;
        // 求以a,b,c...z结尾往左连续的最大子串长度
        int[] max = new int[256];
        max[str[0]]++;
        for (int i = 1; i < N; i++) {
            int cur = str[i];
            int pre = str[i - 1];
            if ((pre == 'z' && cur == 'a') || pre + 1 == cur) {
                len++;
            } else {
                len = 1;
            }
            max[cur] = Math.max(max[cur], len);
        }
        for (int i = 0; i < 256; i++) {
            ans += max[i];
        }
        return ans;
    }
}
