package _Codility;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

/**
 * Brackets
 * 20. 有效的括号
 * https://leetcode.cn/problems/valid-parentheses/
 * Determine whether a given string of parentheses (multiple types) is properly nested.
 *
 * https://app.codility.com/demo/results/trainingR5F97N-DQQ/
 */

public class Problem_TR701_Brackets {

    public int solution(String S) {
        if (S == null || S.length() == 0) {
            return 1;
        }
        char[] str = S.toCharArray();
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char ch : str) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.addLast(map.get(ch));
            } else {
                if (stack.isEmpty() || ch != stack.peekLast()) {
                    return 0;
                }
                stack.pollLast();
            }
        }
        return stack.isEmpty() ? 1 : 0;
    }
}
