package LeetCode;


import java.util.*;

public class Problem_318_MaxProduct {


    public int maxProduct(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (String word : words) {
            int hash = getBitNum(word.toCharArray());
            map.put(hash, Math.max(map.getOrDefault(hash, 0), word.length()));
        }
        if (map.size() == 1) {
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        for (int num1 : map.keySet()) {
            for (int num2 : map.keySet()) {
                if ((num1 & num2) == 0) {
                    ans = Math.max(ans, map.get(num1) * map.get(num2));
                }
            }
        }
        return ans == Integer.MIN_VALUE ? 0 : ans;
    }

    // 数组代替哈希表
    public int maxProduct1(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        int N = words.length;
        int[] map = new int[N];
        int idx = 0;
        for (String w : words) {
            map[idx++] = getBitNum(w.toCharArray());
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if ((map[i] & map[j]) == 0) {
                    ans = Math.max(ans, words[i].length() * words[j].length());
                }
            }
        }
        return ans;
    }

    private Integer getBitNum(char[] str) {
        int ans = 0;
        for (char ch : str) {
            ans |= 1 << (ch - 'a');
        }
        return ans;
    }

    // 对于词频相同（maskmask 值相等）的两字符，只需要保留字符长度大的即可，
    // 因此我们可以使用「哈希表」代替 masksmasks 数组。
    public int maxProduct2(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (String w : words) {
            int hash = 0;
            int len = w.length();
            for (int i = 0; i < len; i++) {
                hash |= (1 << (w.charAt(i) - 'a'));
            }
            if (!map.containsKey(hash) || map.get(hash) < len) {
                map.put(hash, len);
            }
        }
        int ans = 0;
        for (int a : map.keySet()) {
            for (int b : map.keySet()) {
                if ((a & b) == 0) {
                    ans = Math.max(ans, map.get(a) * map.get(b));
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
//        String[] words = new String[]{"a","aa","aaa","aaaa"};
        Problem_318_MaxProduct sl = new Problem_318_MaxProduct();
        int ans = sl.maxProduct(words);
        System.out.println(ans);
    }
}
