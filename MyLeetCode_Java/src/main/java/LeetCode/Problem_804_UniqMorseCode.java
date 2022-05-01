package LeetCode;

import java.util.HashSet;
import java.util.Set;

public class Problem_804_UniqMorseCode {

    private static final String[] morse = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
    public int uniqueMorseRepresentations(String[] words) {
        if(words == null || words.length ==0) {
            return 0;
        }
        Set<String> set = new HashSet<>();
        for(String word : words) {
            StringBuilder sb = new StringBuilder();
            for(char ch : word.toCharArray()) {
                sb.append(morse[ch - 'a']);
            }
            set.add(sb.toString());
        }
        return set.size();
    }
}
