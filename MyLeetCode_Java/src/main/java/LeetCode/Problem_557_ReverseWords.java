package LeetCode;

public class Problem_557_ReverseWords {

    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        StringBuilder sb = new StringBuilder();
        StringBuilder word = new StringBuilder();
        for(char ch : str ) {
            if(ch != ' ') {
                word.insert(0, ch);
            } else {
                sb.append(word).append(" ");
                word.setLength(0);
            }
        }
        return sb.append(word).toString();
    }
}
