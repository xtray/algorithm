package LeetCode;

public class Problem_1189_MaxNumOfBalloons {

    public int maxNumberOfBalloons(String text) {
        if(text == null || text.length() ==0) {
            return 0;
        }
        int[] count = new int[26];
        for(char ch: text.toCharArray()) {
            count[ch -'a']++;
        }
        // a, b, n, o, l
        count['o' - 'a']/=2;
        count['o' - 'a']/=2;
        int ans = Integer.MAX_VALUE;
        char[] dict = new char[]{'a', 'b', 'n', 'o', 'l'};
        for(char ch: dict) {
            ans = Math.min(ans, count[ch - 'a']);
        }
        return ans;
    }

    public static void main(String[] args) {
        String text = "loonbalxballpoon";
        var ans = new Problem_1189_MaxNumOfBalloons().maxNumberOfBalloons(text);
        System.out.println(ans);
    }
}
