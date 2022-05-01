// package L_INPRG;
//
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// TODO: W287 有空时候做吧

// public class Problem_6035_BuildingWays {
//     public long numberOfWays(String s) {
//         if (s == null || s.length() == 0) {
//             return 0;
//         }
//         char[] str = s.toCharArray();
//         int N = str.length;
//         return process(str, 0, '2', 3);
//     }
//
//     // 当前来到i位置, 前一个选择是pre, 返回方法数
//     private long process(char[] str, int index, char pre, int left) {
//         if (index == str.length) {
//             return left == 0 ? 1 : 0;
//         }
//         // 可能性1: 要当前
//         long p1 = 0;
//         if (str[index] != pre) {
//             p1 = process(str, index + 1, str[index], left - 1);
//         }
//         // 可能性2: 不要当前
//         long p2 = process(str, index + 1, pre, left);
//         return p1 + p2;
//     }
//
//     // 记忆化搜索的方法
//     public long numberOfWays2(String s) {
//         if (s == null || s.length() == 0) {
//             return 0;
//         }
//         char[] str = s.toCharArray();
//         int N = str.length;
//         long[][][] dp = new long[N + 1][3][4];
//         for(int i = 0; i<3; i++) {
//             dp[N][i][0] = 1L;
//         }
//         for(int left = 1; left <=3; left++) {
//
//         }
//
//
//
//         return process2(str, 0, '2', 3, dp);
//     }
//
//     // 当前来到i位置, 前一个选择是pre, 返回方法数
//     private long process2(char[] str, int index, char pre, int left, Long[][][] dp) {
//         Long res = 0L;
//         if (index == str.length) {
//             res = left == 0 ? 1L : 0L;
//             //dp[index][pre - '0'][left] = res;
//             return res;
//         }
//         // 可能性1: 要当前
//         long p1 = 0;
//         if (str[index] != pre) {
//             p1 = process2(str, index + 1, str[index], left - 1, dp);
//         }
//         // 可能性2: 不要当前
//         long p2 = process2(str, index + 1, pre, left, dp);
//         res = p1 + p2;
//         dp[index][pre - '0'][left] = res;
//         return res;
//     }
//
//     public static void main(String[] args) {
//         String s = "001101";
//         // String s = "11100";
//         var ans = new Problem_6035_BuildingWays().numberOfWays2(s);
//         System.out.println(ans);
//     }
//
// }
