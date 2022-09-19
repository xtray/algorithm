package LeetCode;

import java.util.*;

public class Problem_761_SpecialBinString {


    public static class Info {
        public String ans; // 子过程结果
        public int end; // 计算到的位置

        public Info(String a, int e) {
            ans = a;
            end = e;
        }
    }

    public static String makeLargestSpecial(String s) {
        List<String> list = new ArrayList<>();
        int N = s.length();
        // 主过程
        int i = 0;
        while (i < N) {
            // 一上来i位置就是(, 去i+1位置调用子函数
            Info info = process(s, i + 1);
            list.add(info.ans);
            i = info.end + 1;
        }
        StringBuilder builder = new StringBuilder();
        list.sort((a, b) -> b.compareTo(a));
        for (String cur : list) {
            builder.append(cur);
        }
        return builder.toString();
    }

    // 从index位置开始处理, 遇到)或者字符串终止位置停止
    public static Info process(String s, int index) {
        List<String> list = new ArrayList<>(); // 收集本次处理过程的合法对, 并排序
        // index 不能是 ) -> 0
        while (s.charAt(index) != '0') {
            // index ( -> 1
            Info info = process(s, index + 1);
            list.add(info.ans);
            index = info.end + 1;
        }
        StringBuilder builder = new StringBuilder();
        list.sort((a, b) -> b.compareTo(a));
        for (String cur : list) {
            builder.append(cur);
        }
        return new Info("1" + builder.toString() + "0", index);
    }
}
