package AlgoExpert;

import java.util.*;

public class Problem_000_LongestStringChain_2 {

    public static class Info {
        String nextStr;
        int maxLen;

        public Info(String str, int len) {
            nextStr = str;
            maxLen = 1;
        }
    }

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
        Map<String, Info> map = new HashMap<>();
        // 从短到长排序
        Collections.sort(list, (a, b) -> a.length() - b.length());
        for (String word : list) {
            if (!map.containsKey(word)) {
                map.put(word, new Info("", 1));
            }
            char[] str = word.toCharArray();
            for (int i = 0; i < str.length; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < str.length; j++) {
                    if (j != i) {
                        sb.append(str[j]);
                    }
                }
                String potential = sb.toString();
                if (map.containsKey(potential)) {
                    updateLongest(word, potential, map);
                }
            }
        }
        return buildLongestStringChain(list, map);
    }

    private static List<String> buildLongestStringChain(List<String> list, Map<String, Info> map) {
        int maxLen = Integer.MIN_VALUE;
        String startStr = "";
        for (String str : list) {
            if (map.get(str).maxLen > maxLen) {
                maxLen = map.get(str).maxLen;
                startStr = str;
            }
        }
        // 根据 startStr 生成返回值
        List<String> res = new ArrayList<>();
        while (!startStr.equals("")) {
            res.add(startStr);
            Info node = map.get(startStr);
            startStr = node.nextStr;
        }
        // 题目要求长度为 1 时返回空
        return maxLen == 1 ? new ArrayList<>() : res;
    }

    // 根据 word 减一个字符找到 potential, 更新 map
    private static void updateLongest(String word, String potential, Map<String, Info> map) {
        Info curInfo = map.get(word);
        Info poInfo = map.get(potential);
        if (poInfo.maxLen + 1 > curInfo.maxLen) {
            curInfo.maxLen = poInfo.maxLen + 1;
            curInfo.nextStr = potential;
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
