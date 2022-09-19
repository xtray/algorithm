package LeetCode.JZOffer;

import java.util.*;


public class Problem_JZII114_AlienDict {

    public String alienOrder(String[] words) {
        Map<Character, List<Character>> map = new HashMap<>(); // 邻接表
        int N = words.length;
        char[] pre = words[0].toCharArray();
        for (int i = 0; i < N; i++) {
            char[] cur = words[i].toCharArray();
            for (char ch : cur) { // 生成所有点集
                map.computeIfAbsent(ch, key -> new ArrayList<>());
            }
            if (pre[0] != cur[0]) {
                map.get(pre[0]).add(cur[0]);
            } else {
                int len = Math.min(pre.length, cur.length);
                int j = 0;
                while (j < len && pre[j] == cur[j]) {
                    j++;
                }
                // 存在不合法的情况 前一个字符串长度大于当前字符串长度, 补位最小原则
                if (j == len && pre.length > cur.length) {
                    return "";
                }
                if (j == len) {
                    continue;
                }
                map.get(pre[j]).add(cur[j]);
            }
            pre = cur;
        }
        Map<Character, Integer> inMap = new HashMap<>();
        for (Character from : map.keySet()) { // 所有的点生成入度表
            if (!inMap.containsKey(from)) {
                inMap.put(from, 0);
            }
            for (Character to : map.get(from)) {
                inMap.put(to, inMap.getOrDefault(to, 0) + 1);
            }
        }
        ArrayDeque<Character> zeroInQueue = new ArrayDeque<>();
        for (Character ch : inMap.keySet()) {
            if (inMap.get(ch) == 0) {
                zeroInQueue.add(ch);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!zeroInQueue.isEmpty()) {
            char cur = zeroInQueue.poll();
            sb.append(cur);
            for (Character next : map.getOrDefault(cur, new ArrayList<>())) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        // 检查大小是否相等, 如果不相等, 有环路!
        return sb.length() == map.size() ? sb.toString() : "";
    }

    public String alienOrder1(String[] words) {
        List<List<Integer>> graph = new ArrayList<>();
        // 初始化邻接表
        for (int i = 0; i < 26; i++) {
            graph.add(new ArrayList<>());
        }
        // 1.根据words建图
        // 规则:
        //   第一个字母不同, 排在前面的字典序在前, 建边
        //   第一个相同, 继续比较短的长度, 直到第一个不同, 建边
        //              如果一直到结束都相同, 且第一个串长, 非法, 返回
        int N = words.length;
        int[] inMap = new int[26];
        boolean[] set = new boolean[26];
        for (char ch : words[0].toCharArray()) {
            set[ch - 'a'] = true;
        }
        for (int i = 1; i < N; i++) {
            char[] pre = words[i - 1].toCharArray();
            char[] cur = words[i].toCharArray();
            for (char ch : cur) {
                set[ch - 'a'] = true;
            }
            int len1 = pre.length;
            int len2 = cur.length;
            if (pre[0] != cur[0]) {
                graph.get(pre[0] - 'a').add(cur[0] - 'a');
                inMap[cur[0] - 'a']++;
            } else {
                int j = 1;
                int len = Math.min(len1, len2);
                while (j < len && pre[j] == cur[j]) {
                    j++;
                }
                if (j == len) { //一路相等
                    if (len2 < len1) return "";
                } else {
                    graph.get(pre[j] - 'a').add(cur[j] - 'a');
                    inMap[cur[j] - 'a']++;
                }
            }
        }
        // 拓扑排序
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < 26; i++) {
            if (set[i] && inMap[i] == 0) {
                queue.addLast(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int cur = queue.pollLast();
            sb.append((char) (cur + 'a'));
            for (int next : graph.get(cur)) {
                --inMap[next];
                if (inMap[next] == 0) {
                    queue.addLast(next);
                }
            }
        }
        int nodeCnt = 0;
        for (boolean s : set) {
            if (s) nodeCnt++;
        }
        // NOTE: 最后拓扑排序的点数量不等于总点数说明有环路
        return sb.length() == nodeCnt ? sb.toString() : "";
    }

    public static void main(String[] args) {
        // String[] words = {"wrt", "wrf", "er", "ett", "rftt"};
        String[] words = {"z", "x", "a", "zb", "zx"}; // ""
        // String[] words = {"z", "z"};
        // String[] words = {"aac","aabb","aaba"};
        // String[] words = {"abc", "ab"}; // ""
        // String[] words = {"ab", "ba", "aa"};
        var ans = new Problem_JZII114_AlienDict().alienOrder(words);
        System.out.println(ans);
    }
}
