package LeetCode;

import java.util.*;

public class Problem_49_GroupAnagrams {

    // 解法 1: 排序后放入哈希表做分类
    public List<List<String>> groupAnagrams(String[] words) {
        List<List<String>> ans = new ArrayList<>();
        if (words == null || words.length == 0) {
            return ans;
        }
        Map<String, List<String>> res = new HashMap<>();
        for (String word : words) {
            char[] str = word.toCharArray();
            Arrays.sort(str);
            String key = String.valueOf(str);
            if (!res.containsKey(key)) {
                res.put(key, new ArrayList<>());
            }
            res.get(key).add(word);
        }
//        for(String key : res.keySet()) {
//            ans.add(res.get(key));
//        }
        ans.addAll(res.values());
        return ans;
    }

    // 解法 2: 如果字符种类比较少, 用 count[26]数组做统计
    // 方法二绕来绕去其实本质上还是对字符串进行排序，计数排序
    // NOTE: 这个是错误的, ref: groupAnagrams3, groupAnagrams4
    // ["bdddddddddd","bbbbbbbbbbc"] -- > [["bdddddddddd","bbbbbbbbbbc"]] , 错误
    // 实际结果: [["bbbbbbbbbbc"],["bdddddddddd"]]
    // 因为 010 --> 0, 10, or 0, 1, 0
    public List<List<String>> groupAnagrams2(String[] words) {
        List<List<String>> ans = new ArrayList<>();
        if (words == null || words.length == 0) {
            return ans;
        }
        Map<String, List<String>> res = new HashMap<>();
        for (String word : words) {
            char[] str = word.toCharArray();
            int[] count = new int[26];
            for (char ch : str) {
                count[ch - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int cnt : count) {
                sb.append(String.valueOf(cnt));
            }
            String key = sb.toString();
            if (!res.containsKey(key)) {
                res.put(key, new ArrayList<>());
            }
            res.get(key).add(word);
        }
        for (String key : res.keySet()) {
            ans.add(res.get(key));
        }
        return ans;
    }

    public List<List<String>> groupAnagrams3(String[] words) {
        List<List<String>> ans = new ArrayList<>();
        if (words == null || words.length == 0) {
            return ans;
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String word : words) {
            int[] cnt = new int[26];
            for (char ch : word.toCharArray()) {
                cnt[ch - 'a']++;
            }
            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (cnt[i] != 0) {
                    sb.append((char) (i + 'a'));
                    sb.append(cnt[i]);
                }
            }
            String key = sb.toString();
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(word);
        }
        ans.addAll(map.values());
        return ans;
    }

    public List<List<String>> groupAnagrams4(String[] words) {
        List<List<String>> ans = new ArrayList<>();
        if (words == null || words.length == 0) {
            return ans;
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String word : words) {
            char[] cnt = new char[26];
            for (char ch : word.toCharArray()) {
                cnt[ch - 'a']++;
            }
            String keyStr = String.valueOf(cnt);
            if (!map.containsKey(keyStr)) {
                map.put(keyStr, new ArrayList<>());
            }
            map.get(keyStr).add(word);
        }
        ans.addAll(map.values());
        return ans;
    }

    public static void main(String[] args) {
        // String[] words = {"bdddddddddd", "bbbbbbbbbbc"};
        String[] words = {"eat","tea","tan","ate","nat","bat"};
        var ans1 = new Problem_49_GroupAnagrams().groupAnagrams(words);
        var ans2 = new Problem_49_GroupAnagrams().groupAnagrams2(words);
        var ans3 = new Problem_49_GroupAnagrams().groupAnagrams3(words);
        var ans4 = new Problem_49_GroupAnagrams().groupAnagrams4(words);
        System.out.println();
    }
}
