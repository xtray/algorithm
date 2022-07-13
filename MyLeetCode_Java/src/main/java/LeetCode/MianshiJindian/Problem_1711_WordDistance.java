package LeetCode.MianshiJindian;

public class Problem_1711_WordDistance {

    public int findClosest(String[] words, String word1, String word2) {
        int pos1 = -1;
        int pos2 = -1;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if(words[i].equals(word1)) {
                pos1 = i;
            }
            if(words[i].equals(word2)) {
                pos2 = i;
            }
            if(pos1!=-1&&pos2!=-1) {
                ans = Math.min(ans, Math.abs(pos1 - pos2));
            }
        }
        return ans;
    }
}
