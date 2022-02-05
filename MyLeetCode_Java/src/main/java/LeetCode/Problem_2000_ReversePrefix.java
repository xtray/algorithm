package LeetCode;

public class Problem_2000_ReversePrefix {

    //
    public String reversePrefix(String word, char ch) {
        char[] str = word.toCharArray();
        int R = -1;
        for(int i = 0; i< str.length;i++) {
            if(str[i]==ch) {
                R = i;
                break;
            }
        }
        // if(R == -1) {
        //     return word;
        // }
        // 0 ... R reverse
        int L = 0;
        while (L<R) { // 没有 ch 字符, R==-1, 不会进入翻转
            char tmp = str[L];
            str[L++] = str[R];
            str[R--] = tmp;
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {
        String word = "xyzxe";
        char ch = 'z';
        var ans = new Problem_2000_ReversePrefix().reversePrefix(word, ch);
        System.out.println(ans);
    }
}
