package LeetCode;

import java.util.LinkedList;

// https://leetcode-cn.com/problems/reverse-words-in-a-string/
public class Problem_151_ReverseWords {

    public static String reverseWords(String s) {
        if (s == null ||s.length()==0) {
            return s;
        }
        char[] str = s.toCharArray();
        int L = 0;
        int R = str.length - 1;
        // 跳过首尾的空格
        while (str[L]==' '){L++;}
        while (str[R]==' '){R--;}
        LinkedList<String> stack = new LinkedList<>();

        while (L <= R) {
            StringBuilder sb = new StringBuilder();
            while (L<=R && str[L] != ' ') {
                sb.append(str[L++]);
            }
            stack.push(sb.toString());
            if(L<R) {
                stack.push(" ");
            }
            while (L<=R && str[L]==' ') {L++;};
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String str = "  hello world  ";
        String ans = reverseWords(str);
        System.out.println(ans);
    }
}
