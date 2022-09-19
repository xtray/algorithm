// package _Simulation.D0812;
//
// import java.util.ArrayList;
// import java.util.List;
//
// public class Problem_1106_ParseBoolExpr {
//
//     public boolean parseBoolExpr(String s) {
//         if (s == null || s.length() == 0) {
//             return false;
//         }
//         char[] str = s.toCharArray();
//
//         boolean ans = false;
//         return process(str, 1, s.charAt(0))[0] == 1;
//     }
//
//     // 从i位置出发, 遇到左括号调递归, 遇到右括号返回
//     // 计算的结果, 计算到的位置
//     private int[] process(char[] str, int index, char expr) {
//         List<Integer> list = new ArrayList<>();
//         int N = str.length;
//         int i = index;
//         for (i = index; i < N && str[i] != ')'; ) {
//             if (str[i] == '(') {
//                 int[] res = process(str, i + 1, str[i - 1]);
//                 list.add(res[0]);
//                 i = res[1] + 1;
//             } else if (str[i] == 't' || str[i] == 'f') {
//                 list.add(str[i] == 't' ? 1 : 0);
//                 i++;
//             } else if (str[i] == ',') {
//                 i++;
//             }
//         }
//         int res = getAnswer(list, expr);
//     }
//
//     private int getAnswer(List<Integer> list, char expr) {
//         int pre = list.get(0);
//
//         for (int i = 0; i<list.size(); i++) {
//
//
//         }
//     }
// }
