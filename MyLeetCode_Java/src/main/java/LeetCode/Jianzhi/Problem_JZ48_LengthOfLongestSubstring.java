package LeetCode.Jianzhi;

public class Problem_JZ48_LengthOfLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < 256; i++) {
            map[i] = -1; // 所有字符都在-1位置出现过
        }
        int N = str.length;
        int ans = 1;
        int pre = 1;//上一个位置向左推了多长
        map[str[0]] = 0;
        for (int i = 1; i != N; i++) {
            int p1 = i - map[str[i]]; // 如果到-1减也是正确的
            int p2 = pre + 1;
            int cur = Math.min(p1, p2);
            ans = Math.max(ans, cur);
            pre = cur;
            map[str[i]] = i;
        }
        return ans;
    }

    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < 256; i++) {
            map[i] = -1; // 所有字符都在-1位置出现过
        }
        int N = str.length;
        int ans = 1;
        int pre = 1;//上一个位置向左推了多长
        map[str[0]] = 0;
        for (int i = 1; i != N; i++) {
            pre = Math.min(i - map[str[i]], pre + 1);
            ans = Math.max(ans, pre);
            map[str[i]] = i;
        }
        return ans;
    }
}
