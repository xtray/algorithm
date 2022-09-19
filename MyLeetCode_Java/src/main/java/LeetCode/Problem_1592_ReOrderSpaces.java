package LeetCode;

public class Problem_1592_ReOrderSpaces {

    public String reorderSpaces(String s) {
        char[] str = s.toCharArray();
        int cnt = 0;
        String[] ss = s.trim().split("\\s+");
        int N = ss.length;
        for (char ch : str) {
            if (ch == ' ') cnt++;
        }
        if (cnt == 0) return s;
        int spCnt = N == 1 ? cnt : cnt / (N - 1);
        int extra = N == 1 ? cnt : cnt %(N - 1); // 一个单词时, 所有空格挪到最后
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        for (String word : ss) {
            idx++;
            sb.append(word);
            if (idx != N) {
                sb.append(" ".repeat(spCnt));
            }
        }
        sb.append(" ".repeat(extra));
        return sb.toString();
    }

    public static void main(String[] args) {
        // String s = "  this   is  a sentence ";
        String s = "  this";
        var ans = new Problem_1592_ReOrderSpaces().reorderSpaces(s);
        System.out.println(ans);
    }


}
