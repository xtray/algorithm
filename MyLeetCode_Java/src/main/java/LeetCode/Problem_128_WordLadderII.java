package LeetCode;

import java.util.*;

// 该做法TLE

public class Problem_128_WordLadderII {

    private class Node {
        public List<String> path;
        public Set<String> set;
        public String curStr;

        public Node(List<String> list, Set<String> set, String s) {
            path = list;
            this.set = set;
            curStr = s;
        }
    }

    // TLE
    public List<List<String>> findLadders(String s, String e, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();
        // BFS
        ArrayDeque<Node> queue = new ArrayDeque<>();
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(e)) return ans;
        Set<String> set = new HashSet<>();
        List<String> list = new ArrayList<>();
        list.add(s);
        queue.addLast(new Node(list, set, s));
        set.add(s);
        int step = 1;
        int minStep = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i =0; i<size; i++) {
                Node cur = queue.pollFirst();
                if (cur.curStr.equals(e) && step <= minStep) {
                    // List<String> res = new ArrayList<>(cur.path);
                    minStep = step;
                    ans.add(cur.path);
                }
                // 找到所有cur的下一步
                List<String> nexts = getNexts(cur.curStr, dict);
                for (String n : nexts) {
                    if (!cur.set.contains(n)) {

                        List<String> path = new ArrayList<>(cur.path);
                        path.add(n);
                        Set<String> nextSet = new HashSet<>(cur.set);
                        nextSet.add(n);

                        queue.addLast(new Node(path, nextSet, n));
                    }
                }
            }
            step++;

        }
        return ans;
    }

    private List<String> getNexts(String s, Set<String> dict) {
        List<String> ans = new ArrayList<>();
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
        String s = "hit";
        String e = "cog";
        String[] ss = {"hot", "dot", "dog", "lot", "log", "cog"};

        var ans = new Problem_128_WordLadderII().findLadders(s, e, Arrays.asList(ss));
        System.out.println(ans);
    }
}
