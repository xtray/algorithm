package _LintCode;

import java.util.HashMap;
import java.util.Map;

public class Problem_1876_AlienDict {

    public boolean isAlienSorted(String[] words, String order) {

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            map.put(order.charAt(i), i);
        }
        int N = words.length;
        char[] pre = words[0].toCharArray();
        for (int i = 1; i < N; i++) {
            char[] cur = words[i].toCharArray();
            int X = pre.length;
            int Y = cur.length;
            int len = Math.min(X, Y);
            int j = 0;
            while (j < len) {
                if (pre[j] == cur[j]) {
                    j++;
                } else {
                    break;
                }
            }
            if (j == len && X > Y) { // 前面的应该短, 否则错误
                return false;
            }
            if (j == len) continue;
            // j< len
            if (map.get(pre[j]) > map.get(cur[j])) {
                return false;
            }
            pre = cur;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] words = {"abhd", "abc", "abcde"};
        String order = "hgfeabcdijklmnopqrstuvwxyz";
        var ans = new Problem_1876_AlienDict().isAlienSorted(words, order);
        System.out.println(ans);
    }
}
