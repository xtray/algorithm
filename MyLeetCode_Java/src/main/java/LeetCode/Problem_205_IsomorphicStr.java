package LeetCode;

import java.util.Arrays;

public class Problem_205_IsomorphicStr {

    public boolean isIsomorphic(String s, String t) {
        char[] s1 = s.toCharArray();
        char[] s2 = t.toCharArray();
        int N = s1.length;
        int[] map = new int[256]; // 映射表
        boolean[] set = new boolean[256]; // to占用表
        Arrays.fill(map, -1);
        for (int i = 0; i < N; i++) {
            if (map[s1[i]] == -1) {
                if (set[s2[i]]) return false; // 检查to是否被占用
                set[s2[i]] = true;
                map[s1[i]] = s2[i]; // 可以映射
            } else {
                if (map[s1[i]] != s2[i]) { // 是否映射匹配
                    return false;
                }
            }
        }
        return true;
    }
}
