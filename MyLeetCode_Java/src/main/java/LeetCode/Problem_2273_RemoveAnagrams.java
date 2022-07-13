package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.cn/problems/find-resultant-array-after-removing-anagrams/

public class Problem_2273_RemoveAnagrams {

    public List<String> removeAnagrams(String[] words) {
        List<String> ans = new ArrayList<>();
        if (words == null || words.length == 0) {
            return ans;
        }
        int N = words.length;
        int idx = 1;
        char[] ch = words[0].toCharArray();
        Arrays.sort(ch);
        String pre = String.valueOf(ch);
        ans.add(words[0]);
        while (idx < N) {
            char[] cur = words[idx].toCharArray();
            Arrays.sort(cur);
            String curStr = String.valueOf(cur);
            if (curStr.compareTo(pre) != 0) {
                ans.add(words[idx]);
                pre = curStr;
            }
            idx++;
        }
        return ans;
    }

    public List<String> removeAnagrams2(String[] words) {
        List<String> ans = new ArrayList<>();
        if (words == null || words.length == 0) {
            return ans;
        }
        int N = words.length;
        char[] pre = words[0].toCharArray();
        Arrays.sort(pre);
        ans.add(words[0]);
        for (int i = 1; i < N; i++) {
            char[] cur = words[i].toCharArray();
            Arrays.sort(cur);
            if (!Arrays.equals(pre, cur)) {
                ans.add(words[i]);
                pre = cur;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] words = {"abba", "baba", "bbaa", "cd", "cd"};
        // String[] words = {"a","b","c","d","e"};
        // String[] words = {"z","z","z","gsw","wsg","gsw","krptu"};
        // String[] words = {"a", "b", "a"}; // "a", "b", "a"
        var ans = new Problem_2273_RemoveAnagrams().removeAnagrams(words);
        for (String w : ans) {
            System.out.print(w + " ");
        }
        System.out.println();
    }
}
