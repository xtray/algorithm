package LeetCode;

import java.util.*;

public class Problem_345_ReverseVowels {


    public String reverseVowels0(String s) {
        Set<Character> set = new HashSet<>();
        for (char ch : vowels) set.add(ch);
        List<int[]> list = new ArrayList<>();
        int idx = 0;
        for (char ch : s.toCharArray()) {
            if (set.contains(ch)) list.add(new int[]{idx++, ch});
        }
        int L = 0;
        int R = list.size() - 1;
        while (L < R) {
            int[] tmp = list.get(L);
            list.set(L++, list.get(R));
            list.set(R--, tmp);
        }
        StringBuilder sb = new StringBuilder();
        idx = 0;
        for (char ch : s.toCharArray()) {
            if (!set.contains(ch)) {
                sb.append(ch);
            } else {
                sb.append((char) (list.get(idx++)[1]));
            }
        }
        return sb.toString();
    }

    private static final char[] vowels = new char[]{'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};

    public String reverseVowels(String s) {
        char[] str = s.toCharArray();
        int N = str.length;
        boolean[] set = new boolean[128];
        for (char ch : vowels) set[ch] = true;
        int L = 0;
        int R = N - 1;
        while (L < R) {
            if (set[str[L]] && set[str[R]]) {
                char tmp = str[L];
                str[L] = str[R];
                str[R] = tmp;
                L++;
                R--;
            }  else if (!set[str[L]]) {
                L++;
            } else if (!set[str[R]]) {
                R--;
            }
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {
        // String s = "hello";
        String s = "hello";
        var ans = new Problem_345_ReverseVowels().reverseVowels(s);
        System.out.println(ans);
    }
}
