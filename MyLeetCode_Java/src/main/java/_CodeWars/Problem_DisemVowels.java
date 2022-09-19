package _CodeWars;

import java.util.HashSet;
import java.util.Set;

public class Problem_DisemVowels {

    public static String disemvowel(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        Set<Character> set = new HashSet<>();
        for (char ch : vowels) {
            set.add(ch);
        }
        StringBuilder sb = new StringBuilder();
        for (char ch : str) {
            if (!set.contains(ch) && !set.contains((char) (ch + 32))) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
