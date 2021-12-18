package AlgoExpert;

import java.util.LinkedList;

// https://leetcode-cn.com/problems/reverse-words-in-a-string/
// 本题区别, 不删除首尾空格, 不合并单词中间的多个空格为一个空格

public class Problem_151_ReverseWordsInString {

    public static String reverseWordsInString(String s) {
        if (s == null ||s.length()==0) {
            return s;
        }
        char[] str = s.toCharArray();
        int L = 0;
        int R = str.length - 1;
        // 跳过首尾的空格
//        while (str[L]==' '){L++;}
//        while (str[R]==' '){R--;}
        LinkedList<String> stack = new LinkedList<>();

        while (L <= R) {
            StringBuilder sb = new StringBuilder();
            while (L<=R && str[L] != ' ') {
                sb.append(str[L++]);
            }
            stack.push(sb.toString());
            StringBuilder space = new StringBuilder();
            while (L<=R && str[L]==' ') {
                space.append(' ');
                L++;
            };
            stack.push(space.toString());
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String str = "whitespaces    4";
        String ans = reverseWordsInString(str);
        System.out.println(ans);
    }
}
