package LeetCode;

public class Problem_691_MinStickers {

    // 暴力递归, TLE
    public int minStickers(String[] stickers, String target) {
        if (stickers == null || stickers.length == 0 ||
                target == null || target.length() == 0) {
            return 0;
        }
        int ans = process(stickers, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    // 贴纸数组能拼出target的最小张数
    private int process(String[] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }
        int res = Integer.MAX_VALUE;
        // NOTE: 尝试每一张贴纸做为第一张
        for (String first : stickers) {
            String cutStr = minus(target, first);
            if (cutStr.length() != target.length()) {
                // 有利可图
                res = Math.min(res, process(stickers, cutStr));
            }
        }
        return res != Integer.MAX_VALUE ? res + 1 : Integer.MAX_VALUE;
    }

    // 从target字符里减掉first字符
    private String minus(String target, String first) {
        char[] str1 = target.toCharArray();
        char[] str2 = first.toCharArray();
        int[] cnt = new int[26];
        for (char ch : str1) {
            cnt[ch - 'a']++;
        }
        for (char ch : str2) {
            if (cnt[ch - 'a'] > 0) {
                cnt[ch - 'a']--;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (cnt[i] != 0) {
                sb.append(String.valueOf((char) (i + 'a')).repeat(cnt[i]));
            }
        }
        return sb.toString();
    }
}
