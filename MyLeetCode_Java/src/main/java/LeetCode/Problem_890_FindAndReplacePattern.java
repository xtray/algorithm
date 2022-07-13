package LeetCode;

import java.util.*;

public class Problem_890_FindAndReplacePattern {

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        if (words == null || words.length == 0 || pattern == null || pattern.length() == 0) {
            return new ArrayList<>();
        }
        List<String> ans = new ArrayList<>();
        char[] exp = pattern.toCharArray();
        for (String w : words) {
            char[] str = w.toCharArray();
            if (check(str, exp)) {
                ans.add(w);
            }
        }
        return ans;
    }

    private boolean check(char[] str, char[] exp) {
        if (str.length != exp.length) {
            return false;
        }
        // NOTE: 字母0~25, ascii 有256, ascii 0 位置肯定不是字母
        int[] map = new int[256];
        boolean[] used = new boolean[256];
        int N = str.length;
        for (int i = 0; i < N; i++) {
            if (map[str[i]] == 0) { // 没有映射
                if (!used[exp[i]]) {
                    map[str[i]] = exp[i];
                    used[exp[i]] = true;
                } else {
                    return false;
                }
            } else {
                // map[str[i]] != 0
                if (map[str[i]] != exp[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    // NOTE: 化简后的函数
    private boolean check1(char[] str, char[] exp) {
        if (str.length != exp.length) {
            return false;
        }
        // NOTE: 字母0~25, ascii 有256, ascii 0 位置肯定不是字母
        int[] map = new int[256];
        boolean[] used = new boolean[256];
        int N = str.length;
        for (int i = 0; i < N; i++) {
            if (map[str[i]] == 0 && !used[exp[i]]) { // 没有映射
                map[str[i]] = exp[i];
                used[exp[i]] = true;
            } else {
                // map[str[i]] != 0 or
                // map[str[i]] == 0 && used[exp[i]] == true
                // 二者都存在下面
                if (map[str[i]] != exp[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    // 字符串str 模式跟exp匹配
    private boolean check2(char[] str, char[] exp) {
        if (str.length != exp.length) {
            return false;
        }
        int N = str.length;
        Map<Character, Character> map = new HashMap<>();
        Set<Character> used = new HashSet<>();
        map.put(str[0], exp[0]);
        used.add(exp[0]);
        StringBuilder sb = new StringBuilder();
        sb.append(exp[0]);
        for (int i = 1; i < N; i++) {
            if (map.containsKey(str[i])) {
                sb.append(map.get(str[i]));
            } else {
                if (!used.contains(exp[i])) {
                    map.put(str[i], exp[i]);
                    sb.append(exp[i]);
                    used.add(exp[i]);
                } else {
                    return false;
                }
            }
        }
        return String.valueOf(exp).equals(sb.toString());
    }

    public static void main(String[] args) {
        String[] words = {"abc", "deq", "mee", "aqq", "dkd", "ccc"};
        String pattern = "abb";
        var ans = new Problem_890_FindAndReplacePattern().findAndReplacePattern(words, pattern);
        System.out.println(ans.toString());
    }
}
