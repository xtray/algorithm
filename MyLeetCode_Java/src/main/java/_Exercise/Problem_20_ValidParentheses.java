package _Exercise;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

public class Problem_20_ValidParentheses {

    public boolean isValid(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }
        ArrayDeque<Character> stack = new ArrayDeque<>();
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        char[] str = s.toCharArray();
        for (char ch : str) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(map.get(ch));
            } else {
                if (stack.isEmpty() || stack.peek() != ch) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
