package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_438_FindAnagrams {

    public List<Integer> findAnagrams(String s, String p) {
        if (s == null || s.length() == 0 || p == null || p.length() == 0 || p.length() > s.length()) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        int[] tCount = new int[26];
        for (int ch : p.toCharArray()) {
            tCount[ch - 'a']++;
        }
        process(s.toCharArray(), 0, tCount, p.length(), res);
        return res;
    }

    // 从 s index 位置出发,能不能匹配出 t 的异位词
    // 结果放到 res 里
    private void process(char[] s, int index, int[] tCount, int len, List<Integer> res) {
        if (index == s.length || s.length - index < len) {
            return;
        }
        // i 位置开始到字符串结束至少有 t 个字符
        int[] count = Arrays.copyOf(tCount, 26);
        boolean match = true;
        for (int i = index; i - index < len; i++) {
            if (count[s[i] - 'a'] > 0) {
                count[s[i] - 'a']--;
            } else {
                match = false;
                break;
            }
        }
        if (match) {
            res.add(index);
        }
        process(s, index + 1, tCount, len, res);
    }


    public List<Integer> findAnagrams1(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0 || p.length() > s.length()) {
            return res;
        }
        int N = s.length();
        int M = p.length();
        int[] tCount = new int[26]; //词频欠账表
        int all = M;
        for (int ch : p.toCharArray()) {
            tCount[ch - 'a']++;
        }
        char[] str = s.toCharArray();

        for(int end = 0; end < M -1;end++){
            if(tCount[str[end] - 'a']>0) { // 有效还款
                all--;
            }
            tCount[str[end] - 'a']--;
        }
        for(int end = M-1, start = 0; end < N;end++, start++){
            if(tCount[str[end] - 'a']>0) { // 先形成一个窗口
                all--;
            }
            tCount[str[end] - 'a']--; // 无效还款会让 tcount 对应词频变为负数
            if(all ==0) {
                res.add(start);
            }
            // start 字符要出去
            if(tCount[str[start] - 'a']>=0) { // 负数的忽略
                all++; // 恢复欠账
            }
            tCount[str[start] - 'a']++;
        }
        return res;
    }
}
