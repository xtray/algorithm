package LeetCode.Jianzhi;

public class Problem_JZ5_ReplaceSpace {

    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        char[] str = s.toCharArray();
        for (char c : str) {
            if (c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = " A B C ";
        var ans = new Problem_JZ5_ReplaceSpace().replaceSpace(s);
        System.out.println(ans);
    }
}
