package _Contest.LC.W308;

import java.util.ArrayDeque;

public class Problem_2390_RemoveStars {

    public String removeStars(String s) {
        StringBuilder sb = new StringBuilder();
        ArrayDeque<Character> stack = new ArrayDeque<>();
        char[] str = s.toCharArray();
        int N = str.length;
        for (int i = N - 1; i >= 0; i--) {
            if (!stack.isEmpty() && stack.peekLast() == '*' && str[i] != '*') {
                stack.pollLast();
                continue;
            }
            stack.addLast(str[i]);
        }
        if (stack.isEmpty()) {
            return "";
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pollLast());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "leet**cod*e";
        var ans = new Problem_2390_RemoveStars().removeStars(s);
        System.out.println(ans);
    }
}
