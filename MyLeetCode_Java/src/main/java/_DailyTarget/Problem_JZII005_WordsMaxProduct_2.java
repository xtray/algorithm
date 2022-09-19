package _DailyTarget;

import java.util.HashMap;
import java.util.Map;

public class Problem_JZII005_WordsMaxProduct_2 {

    public int maxProduct(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        int N = words.length;
        int[] map = new int[N]; // 每个下标对应的单词生成一个数
        int idx = 0;
        for (String w : words) {
            map[idx++] = getWordNum(w.toCharArray());
        }
        int ans = 0;
        // 两两枚举求最大值
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int len1 = words[i].length();
                int len2 = words[j].length();
                if ((map[i] & map[j]) == 0) {
                    ans = Math.max(ans, len1 * len2);
                }
            }
        }
        return ans;
    }

    private int getWordNum(char[] str) {
        int ans = 0;
        for (char ch : str) {
            ans |= 1 << (ch - 'a');
        }
        return ans;
    }

    // 对于词频(mask值)相同的两字符，只保留字符最大长度
    public int maxProduct2(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        int N = words.length;
        // mask, 最大长度
        Map<Integer, Integer> map = new HashMap<>();
        for (String w : words) {
            int mask = getWordNum(w.toCharArray());
            int len = w.length();
            if (!map.containsKey(mask) || map.get(mask) < len) {
                map.put(mask, len);
            }
        }
        int ans = 0;
        for (int key1 : map.keySet()) {
            for (int key2 : map.keySet()) {
                if ((key1 & key2) == 0) {
                    ans = Math.max(ans, map.get(key1) * map.get(key2));
                }
            }
        }
        return ans;
    }


}
