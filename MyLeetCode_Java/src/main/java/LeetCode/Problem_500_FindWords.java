package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem_500_FindWords {

    // https://leetcode-cn.com/problems/keyboard-row/
    public static String[] findWords(String[] words) {
        if (words == null || words.length == 0) {
            return null;
        }
        // 对三行键盘数据做分类
        String[] dict = new String[]{"qwertyuiop", "asdfghjkl", "zxcvbnm"};
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < dict.length; i++) {
            char[] str = dict[i].toCharArray();
            for (char ch : str) {
                map.put(ch, i);
            }
        }
        List<String> res = new ArrayList<>();
        for (String word : words) {
            char[] str = word.toLowerCase().toCharArray();
            int pre = map.get(str[0]);
            //既然要求一个单词是一类, 那么必然后面的都跟第一个是一类
            boolean isValid = true;
            for (int j = 1; j < str.length; j++) {
                int cur = map.get(str[j]);
                if (cur != pre) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                res.add(word);
            }
        }
        String[] ans = new String[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"Hello", "Alaska", "Dad", "Peace"};
        String[] ans = findWords(words);
        for (String str : ans) {
            System.out.print(str + " ");
        }
        System.out.println();
    }
}
