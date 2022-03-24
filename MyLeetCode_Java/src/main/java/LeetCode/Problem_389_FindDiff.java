package LeetCode;

public class Problem_389_FindDiff {
    public char findTheDifference(String s, String t) {
        char[] s1 = s.toCharArray();
        char[] s2 = t.toCharArray();
        int[] cnt = new int[26];
        for (char ch : s1) {
            cnt[ch - 'a']++;
        }
        for (char ch : s2) {
            if (cnt[ch - 'a'] == 0) {
                return ch;
            } else {
                cnt[ch - 'a']--;
            }
        }
        return ' ';
    }

    public char findTheDifference2(String s, String t) {
        char[] s1 = s.toCharArray();
        char[] s2 = t.toCharArray();
        int[] cnt = new int[26];
        for (char ch : s1) {
            cnt[ch - 'a']++;
        }
        for (char ch : s2) {
            cnt[ch - 'a']--;
            if (cnt[ch - 'a'] < 0) {
                return ch;
            }
        }
        return ' ';
    }

    // ASCII求和, 以及整数强转char
    public char findTheDifference3(String s, String t) {
        char[] s1 = s.toCharArray();
        char[] s2 = t.toCharArray();
        int sum = 0;
        for (char ch : s2) {
            sum += ch;
        }
        for (char ch : s1) {
            sum -= ch;
        }
        return (char) sum;
    }

    public static void main(String[] args) {
        String s = "a";
        String t = "aa";
        var ans = new Problem_389_FindDiff().findTheDifference(s, t);
        System.out.println(ans);
    }
}
