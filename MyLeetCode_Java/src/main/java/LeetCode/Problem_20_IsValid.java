package LeetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Problem_20_IsValid {

    public boolean isValid(String s) {
        if(s == null || s.length()==0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        char[] str = s.toCharArray();
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        for(char ch : str) {
            if(ch == '(' || ch == '{' || ch == '[') {
                stack.push(map.get(ch));
            } else {
                if(!stack.isEmpty() && stack.peek() == ch) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
