package _LintCode;

import java.util.*;

public class Problem_423_ValidParentheses {

    public boolean isValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        int N = s.length();
        char[] str = s.toCharArray();
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            if (str[i] == '(' || str[i] == '[' || str[i] == '{') {
                stack.addLast(map.get(str[i]));
            } else {
                if (!stack.isEmpty() && stack.peekLast() == str[i]) {
                    stack.pollLast();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
