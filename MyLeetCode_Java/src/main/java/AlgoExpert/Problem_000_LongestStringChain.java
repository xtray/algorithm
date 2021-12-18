package AlgoExpert;

import java.util.*;

public class Problem_000_LongestStringChain {

    // 从短的到长的
    public static List<String> longestStringChain(List<String> strings) {
        if (strings == null || strings.size() == 0) {
            return new ArrayList<>();
        }
        return process(strings);
    }

    // ans: 存放最长的答案
    private static List<String> process(List<String> list) {

        // 当做傻缓存
        Map<String, List<String>> map = new HashMap<>();
        // 从短到长排序
        Collections.sort(list, (a, b) -> a.length() - b.length());
        for (String word : list) {
            if (!map.containsKey(word)) {
                map.put(word, new ArrayList<>());
            }
            char[] str = word.toCharArray();
            for (int i = 0; i < str.length; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < str.length; j++) {
                    if (j != i) {
                        sb.append(str[j]);
                    }
                }
                String potentials = sb.toString();
                if (map.containsKey(potentials)) {
                    map.get(word).add(potentials);
                }
            }
        }

        LinkedList<String> res = new LinkedList<>();
        int[] maxLen = new int[1];
        maxLen[0] = Integer.MIN_VALUE;
        for(int i =list.size() - 1; i>=0; i--) {
            String startStr = list.get(i);
            if(!map.get(startStr).isEmpty()) {
                // 从每一个单词出发做 DFS
                LinkedList<String> path= new LinkedList<>();
                path.add(startStr);
                dfs(startStr, map, path, maxLen, res);
                path.removeLast();
            }
        }
        return res;
    }
    // 从 start 出发找最长的单词链
    private static void dfs(String startStr, Map<String, List<String>> map, LinkedList<String> path, int[] maxLen, LinkedList<String> res) {
        if(map.get(startStr).isEmpty()) {
            if (path.size() > maxLen[0]) {
                maxLen[0] = path.size();
                res.clear();
                res.addAll(path);
            }
            return;
        }
        var next = map.get(startStr); // 可以走的下级
        if(next.isEmpty()) {
            return;
        }
        for(String str : next) {
            path.addLast(str);
            dfs(str, map, path, maxLen, res);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        List<String> strings =
//                new ArrayList<String>(
//                        Arrays.asList("abde", "abc", "abd", "abcde", "ade", "ae", "1abde", "abcdef"));        List<String> strings =
                new ArrayList<String>(
                        Arrays.asList("abcdefg", "abdefg", "abdfg", "bdfg", "bfg", "bg", "g"));
//        List<String> expected =
//                new ArrayList<String>(Arrays.asList("abcdef", "abcde", "abde", "ade", "ae"));
        var res = longestStringChain(strings);
        System.out.println(res.toString());
    }
}
