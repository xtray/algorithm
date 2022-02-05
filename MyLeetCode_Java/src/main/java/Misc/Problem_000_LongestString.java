package Misc;

import java.util.*;

/**
 * 给定 3 个数 : A，B，C， 表 示 字 母 a,b,c 的个数 。 使用 a,b,c 拼字符串 ，
 * 不能有连续 3 个字母一样 ，求能拼出来的最长的字符串
 *
 * Example: 1
 *   input:(1, 1, 6)
 *   output: ccaccbcc
 *   ccbccacc 也 是 一 种 答案
 * Example: 2
 *   input:(0, 1, 6)
 *   output: ccbcc
 * Example: 3
 *   input: (0，0，6)
 *   output: cc
 *
 * Limit:
 * A, B, C 的取值范围 [0，100]
 * A+B+C>0
 */
public class Problem_000_LongestString {

    private String ans = "";

    public String longestStringBao0(int A, int B, int C) {
        process(A, B, C, 0, 0, 0, "");
        // System.out.println("ans : " + ans);
        return ans;
    }

    private void process(int a, int b, int c, int curA, int curB, int curC, String path) {
        // base case
        if ((a + b + c == 0) || (b + c == 0 && curA >= 2) || (a + c == 0 && curB >= 2) || (a + b == 0 && curC >= 2)) {
            if (path.length() > ans.length()) {
                ans = path;
            }
        }
        if (a > 0 & curA < 2) {
            process(a - 1, b, c, curA + 1, 0, 0, path + "a");
        }
        if (b > 0 & curB < 2) {
            process(a, b - 1, c, 0, curB + 1, 0, path + "b");
        }
        if (c > 0 && curC < 2) {
            process(a, b, c - 1, 0, 0, curC + 1, path + "c");
        }
    }


    public String longestStringBao(int a, int b, int c) {
        int total = a + b + c;
        int[] count = new int[]{a, b, c};
        StringBuilder path = new StringBuilder();
        return process(0, total, count, path);
    }

    private String process(int index, int len, int[] count, StringBuilder path) {
        if (index == len) {
            if (isValid(path.toString())) {
                return path.toString();
            }
            return "";
        }
        // 每一个位置可以填a,b,c, 或者不填, 一旦不填就返回
        String ans1 = ""; // 选择不填, 到此为止
        if (isValid(path.toString())) {
            ans1 = path.toString();
        }
        String ans2 = "";
        for (int i = 0; i < 3; i++) {// 选择填
            if (count[i] > 0) {
                path.append((char) (i + 'a'));
                count[i]--;
                String res = process(index + 1, len, count, path);
                if (isValid(res)) {
                    ans2 = res.length() > ans2.length() ? res : ans2;
                }
                path.deleteCharAt(path.length() - 1);
                count[i]++;
            }
        }
        if (!ans1.isEmpty() && !ans2.isEmpty()) {
            return ans1.length() >= ans2.length() ? ans1 : ans2;
        }
        if (!ans1.isEmpty()) return ans1;
        if (!ans2.isEmpty()) return ans2;
        return "";
    }

    private boolean isValid(String path) {
        char[] str = path.toCharArray();
        if (str.length <= 2) return true;
        char pre1 = str[0];
        char pre2 = str[1];
        for (int i = 2; i < str.length; i++) {
            if (pre1 == pre2 && str[i] == pre2) {
                return false;
            }
            pre1 = pre2;
            pre2 = str[i];
        }
        return true;
    }


    public String longestString(int a, int b, int c) {
        List<int[]> list = new ArrayList<>();
        if (a > 0) list.add(new int[]{a, 0}); // [count, type]
        if (b > 0) list.add(new int[]{b, 1});
        if (c > 0) list.add(new int[]{c, 2});
        list.sort((o1, o2) -> o2[0] - o1[0]); // 从大到小排序, 至少>=1
        // 从数字最大的开始, 两个一组, 看最多有多少个组
        int group = (list.get(0)[0] + 1) / 2; // +1 向上取整
        int type = list.size();
        StringBuilder[] sb = new StringBuilder[group];
        int fillNum = 0;
        int sum = a + b + c - list.get(0)[0];
        // 先紧着数量最多的开始填充每一个分组, 先消耗数量第一多的
        for (int i = 0; i < group; i++) {
            sb[i] = new StringBuilder();
            // 先填第一多的, 如果有>=2 个, 则按最少两个填
            int count = Math.min(list.get(0)[0], 2);
            if (count > 0) {
                sb[i].append(multiplyString(list.get(0)[1], count));
                list.get(0)[0] -= count;
            }
        }

        // 后面两个级别的数量不可能比第一多的多, 先按 1 个 1 个填充第一组分组的空槽
        while (sum > 0) {
            int field = 0; // 每次最多填了几个 group
            for (int i = 0; i < group; i++) {
                // 填次多的, 1个 1 个,先插空
                for (int j = 1; j < type; j++) {
                    int count = Math.min(list.get(j)[0], 1);
                    if (count > 0) {
                        sum--;
                        field++; // 填的空槽数
                        sb[i].append((char) (list.get(j)[1] + 'a'));
                        list.get(j)[0]--;
                        break;
                    }
                }
            }
            fillNum = Math.max(fillNum, field);
        }

        StringBuilder res = new StringBuilder();
        // fillNum + 1是因为,3 组最多用2个可以隔开
        for (int i = 0; i < Math.min(fillNum + 1, group); i++) {
            res.append(sb[i].toString());
        }
        return res.toString();
    }

    private String multiplyString(int ch, int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            sb.append((char) ('a' + ch));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // int a = 1;
        // int b = 1;
        // int c = 6;
        // int a = 0;
        // int b = 1;
        // int c = 6;
        // int a = 0;
        // int b = 0;
        // int c = 6;
        // int a = 0;
        // int b = 5;
        // int c = 1;
        int a = 1;
        int b = 7;
        int c = 5;
        var ans = new Problem_000_LongestString().longestString(a, b, c);
        var ansBao = new Problem_000_LongestString().longestStringBao0(a, b, c);
        System.out.println(ans);
        System.out.println(ansBao);

        System.out.println("Test Start!");
        int times = 1000;
        int maxLen = 8;
        for (int i = 0; i < times; i++) {
            int aa = 0;
            int bb = 0;
            int cc = 0;
            while (aa + bb + cc == 0) {
                aa = (int) (Math.random() * maxLen);
                bb = (int) (Math.random() * maxLen);
                cc = (int) (Math.random() * maxLen);
            }

            var ans1 = new Problem_000_LongestString().longestString(aa, bb, cc);
            var ans2 = new Problem_000_LongestString().longestStringBao0(aa, bb, cc);
            char[] str1 = ans1.toCharArray();
            char[] str2 = ans2.toCharArray();
            Arrays.sort(str1);
            Arrays.sort(str2);
            if (String.valueOf(str1).compareTo(String.valueOf(str2)) != 0) {
                System.out.println("Ooopps!");
                System.out.println("a: " + aa);
                System.out.println("b: " + bb);
                System.out.println("c: " + cc);
                System.out.println("ans1: " + ans1);
                System.out.println("ans2: " + ans2);
                break;
            }
        }
        System.out.println("Test Done!");
    }
}
