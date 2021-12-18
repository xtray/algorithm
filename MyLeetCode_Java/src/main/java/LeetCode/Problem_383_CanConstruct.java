package LeetCode;

public class Problem_383_CanConstruct {

    public boolean canConstruct(String ransomNote, String magazine) {
        if(ransomNote == null || ransomNote.length() == 0||magazine==null||magazine.length()==0) {
            return false;
        }

        char[] str1 = ransomNote.toCharArray();
        char[] str2 = magazine.toCharArray();
        int[] count = new int[26];
        for(char ch: str2) {
            count[ch - 'a']++;
        }
        for(char ch: str1) {
            if (count[ch - 'a'] <= 0) {
                return false;
            }
            count[ch - 'a']--;
        }
        return true;
    }

    public static void main(String[] args) {
        String str1 = "aa";
        String str2 = "aab";
        var res = new Problem_383_CanConstruct().canConstruct(str1, str2);
        System.out.println(res);
    }
}
