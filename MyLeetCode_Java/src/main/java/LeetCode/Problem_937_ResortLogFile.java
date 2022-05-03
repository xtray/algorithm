package LeetCode;

import java.util.Arrays;

public class Problem_937_ResortLogFile {

    public static String[] reorderLogFiles(String[] logs) {
        if (logs == null || logs.length == 0) {
            return new String[0];
        }
        Arrays.sort(logs, (log1, log2) -> {
            String[] words1 = log1.split(" ", 2); // NOTE: 分割成两段的split方法
            String[] words2 = log2.split(" ", 2);
            int type1 = words1[1].charAt(0) >= 'a' && words1[1].charAt(0) <= 'z' ? 0 : 1;
            int type2 = words2[1].charAt(0) >= 'a' && words2[1].charAt(0) <= 'z' ? 0 : 1;
            if (type1 != type2) {
                return type1 - type2;
            }
            if (type1 == 0) { //都是字母
                int cmp = words1[1].compareTo(words2[1]); //先比较内容字母split1>split2则返回1，等于返0，小于返-1
                if (cmp != 0) return cmp;
                return words1[0].compareTo(words2[0]);//若内容字母相同则比较标识符
            }
            return 0; // 数字顺序不变
        });
        return logs;
    }

    public static void main(String[] args) {
        String[] logs = {"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};
        var ans = reorderLogFiles(logs);
        for(String log : ans) {
            System.out.print(log + ", ");
        }
        System.out.println();
    }


}
