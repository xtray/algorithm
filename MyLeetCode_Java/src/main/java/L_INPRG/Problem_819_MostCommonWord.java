package L_INPRG;

import java.util.*;

public class Problem_819_MostCommonWord {

    // 使用哈希表统计词频, 当前出现不是字母或者到了末尾, 收集答案
    public String mostCommonWord(String paragraph, String[] banned) {
        if (paragraph == null || paragraph.length() == 0) {
            return null;
        }
        Set<String> set = new HashSet<>();
        for (String ban : banned) {
            set.add(ban);
        }
        Map<String, Integer> cntMap = new HashMap<>();
        char[] str = paragraph.toLowerCase().toCharArray();
        StringBuilder sb = new StringBuilder();
        int maxCnt = 0;
        String maxWord = null;
        for (int i = 0; i <= str.length; i++) {
            if (i == str.length || str[i] < 'a' || str[i] > 'z') {
                if (sb.length() != 0) { // 结算
                    String key = sb.toString();
                    if (!set.contains(key)) {
                        cntMap.put(key, cntMap.getOrDefault(key, 0) + 1);
                        if (cntMap.get(key) > maxCnt) {
                            maxCnt = cntMap.get(key);
                            maxWord = key;
                        }
                    }
                    sb.setLength(0);
                }
            } else {
                sb.append(str[i]);
            }
        }
        // if (sb.length() != 0) { // 结算
        //     String key = sb.toString();
        //     if (!set.contains(key)) {
        //         cntMap.put(key, cntMap.getOrDefault(key, 0) + 1);
        //         if (cntMap.get(key) > maxCnt) {
        //             maxWord = key;
        //         }
        //     }
        // }
        return maxWord;
    }

    public static void main(String[] args) {
        // String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
        // String[] banned = {"hit"};
        String paragraph = "a.";
        String[] banned = {""};
        var ans = new Problem_819_MostCommonWord().mostCommonWord(paragraph, banned);
        System.out.println(ans);
    }
}
