package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem_884_UncommonFromSentences {

    public String[] uncommonFromSentences(String s1, String s2) {
        if ((s1 == null || s1.length() == 0) && (s2 == null || s2.length() == 0)) {
            return new String[0];
        }
        List<String> ans = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        add(map, s1);
        add(map, s2);
        for (String str : map.keySet()) {
            if (map.get(str) == 1) {
                ans.add(str);
            }
        }
        return ans.toArray(new String[0]);
    }

    private void add(Map<String, Integer> map, String s1) {
        // \\s表示   空格,回车,换行等空白符
        String[] str1 = s1.split("\\s+");
        for (String str : str1) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
    }


}
