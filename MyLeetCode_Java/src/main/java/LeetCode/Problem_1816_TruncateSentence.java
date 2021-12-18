package LeetCode;

public class Problem_1816_TruncateSentence {
    public String truncateSentence(String s, int k) {
        char[] str = s.toCharArray();
        int count = 0;
        int i = 0;
        for (; i < str.length; i++) {
            if (str[i] == ' ') {
                count++;
            }
            if (count == k) {
                break;
            }
        }
        // i 是需要断开的位置, 如果 i 到了字符串最后说明 k 等于所有单词数
        return s.substring(0,i);
    }

    public static void main(String[] args) {
        String s = "What is the solution to this problem";
        int k = 7;
        var res = new Problem_1816_TruncateSentence().truncateSentence(s, k);
        System.out.println(res);
    }
}
