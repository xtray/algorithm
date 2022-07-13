package LeetCode;

import java.util.*;

// IMP: 滑动窗口

public class Problem_30_FindSubString {

    // https://leetcode.cn/problems/substring-with-concatenation-of-all-words/solution/chuanlian-by-jiang-hui-4-ycgi/
    // 枚举每一个窗口的开始位置, 没有出窗口的过程, 每到一个i位置, 窗口重新建立
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return ans;
        }
        int M = words.length; // 单词个数
        int k = words[0].length(); // 每个单词长度
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        // 枚举每一个单词窗口的开始位置, 每个i位置建立一张哈希表
        for (int i = 0; i < s.length() - k * M + 1; i++) {
            HashMap<String, Integer> curMap = new HashMap<>();
            boolean match = true;
            for (int j = 0; j < M; j++) { // 获取从i位置开始的窗口中的每一个单词
                //第j个单词
                String curWord = s.substring(i + j * k, i + (j + 1) * k);
                //出现不存在单词
                if (!map.containsKey(curWord)) {
                    match = false;
                    break;
                }
                curMap.put(curWord, curMap.getOrDefault(curWord, 0) + 1);
                //出现超数量单词
                if (curMap.get(curWord) > map.get(curWord)) {
                    match = false;
                    break;
                }
            }
            if (match) { // 没有异常情况 记录答案
                ans.add(i);
            }
        }
        return ans;
    }

    /**
     * 优化枚举起点的过程
     * 可以将起点根据 当前下标与单词长度的取余结果 进行分类，这样我们就不用频繁的建立新的哈希表和进行单词统计。
     * 对于取余结果相同的位置，我们可以运用「滑动窗口」来进行词频统计,
     * 当处理完所有余数情况时，代表我们枚举完了所有的起点。
     */
    public List<Integer> findSubstring1(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return ans;
        }
        int N = s.length();
        int M = words.length; // 单词个数
        int k = words[0].length(); // 单词长度
        // 统计 words 中「每个目标单词」的出现次数
        Map<String, Integer> map = new HashMap<>();
        for (String str : words) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        // IMP: 因为k个字母作为一个单词整体, 每个字母都可以做为开始
        //  将起点根据 当前下标与单词长度的取余结果 进行分类, 做为k个滑动窗口的起点!!!
        for (int i = 0; i < k; i++) {
            // 构建一个当前子串对应的哈希表，统计当前子串中「每个目标单词」的出现次数
            Map<String, Integer> winMap = new HashMap<>();
            // NOTE: 滑动窗口的大小固定是 m * k，每次将下一个单词添加进 winMap，上一个单词移出 winMap
            for (int j = i; j + k <= N; j += k) { // 以k个字母为间隔滑动窗口
                String cur = s.substring(j, j + k);
                winMap.put(cur, winMap.getOrDefault(cur, 0) + 1);
                if (j >= i + (M * k)) { // 出窗口范围了
                    int idx = j - M * k; // NOTE: 出窗口的单词位置
                    String prev = s.substring(idx, idx + k); // 出窗口的单词
                    if (winMap.get(prev) == 1) {
                        winMap.remove(prev);
                    }
                    else {
                        winMap.put(prev, winMap.get(prev) - 1);
                    }
                    // if (!winMap.getOrDefault(prev, 0).equals(map.getOrDefault(prev, 0))) {
                    //     continue;
                    // }
                }
                // if (!winMap.getOrDefault(cur, 0).equals(map.getOrDefault(cur, 0))) {
                //     continue;
                // }
                // 上面两个 continue 可以减少 map 之间的 equals 操作
                if (winMap.equals(map)) { // NOTE: 学习map的相等比较操作
                    // i 位置开始的单词, i位置此时来到位置是 j + M *k, 窗口外的第一个单词
                    // j - (M - 1) * k 的位置就是此时窗口的第一个单词
                    ans.add(j - (M - 1) * k);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "barfoothefoobarman";
        String[] words = new String[]{"foo", "bar"};
        var ans = new Problem_30_FindSubString().findSubstring(s, words);
        System.out.println(ans.toString());
    }
}
