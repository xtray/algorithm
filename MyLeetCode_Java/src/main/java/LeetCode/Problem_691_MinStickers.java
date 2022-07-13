package LeetCode;

// IMP: 这个题目，它在我们整个从暴利的归到动态规划中的地位非常的高, 因为这个递归你不需要搞成严格表结构的动态规划!!
// 记忆化搜索就行了!!

import java.util.HashMap;
import java.util.Map;

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
        // NOTE: target还没处理完, 尝试每一张贴纸做为第一张
        int res = Integer.MAX_VALUE;
        for (String first : stickers) {
            String cutStr = minus(target, first);
            if (cutStr.length() != target.length()) { // 有字符被消掉了, 可以走这条路
                int next = process(stickers, cutStr);
                if (next != Integer.MAX_VALUE) { // 后序可行, 共使用next + 1张
                    res = Math.min(res, next + 1);
                }
            }
        }
        return res;
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
            if (cnt[ch - 'a'] > 0) { // str2里的字符在str1出现才去减
                cnt[ch - 'a']--;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (cnt[i] != 0) {
                sb.append(String.valueOf((char) (i + 'a')).repeat(cnt[i])); // NOTE: 学习repeat用法!!
            }
        }
        return sb.toString();
    }

    // TLE
    public int minStickers2(String[] stickers, String target) {
        if (stickers == null || stickers.length == 0 ||
                target == null || target.length() == 0) {
            return 0;
        }
        int N = stickers.length;
        int[][] count = new int[N][26]; // 生成贴纸的词频统计二维数组
        for (int i = 0; i < N; i++) {
            for (char ch : stickers[i].toCharArray()) {
                count[i][ch - 'a']++;
            }
        }
        int ans = process2(count, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    // stickers[i][j]: 贴纸二维数组词频
    // 每一张贴纸stickers[i]有无穷张, 返回搞定target的最少张数
    // 优化
    // 1. 提前生成词频表互怼, 会快很多
    // 2. 剪枝, 少走很多冤枉路
    private int process2(int[][] stickers, String t) {
        if (t.length() == 0) {
            return 0;
        }
        char[] target = t.toCharArray();
        int[] tCounts = new int[26];
        for (char ch : target) { // target做词频统计
            tCounts[ch - 'a']++;
        }
        int res = Integer.MAX_VALUE;
        for (int[] sticker : stickers) { // 尝试每一张贴纸
            // NOTE: 非常重要的剪枝优化!!
            if (sticker[target[0] - 'a'] > 0) { // 先尝试所有贴纸中必须含有我target第一个字符的贴纸才去尝试
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tCounts[j] > 0) {
                        int nums = tCounts[j] - sticker[j]; // NOTE: 会有负数
                        if (nums > 0) {
                            sb.append(String.valueOf((char) (j + 'a')).repeat(nums));
                        }
                    }
                }
                String rest = sb.toString();
                int next = process2(stickers, rest);
                if (next != Integer.MAX_VALUE) {
                    res = Math.min(res, next + 1);
                }
            }
        }
        return res;
    }

    // 改傻缓存!!
    public int minStickers3(String[] stickers, String target) {
        if (stickers == null || stickers.length == 0 ||
                target == null || target.length() == 0) {
            return 0;
        }
        int N = stickers.length;
        int[][] count = new int[N][26]; // 生成贴纸的词频统计二维数组
        for (int i = 0; i < N; i++) {
            for (char ch : stickers[i].toCharArray()) {
                count[i][ch - 'a']++;
            }
        }
        Map<String, Integer> dp = new HashMap<>();
        dp.put("", 0); // NOTE: 空字符串的数目是0!!
        int ans = process3(count, target, dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int process3(int[][] stickers, String t, Map<String, Integer> dp) {
        if (dp.containsKey(t)) {
            return dp.get(t);
        }
        char[] target = t.toCharArray();
        int[] tCounts = new int[26];
        for (char ch : target) { // target做词频统计
            tCounts[ch - 'a']++;
        }
        int res = Integer.MAX_VALUE;
        for (int[] sticker : stickers) { // 尝试每一张贴纸
            // NOTE: 非常重要的剪枝优化!!
            if (sticker[target[0] - 'a'] > 0) { // 先尝试所有贴纸中必须含有我target第一个字符的贴纸才去尝试
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tCounts[j] > 0) {
                        int nums = tCounts[j] - sticker[j]; // NOTE: 会有负数
                        if (nums > 0) {
                            sb.append(String.valueOf((char) (j + 'a')).repeat(nums));
                        }
                    }
                }
                String rest = sb.toString();
                int next = process3(stickers, rest, dp);
                if (next != Integer.MAX_VALUE) {
                    res = Math.min(res, next + 1);
                }
            }
        }
        dp.put(t, res);
        return res;
    }

}
