// package LeetCode;
//
// import java.util.ArrayDeque;
//
// public class Problem_592_FractionAdd {
//
//     public String fractionAddition(String expr) {
//         if (expr == null || expr.length() == 0) {
//             return "";
//         }
//         char[] str = expr.toCharArray();
//         int N = str.length;
//         int up = 0;
//         int down = 0;
//         boolean slash = false;
//         boolean first = false;
//
//         StringBuilder curNum = new StringBuilder();
//         for (int i = 0; i < N; i++) {
//             if (str[i] >= '0' && str[i] <= '9') {
//                 if (slash) {
//                     up += up * 10 + str[i] - '0';
//                 } else {
//                     down += down * 10 + str[i] - '0';
//                 }
//             } else if (str[i] == '/') {
//                 slash = true;
//             } else { // +, -
//                 if(first) {
//
//                 }
//
//                 if(!first) first = true;
//
//
//             }
//         }
//
//     }
// }
