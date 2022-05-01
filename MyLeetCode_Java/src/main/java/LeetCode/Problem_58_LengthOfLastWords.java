package LeetCode;

public class Problem_58_LengthOfLastWords {

    public int lengthOfLastWord(String s) {
        if(s == null || s.length() ==0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int ans = 0;
        int idx = N - 1;
        while (idx >= 0 && str[idx] == ' ') {
            idx--;
        }
        while (idx >= 0 && str[idx] != ' ') {
            idx--;
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        // String s = "   fly me   to   the moon  ";
        // String s = "a";
        String s = " ";
        var ans = new Problem_58_LengthOfLastWords().lengthOfLastWord(s);
        System.out.println(ans);
    }
}
