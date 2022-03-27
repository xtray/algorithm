package LeetCode;

public class Problem_1309_FreqAlphabets {

    // NOTE: 重点是反向遍历
    public String freqAlphabets(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int index = N - 1;
        StringBuilder sb = new StringBuilder();
        // 碰到# 读取两个, 否则一个一个读取
        while (index >= 0) {
            if (str[index] == '#' && index - 2 >= 0) {
                int val = (str[index - 2] - '0') * 10 + (str[index - 1] - '0');
                sb.append((char) (val - 1 + 'a'));
                index -= 3;
            } else {
                sb.append((char) (str[index--] - '0' - 1 + 'a'));
            }
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        String s = "10#11#12";
        var ans = new Problem_1309_FreqAlphabets().freqAlphabets(s);
        System.out.println(ans);
    }
}
