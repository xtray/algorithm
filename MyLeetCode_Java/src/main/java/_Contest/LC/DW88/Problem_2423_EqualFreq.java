package _Contest.LC.DW88;

import java.util.HashMap;
import java.util.*;

public class Problem_2423_EqualFreq {

    public boolean equalFrequency(String word) {
        int[] cnt = new int[26];

        for (char ch : word.toCharArray()) {
            cnt[ch - 'a']++;
        }
        Map<Integer, List<Character>> map = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            if (cnt[i] != 0) {
                map.computeIfAbsent(cnt[i], k -> new ArrayList<>()).add((char) (i + 'a'));
            }
        }

        int maxCnt = Integer.MIN_VALUE;
        int minCnt = Integer.MAX_VALUE;
        for (int c : map.keySet()) {
            maxCnt = Math.max(maxCnt, c);
            minCnt = Math.min(minCnt, c);
        }

        // 只有一种次数, aaa, ab, abc
        // aabb
        if (map.size() == 1) {
            List<Character> list = map.get(maxCnt);
            if (list.size() == 1) return true; // 只有一种字母
            for (char ch : list) { // 多个字母, 每个频次都必须是1
                if (cnt[ch - 'a'] != 1) {
                    return false;
                }
            }
            return true;
        }

        // 必须有两种频次
        if (map.size() != 2) return false;
        // 最少的次数是1
        // aab   aaab aaaab  aaabbbc
        if (minCnt == 1 && map.get(minCnt).size() == 1) return true;
        // aabbccc
        if (maxCnt - minCnt == 1 && map.get(maxCnt).size() == 1) return true;

        return false;
    }

    public static void main(String[] args) {
        // String s = "ddaccb";
        String s = "aab";
        var ans = new Problem_2423_EqualFreq().equalFrequency(s);
        System.out.println(ans);
    }


}
