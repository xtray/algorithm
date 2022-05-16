package LeetCode;

// IMP: 重要基础题!!
// NOTE: 滑动窗口模板题

import java.util.HashMap;
import java.util.Map;

public class Problem_3_LengthOfLongestSubstring {

    // 用一个哈希表记录上次字符出现的位置
    // 数组替代哈希表
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
        int ans = 1; // 答案至少为1
        int pre = 1; // 上一个位置向左推了多长
        map[str[0]] = 0;
        for (int i = 1; i != N; i++) {
            // 可能性1: 当前字符上次出现的位置
            int p1 = i - map[str[i]]; // 如果到-1减也是正确的
            // 可能性2: 上一个位置取到的最长长度
            int p2 = pre + 1;
            // 两种可能性取最小的
            int cur = Math.min(p1, p2);
            ans = Math.max(ans, cur);
            pre = cur;
            map[str[i]] = i; // 不要忘了更新当前字符的上一次最新的位置
        }
        return ans;
    }

    public int lengthOfLongestSubstring1(String s) {
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

    // 不定长滑动窗口解法
    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int L = 0;
        int R = 0;
        int ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (R < N) {
            map.put(str[R], map.getOrDefault(str[R], 0) + 1);
            while (map.get(str[R]) > 1) {
                map.put(str[L], map.get(str[L]) - 1);
                L++;
            }
            ans = Math.max(ans, R - L + 1);
            R++;
        }
        return ans;
    }
}
