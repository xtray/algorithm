package _LintCode;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.*;

public class Problem_120_WordLadder {

    public int ladderLength(String s, String e, Set<String> dict) {
        // BFS
        ArrayDeque<String> queue = new ArrayDeque<>();
        Set<String> set = new HashSet<>();
        queue.addLast(s);
        set.add(s);
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.pollFirst();
                if (cur.equals(e)) {
                    return step;
                }
                // 找到所有cur的下一步
                List<String> nexts = getNexts(cur, e, dict);
                for (String n : nexts) {
                    if (!set.contains(n)) {
                        set.add(n);
                        queue.addLast(n);
                    }
                }
            }
            step++;
        }
        return 0;
    }

    private List<String> getNexts(String s, String e, Set<String> dict) {
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < 26; j++) {
                if (s.charAt(i) != j + 'a') {
                    String cur = s.substring(0, i) + (char) (j + 'a') + s.substring(i + 1);
                    if (dict.contains(cur) || cur.equals(e)) {
                        ans.add(cur);
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // String s = "hit";
        // String e = "cog";
        // String[] ss = {"hot", "dot", "dog", "lot", "log"};
        String s = "a";
        String e = "c";
        String[] ss = {"b"};
        Set<String> dict = new HashSet<>(Arrays.asList(ss));
        var ans = new Problem_120_WordLadder().ladderLength(s, e, dict);
        System.out.println(ans);
    }
}
