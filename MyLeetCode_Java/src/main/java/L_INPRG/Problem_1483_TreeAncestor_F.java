// package L_INPRG;
//
// import java.util.*;
//
// /**
//  * Note: 倍增法
//  */
//
// // ref: https://leetcode-cn.com/problems/kth-ancestor-of-a-tree-node/solution/acw-bei-zeng-dong-tai-gui-hua-by-acw_weian/
//
// public class Problem_1483_TreeAncestor {
//
//     // dp[i][j]: 跟节点i距离为2^j的祖先节点
//     // dp[i][j] = dp[dp[i][j-1]][j-1]
//     // dp[i][0]: 跟节点i距离为1的节点, 即:直接父亲
//
//     private int[][] dp;
//
//     public Problem_1483_TreeAncestor(int N, int[] partent) {
//         // 2^19 = 524288 > 5*10^5, 最大值为19
//         int M = (int) (Math.log(N) / Math.log(2)) + 1;
//         dp = new int[N][M];
//
//         for (int i = 0; i < N; i++) {
//             Arrays.fill(dp[i], -1);
//             dp[i][0] = partent[i];
//         }
//
//         for (int j = 1; j < M; j++) {
//             for (int i = 0; i < N; i++) {
//                 if (dp[i][j - 1] != -1) {
//                     dp[i][j] = dp[dp[i][j - 1]][j - 1];
//                 }
//             }
//         }
//     }
//
//
//     public int getKthAncestor(int node, int k) {
//         if (k == 0 || node == -1) {
//             return node;
//         }
//         //找到右边开始第一个1的位置
//         int j = (int) (Math.log(k) / Math.log(2));
//         // int j = 0, tmp = k;
//         //
//         // while (tmp > 0 && (tmp & 1) == 0){
//         //     tmp >>= 1;
//         //     j++;
//         // }
//         return getKthAncestor(dp[node][j], k - (1 << j));
//     }
//
//     // PENDING: 迭代法
//     public int getKthAncestor1(int node, int k) {
//         int res = node;
//         int pos = 0;
//         while (k != 0 && res != -1) {
//             if (pos >= dp[res].size()) return -1;
//
//             if ((k & 1) != 0) { // k 的当前二进制位上有1
//                 res = dp[res][pos];
//             }
//             k >>= 1;
//             pos++;
//         }
//         return res;
//     }
// }
