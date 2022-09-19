package BigComQues.MS;

import java.util.*;

/**
 * You're given a dictionary (or a map) with (key,value) pairs where value can contain
 * reference to another key in dictionary. It is possible, the values contain nested
 * references as in example is below. Implement a function that takes a dictionary
 * and return a dictionary by replacing references with actual values.
 * <p>
 * Example:
 * Input:
 * Key1 = "Hello {Key3}"
 * Key2 = "Hi {Key4}, how are you?"
 * Key3 = "{Key4}"
 * Key4 = "John {Key5}"
 * Key5 = "Doe"
 * <p>
 * Expected Output:
 * Key1 = "Hello John Doe"
 * Key2 = "Hi John Doe, how are you?"
 * Key3 = "John Doe"
 * Key4 = "John Doe"
 * Key5 = "Doe"
 */

public class Problem_MS_DecodeMessage {

    public String[][] decodeMessage(String[][] dict) {
        Map<String, Set<String>> map = new HashMap<>();
        Map<String, Integer> inMap = new HashMap<>();
        Map<String, String> dicMap = new HashMap<>();
        for (String[] d : dict) {
            // Key1 = "Hello {Key3}"
            // to        from
            // Key1需要先做Key3, 所以Key3在前
            // NOTE: 注意from, to是反着理解的
            String to = d[0];
            String val = d[1];
            dicMap.put(to, val);
            Set<String> keys = getKeysFromVal(val);
            for (String from : keys) {
                map.computeIfAbsent(from, k -> new HashSet<>()).add(to);
                inMap.put(to, inMap.getOrDefault(to, 0) + 1);
            }
        }
        ArrayDeque<String> queue = new ArrayDeque<>();
        for (String[] key : dict) {
            if (inMap.getOrDefault(key[0], 0) == 0) {
                queue.add(key[0]);
            }
        }
        String[][] ans = new String[dict.length][2];
        int idx = 0;
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            String real = decode(dicMap, cur);
            dicMap.put(cur, real);
            ans[idx][0] = cur;
            ans[idx++][1] = real;
            for (String next : map.getOrDefault(cur, new HashSet<>())) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    queue.add(next);
                }
            }
        }
        Arrays.sort(ans, (o1, o2) -> o1[0].compareTo(o2[0]));
        return ans;
    }

    private String decode(Map<String, String> dicMap, String s) {
        char[] str = dicMap.get(s).toCharArray();
        boolean key = false;
        StringBuilder sb = new StringBuilder();
        StringBuilder encodedKey = new StringBuilder();
        for (char ch : str) {
            if (ch == '{') {
                key = true;
            } else if (ch == '}') {
                String value = dicMap.get(encodedKey.toString());
                sb.append(value);
                encodedKey.setLength(0);
                key = false;
            } else if (key) {
                encodedKey.append(ch);
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    private Set<String> getKeysFromVal(String val) {
        Set<String> ans = new HashSet<>();
        char[] str = val.toCharArray();

        boolean key = false;
        StringBuilder sb = new StringBuilder();
        for (char ch : str) {
            if (ch == '{') {
                key = true;
            } else if (ch == '}') {
                ans.add(sb.toString());
                sb.setLength(0);
                key = false;
            } else if (key) {
                sb.append(ch);
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        String[][] dict = {
                {"Key1", "Hello {Key3}"},
                {"Key2", "Hi {Key4}, how are you?"},
                {"Key3", "{Key4}"},
                {"Key4", "John {Key5}"},
                {"Key5", "Doe"}
        };
        var ans = new Problem_MS_DecodeMessage().decodeMessage(dict);
        System.out.println(Arrays.deepToString(ans));
    }


}
