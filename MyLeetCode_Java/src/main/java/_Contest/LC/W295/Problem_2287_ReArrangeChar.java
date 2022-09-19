package _Contest.LC.W295;

public class Problem_2287_ReArrangeChar {

    public int rearrangeCharacters(String s, String target) {
        int[] cnt = new int[26];
        int[] tcnt = new int[26];
        for (char ch : s.toCharArray()) {
            cnt[ch - 'a']++;
        }
        int ans = Integer.MAX_VALUE;
        for (char ch : target.toCharArray()) {
            tcnt[ch - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (tcnt[i] != 0) {
                ans = Math.min(ans, cnt[i] / tcnt[i]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "ilovecodingonleetcode", target = "code";//2
        // String s = "abcba", target = "abc"; //1
        // String s = "abbaccaddaeea", target = "aaaaa"; //1
        var ans = new Problem_2287_ReArrangeChar().rearrangeCharacters(s, target);
        System.out.println(ans);
    }
}
