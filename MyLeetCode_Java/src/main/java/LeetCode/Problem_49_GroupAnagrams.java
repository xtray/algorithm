package LeetCode;

import java.util.*;

public class Problem_49_GroupAnagrams {

    // 解法 1: 排序后放入哈希表做分类
    public List<List<String>> groupAnagrams(String[] words) {
        List<List<String>> ans = new ArrayList<>();
        if (words == null || words.length == 0 ) {
            return ans;
        }
        Map<String, List<String >> res = new HashMap<>();
        for (String word : words) {
            char[] str = word.toCharArray();
            Arrays.sort(str);
            String key = String.valueOf(str);
            if(!res.containsKey(key)) {
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
    public List<List<String>> groupAnagrams2(String[] words) {
        List<List<String>> ans = new ArrayList<>();
        if (words == null || words.length == 0 ) {
            return ans;
        }
        Map<String, List<String >> res = new HashMap<>();
        for (String word : words) {
            char[] str = word.toCharArray();
            int[] count = new int[26];
            for(char ch : str) {
                count[ch - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for(int cnt : count) {
                sb.append(String.valueOf(cnt));
            }
            String key = sb.toString();
            if(!res.containsKey(key) ){
                res.put(key, new ArrayList<>());
            }
            res.get(key).add(word);
        }
        for(String key : res.keySet()) {
            ans.add(res.get(key));
        }
        return ans;
    }
}
