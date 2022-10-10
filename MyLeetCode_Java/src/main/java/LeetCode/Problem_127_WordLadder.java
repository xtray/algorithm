package LeetCode;

import java.util.*;

public class Problem_127_WordLadder {

    public int ladderLength(String s, String e, List<String> wordList) {
        // BFS
        ArrayDeque<String> queue = new ArrayDeque<>();
        Set<String> dict = new HashSet<>(wordList);
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
                List<String> nexts = getNexts(cur, dict);
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

    private List<String> getNexts(String s, Set<String> dict) {
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < 26; j++) {
                if (s.charAt(i) != j + 'a') {
                    String cur = s.substring(0, i) + (char) (j + 'a') + s.substring(i + 1);
                    if (dict.contains(cur)) {
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
        String s = "hit";
        String e = "cog";
        String[] ss = {"hot","dot","dog","lot","log"};
        var ans = new Problem_127_WordLadder().ladderLength(s, e, Arrays.asList(ss));
        System.out.println(ans);
    }
}
