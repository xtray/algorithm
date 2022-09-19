// package _DailyTarget;
//
// import java.util.*;
//
//
// public class Problem_726_CountAtoms {
//
//
//     public static class Info {
//         TreeMap<String, Integer> treeMap = new TreeMap<>();
//         int index; // 计算到的位置
//
//         public Info(TreeMap<String, Integer> map, int idx) {
//             treeMap = map;
//             index = idx;
//         }
//     }
//
//     public String countOfAtoms(String s) {
//         if (s == null || s.length() == 0) {
//             return "";
//         }
//         int N = s.length();
//         char[] str = s.toCharArray();
//         process(str, 0);
//         StringBuilder sb = new StringBuilder();
//
//
//     }
//
//     private Info process(char[] s, int i) {
//         TreeMap<String, Integer> treeMap = new TreeMap<>();
//         int cnt = 0;
//         StringBuilder sb = new StringBuilder();
//         Info info = null;
//         int N = s.length;
//         while (i < N && s[i] != ')') {
//             if (s[i] >= 'A' && s[i] <= 'Z' || s[i] == '(') {
//                 cnt = cnt == 0 ? 1 : cnt;
//                 // 结算上一个原子
//                 if (sb.length() != 0) {
//                     String atom = sb.toString();
//                     treeMap.put(atom, treeMap.getOrDefault(atom, 0) + cnt);
//                     sb.setLength(0);
//                     cnt = 0;
//                 }
//                 if (s[i] == '(') {
//                     info = process(s, i + 1);
//                     // 再预读一个数字
//                     while (i < N && s[i] >= '0' && s[i] <= '9') {
//                         cnt = cnt * 10 + s[i++] - '0';
//                     }
//                     cnt = cnt == 0 ? 1 : cnt;
//
//
//                 } else {
//                     sb.append(s[i++]);
//                 }
//             } else if (s[i] >= '0' && s[i] <= '9') {
//                 cnt = cnt * 10 + s[i++] - '0';
//             } else if (s[i] >= 'a' && s[i] <= 'z') {
//                 sb.append(s[i++]);
//             }
//
//         }
//
//     }
// }
