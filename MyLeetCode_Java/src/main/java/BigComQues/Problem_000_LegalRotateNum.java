package BigComQues;


import java.util.*;

/**
 * https://leetcode-cn.com/circle/discuss/FsSc9x/
 * 1-N数字的字符串（无前导0） 转180度是否合法 合法就输出 18 - 81
 * follow up: 问了复杂度，刚开始是800个，后面就是很长的字符串，问怎么优化
 *
 */

// 复杂度: 假设数字长度 N, O(5^N), 每一位有 5 种情况
public class Problem_000_LegalRotateNum {

    private static final char[] base = new char[]{'0', '1', '6', '8', '9'};

    public static void legalRotateNumber(String nstr) {

        char[] str = nstr.toCharArray();
        int len = str.length;

        // 0 位: {'1', '6', '8', '9'};
        // >=1 位数
        // 0 位: {'0', '1', '6', '8', '9'};
        // >=1 位上: {'1', '6', '8', '9'};

        // 1~len-1 是全变换, 每一位尝试 base 里的数, 注意 0 的处理
        // 3726 为例
        // 先处理 1~999
        Map<Integer, List<String>> map = genAllLegals(len - 1);
        // 再处理 1000~3726
        getLeft(map, str);
        // 输出 map 里的结果
        genOutput(map);

    }

    private static void genOutput(Map<Integer, List<String>> map) {
        for(List<String> m : map.values()) {
            for(String v : m) {
                System.out.println(v);
            }
        }
        System.out.println();
    }

    // 生成剩余的合法
    // 3726
    // 1000~3726
    private static void getLeft(Map<Integer, List<String>> map, char[] str) {
        int len = str.length;
        List<List<String>> list = new ArrayList<>();
        list.add(new ArrayList<>());
        for(int i = 1; i< base.length; i++) {
            if(base[i] <= str[0]) {
                list.get(0).add(String.valueOf(base[i]));
            }
        }
        for(int i = 1; i< str.length;i++) {
            list.add(new ArrayList<>());
            int j = 1;
            if(i < str.length -1) {
                j = 0;
            }
            for(; j< base.length; j++) {
                if(base[j] <= str[i]) {
                    list.get(i).add(String.valueOf(base[j]));
                } else {
                    break;
                }
            }
        }

        List<String> pre = list.get(0);
        for(int i = 1; i<len;i++) {
            List<String> cur = list.get(i);
            List<String> next = new ArrayList<>();
            for(String p : pre) {
                for(String c: cur) {
                    next.add(p + c);
                }
            }
            pre = next;
        }
        if(!pre.isEmpty()) {
            map.put(len, pre);
        }
    }

    // 1~len 全生成
    private static Map<Integer, List<String>> genAllLegals(int len) {
        List<String> res = new ArrayList<>();
        Map<Integer, List<String>> map = new HashMap<>();
        map.put(1, new ArrayList<>());
        for(int i = 1; i< base.length; i++) {
            map.get(1).add(String.valueOf(base[i]));
        }
        int curLen = 2;
        List<String> pre = new ArrayList<>(map.get(curLen-1));
        // pre.add(0, String.valueOf(base[0]));
        while (curLen <= len) {
            map.put(curLen, new ArrayList<>());
            List<String> withZero = new ArrayList<>();
            List<String> withOutZero = new ArrayList<>();
            for(int i = 0; i< base.length; i++) {
                for(String p : pre) {
                    if(i>=1) {
                        withOutZero.add(base[i] + p );
                    }
                    withZero.add(base[i] + p );
                }
            }
            map.get(curLen).addAll(withOutZero);
            pre = withZero;
            curLen++;
        }
        return map;
    }

    public static void main(String[] args) {
        String N = "7356";
        legalRotateNumber(N);
    }


}
