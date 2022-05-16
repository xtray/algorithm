package LeetCode;

import java.util.*;

// PENDING: 写的太复杂了, 待优化!!


public class Problem_726_CountOfAtoms {

    public String countOfAtoms(String formula) {
        if (formula == null || formula.length() == 0) {
            return "";
        }
        Info info = process(formula.toCharArray(), 0);
        return generateStr(info.map);
    }

    private String generateStr(Map<String, Integer> map) {
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (String key : map.keySet()) {
            sb.append(key);
            int cnt = map.get(key);
            if (cnt > 1) {
                sb.append(String.valueOf(cnt));
            }
            ans.add(sb.toString());
            sb.setLength(0);
        }
        ans.sort((o1, o2) -> o1.compareTo(o2));
        for (String word : ans) {
            sb.append(word);
        }
        return sb.toString();
    }


    public static class Info {
        public Map<String, Integer> map;
        public int index;

        public Info(Map<String, Integer> map, int i) {
            this.map = map;
            index = i;
        }
    }

    // 从 str[i...]往下计算, 遇到字符串终止位置或者右括号就停止
    // 当遇到右括号停止时, 往左多判断一个位置, 尝试多夺取一个数字
    // 返回 两个值:
    // map: 负责的这一段的结果
    // index: 负责的这一段计算到了哪个位置, 如果) 外有数字, 到数字结束的位置
    private Info process(char[] str, int i) {
        Map<String, Integer> map = new HashMap<>();
        int N = str.length;
        int cur = 0;
        StringBuilder sb = new StringBuilder();
        while (i < N && str[i] != ')') {
            if (str[i] == ' ') {
                i++;
                continue;
            }
            if (str[i] >= '0' && str[i] <= '9') { // 数字相当于运算符
                cur = cur * 10 + str[i++] - '0';
            } else if (Character.isLetter(str[i])) {
                if (Character.isUpperCase(str[i])) {
                    cur = cur == 0 ? 1 : cur;
                    getAtomCnt(map, sb.toString(), cur);
                    cur = 0;
                    sb.setLength(0);
                }
                sb.append(str[i++]);
            } else { // 碰到( 开启下一个
                cur = cur == 0 ? 1 : cur;
                getAtomCnt(map, sb.toString(), cur);
                cur = 0;
                sb.setLength(0);

                Info next = process(str, i + 1);
                mergeAtomMap(map, next.map);
                i = next.index + 1;
            }
        }
        cur = cur == 0 ? 1 : cur;
        getAtomCnt(map, sb.toString(), cur);
        cur = 0;
        sb.setLength(0);

        if (i < N && str[i] == ')') {
            i++;
            while (i < N && Character.isDigit(str[i])) {
                cur = cur * 10 + str[i] - '0';
                i++;
            }
            if (cur != 0) {
                mulMap(map, cur);
            }
            i--;
        }

        return new Info(map, i);
    }

    private void mulMap(Map<String, Integer> map, int cur) {
        for (String key : map.keySet()) {
            map.put(key, map.get(key) * cur);
        }
    }

    private void mergeAtomMap(Map<String, Integer> map1, Map<String, Integer> map2) {
        for (String key : map2.keySet()) {
            map1.put(key, map1.getOrDefault(key, 0) + map2.get(key));
        }
    }

    // 把str里的字符加到map里
    private void getAtomCnt(Map<String, Integer> map, String s, int cur) {
        if (s == null || s.length() == 0) {
            return;
        }
        char[] str = s.toCharArray();
        int i = 0;
        StringBuilder atom = new StringBuilder();
        while (i < str.length) {
            if (Character.isUpperCase(str[i])) {
                // 上一个结束
                if (atom.length() != 0) {
                    String key = atom.toString();
                    map.put(key, map.getOrDefault(key, 0) + cur);
                }
                atom.setLength(0);
                atom.append(str[i++]);
            } else {
                atom.append(str[i++]);
            }
        }
        // 最后一个
        String key = atom.toString();
        map.put(key, map.getOrDefault(key, 0) + cur);
    }

    public static void main(String[] args) {
        // String formula = "H2O";
        // String formula = "Mg(OH)2";
        String formula = "K4(ON(SO3)2)2";
        // String formula = "Mg(H2O)N";
        var ans = new Problem_726_CountOfAtoms().countOfAtoms(formula);
        System.out.println(ans);
    }


}
