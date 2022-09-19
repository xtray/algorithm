package _LintCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.*;
import java.util.Set;

public class Problem_384_LongestSubstr {

    // 用一个哈希表记录上次字符出现的位置
    // 数组替代哈希表
    // 动态规划: 考虑以每个i位置字符结尾的可能性
    // 1. 上一次当前字符出现的位置 i - j
    // 2. 上一个位置取到的最大长度 len + 1
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] map = new int[256]; // 字符上一次出现的位置
        Arrays.fill(map, -1);
        char[] str = s.toCharArray();
        int N = str.length;
        int ans = 0;
        int pre = 0; // 上一个位置取到的最大长度
        for (int i = 0; i < N; i++) {
            // 1. 当前字符上次出现的位置
            int p1 = i - map[str[i]];
            // 2. 前一个的长度
            int p2 = pre + 1;
            int cur = Math.min(p1, p2);
            ans = Math.max(ans, cur);
            pre = cur;
            map[str[i]] = i; // 不要忘了更新当前字符的上一次最新的位置
        }
        return ans;
    }

    // 滑动窗口
    public int lengthOfLongestSubstring1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int ans = 0;
        int L = 0;
        int R = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (R < N) {
            if (map.containsKey(str[R])) {
                L = map.get(str[R]) + 1;
            }
            ans = Math.max(ans, R - L + 1);
            map.put(str[R], R);
            R++;
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "an++--viaj"; // 5
        var ans = new Problem_384_LongestSubstr().lengthOfLongestSubstring(s);
        System.out.println(ans);
    }
}
