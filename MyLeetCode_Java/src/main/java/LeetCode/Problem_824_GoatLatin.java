package LeetCode;

import java.util.HashSet;
import java.util.Set;

public class Problem_824_GoatLatin {

    private static final char[] vowels = new char[]{'a', 'e', 'i', 'o', 'u'};
    private static final String last = "ma";

    public String toGoatLatin(String sentence) {
        if (sentence == null || sentence.length() == 0) {
            return sentence;
        }
        String[] words = sentence.split("\\s+");
        Set<Character> vowelsSet = new HashSet<>();
        for (char ch : vowels) {
            vowelsSet.add(ch);
            vowelsSet.add((char) (ch - 32));
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            char[] str = words[i].toCharArray();
            if (vowelsSet.contains(str[0])) {
                sb.append(words[i]);
                sb.append(last);
            } else {
                sb.append(String.valueOf(str, 1, str.length - 1));
                sb.append(str[0]);
                sb.append(last);
            }
            sb.append("a".repeat(i + 1));
            if (i != words.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
