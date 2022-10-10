package LeetCode;

import java.util.*;

// 力扣增加case了, 这个做法超时了

public class Problem_128_WordLadderII_2 {

    // TLE
    public List<List<String>> findLadders(String s, String e, List<String> wordList) {

        Set<String> set = new HashSet<>(wordList);
        Map<String, List<String>> nextsMap = new HashMap<>(); // 邻居表
        if (!set.contains(e)) return new ArrayList<>();

        // 1.生成邻居表
        getNexts(s, set, nextsMap);
        for (String word : wordList) {
            getNexts(word, set, nextsMap);
        }
        // 2.生成距离表, 指导下面的DFS
        Map<String, Integer> distMap = getDistance(s, nextsMap);

        List<List<String>> ans = new ArrayList<>();
        List<String> list = new ArrayList<>();
        list.add(s); // 先加入
        getShortestPaths(s, e, nextsMap, distMap, list, ans);
        return ans;
    }

    private void getShortestPaths(
            String cur,
            String to,
            Map<String, List<String>> nextsMap,
            Map<String, Integer> distMap,
            List<String> path,
            List<List<String>> ans) {
        if (cur.equals(to)) {
            // path.add(cur);
            ans.add(new ArrayList<>(path));
            return;
        }
        for (String next : nextsMap.get(cur)) {
            if (distMap.get(next) == distMap.get(cur) + 1) {
                path.add(next); // 先加入
                getShortestPaths(next, to, nextsMap, distMap, path, ans);
                path.remove(path.size() - 1);
            }
        }
    }

    // BFS
    private Map<String, Integer> getDistance(String s, Map<String, List<String>> map) {
        Map<String, Integer> distMap = new HashMap<>();
        ArrayDeque<String> queue = new ArrayDeque<>();
        Set<String> set = new HashSet<>();
        set.add(s);
        queue.addLast(s);
        distMap.put(s, 0);
        while (!queue.isEmpty()) {
            String curStr = queue.pollFirst();
            for (String next : map.get(curStr)) {
                if (!set.contains(next)) {
                    set.add(next);
                    distMap.put(next, distMap.get(curStr) + 1);
                    queue.add(next);
                }
            }
        }
        return distMap;
    }

    // 每一个单词每一个位置做a~z变换, 找寻是否在dict里出现
    private void getNexts(String s, Set<String> dict, Map<String, List<String>> map) {
        List<String> list = new ArrayList<>();
        char[] str = s.toCharArray();
        int N = str.length;
        for (int i = 0; i < N; i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) { // 每一个i位置都a~z变换
                if (str[i] != ch) {
                    char tmp = str[i];
                    str[i] = ch;
                    String cur = String.valueOf(str);
                    if (dict.contains(cur)) {
                        list.add(cur);
                    }
                    str[i] = tmp;
                }
            }
        }
        map.put(s, list);
    }

    public static void main(String[] args) {
        // String s = "hit";
        // String e = "cog";
        // String[] ss = {"hot", "dot", "dog", "lot", "log", "cog"};

        String s = "a";
        String e = "c";
        String[] ss = {"a", "b", "c"};

        var ans = new Problem_128_WordLadderII_2().findLadders(s, e, Arrays.asList(ss));
        System.out.println(ans);
    }
}
