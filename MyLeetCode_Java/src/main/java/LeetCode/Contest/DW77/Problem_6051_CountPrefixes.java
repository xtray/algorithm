package LeetCode.Contest.DW77;

public class Problem_6051_CountPrefixes {


    public int countPrefixes(String[] words, String s) {
        if (words == null || words.length == 0) {
            return 0;
        }
        int ans = 0;
        char[] str = s.toCharArray();
        int N = str.length;
        for (String word : words) {
            if (word.length() > N) continue;
            int i = 0;
            while (i < N && i < word.length() && word.charAt(i) == str[i]) {
                i++;
            }
            if(i == word.length()) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // String[] words = {"a", "b", "c", "ab", "bc", "abc"};
        // String s = "abc";
        // String[] words = {"a", "a"};
        // String s = "aa";
        String[] words = {"feh", "w", "w", "lwd", "c", "s", "vk", "zwlv", "n", "w", "sw", "qrd", "w", "w", "mqe", "w", "w", "w", "gb", "w", "qy", "xs", "br", "w", "rypg", "wh", "g", "w", "w", "fh", "w", "w", "sccy"};
        String s = "w";
        var ans = new Problem_6051_CountPrefixes().countPrefixes(words, s);
        System.out.println(ans);
    }
}
