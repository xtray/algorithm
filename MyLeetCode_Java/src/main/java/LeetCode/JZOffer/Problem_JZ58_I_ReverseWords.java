package LeetCode.JZOffer;

import java.util.LinkedList;

public class Problem_JZ58_I_ReverseWords {

    public static String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        String[] words = s.trim().split("\\s+");
        int N = words.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(words[N - 1 - i]);
            if (i != N - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public static String reverseWords2(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int L = 0;
        int R = N - 1;
        // 跳过首尾的空格
        while (L < N && str[L] == ' ') {
            L++;
        }
        while (R >= 0 && str[R] == ' ') {
            R--;
        }
        LinkedList<String> stack = new LinkedList<>();
        while (L <= R) {
            StringBuilder sb = new StringBuilder();
            while (L <= R && str[L] != ' ') {
                sb.append(str[L++]);
            }
            stack.push(sb.toString());
            while (L <= R && str[L] == ' ') {
                L++;
            }
        }
        if (stack.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        while (stack.size() != 1) {
            sb.append(stack.pop());
            sb.append(" ");
        }
        sb.append(stack.pop());
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "  hello world!  ";
        var ans = reverseWords2(s);
        System.out.println(ans);
    }
}
