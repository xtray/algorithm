package LeetCode;

public class Problem_859_BuddyStrings {
    public boolean buddyStrings(String s, String goal) {
        if (s == null || goal == null || s.length() == 0 || goal.length() == 0) {
            return false;
        }
        if (s.length() < 2 || goal.length() < 2 || s.length() != goal.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = goal.toCharArray();
        int i = 0;
        int[] count = new int[26];
        boolean dup = false;
        int diff = 0;
        int diffNum1 = -1;
        int diffNum2 = -1;
        while (i < str1.length) {
            count[str1[i]-'a']++;
            if(count[str1[i]-'a'] >=2) {
                dup = true;
            }
            if (str1[i] != str2[i]) {
                if(diffNum1==-1) {
                    diffNum1 = str1[i]-'a';
                    diffNum2 = str2[i]-'a';
                } else {
                    diffNum1 -= str2[i]-'a';
                    diffNum2 -= str1[i]-'a';
                }
                diff++;
            }
            i++;
        }
        // 情况 1: 两个字符串完全一样, 且有重复的字符串
        // 情况 2 : 不同字符超过两个
        return (diff == 0 && dup) || (diff == 2 && diffNum1 == 0 && diffNum2 ==0 );
    }

    public static void main(String[] args) {
        Problem_859_BuddyStrings sl = new Problem_859_BuddyStrings();
        String str1 = "bc";
        String str2 = "cb";
        var res = sl.buddyStrings(str1, str2);
        System.out.println(res);
    }
}
