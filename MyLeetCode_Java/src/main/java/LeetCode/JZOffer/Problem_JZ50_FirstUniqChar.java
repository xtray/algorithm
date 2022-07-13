package LeetCode.JZOffer;

public class Problem_JZ50_FirstUniqChar {

    public static char firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return ' ';
        }
        char[] str = s.toCharArray();
        int[] cnt = new int[26];
        int N = str.length;
        char res = ' ';
        for (char value : str) {
            cnt[value - 'a']++;
        }
        for (char c : str) {
            if (cnt[c - 'a'] == 1) {
                return c;
            }
        }
        return ' ';
    }

    public static void main(String[] args) {
        // String s = "cc";
        String s = "dddccdbba";
        var ans = firstUniqChar(s);
        System.out.println(ans);
    }
}
