// package LeetCode;
//
// public class Problem_871_MinRefuelStops {
//
//     public int minRefuelStops(int target, int startFuel, int[][] stations) {
//         if (startFuel >= target) {
//             return 0;
//         }
//         return process(target, startFuel, stations, 0);
//     }
//
//     // cur: 当前到的位置, 跟i相关
//     // rest: 当前剩余的油
//     private int process(int target, int rest, int[][] stations, int i) {
//         int N = stations.length;
//         if (i == N) {
//             if (stations[N - 1][0] + rest >= target) {
//                 return 0;
//             } else {
//                 return -1;
//             }
//         }
//         if (rest <= 0) {
//             return 0;
//         }
//
//         int cur = stations[i][0];
//         int gas = stations[i][1];
//
//
//         // 1. 不要i位置的油
//         int p1 = Integer.MAX_VALUE;
//         int next = process(rest + stations[i][0], stations, i + 1);
//         if (next != -1) {
//             p1 = 1 + next;
//         }
//
//         // 2. 要i位置的油
//         int p2 = Integer.MAX_VALUE;
//         next = process(rest + stations[i][0] - stations[i][1], stations, i + 1);
//         if (next != -1) {
//             p2 = 1 + next;
//         }
//         int res = Math.min(p1, p2);
//         return res == Integer.MAX_VALUE ? -1 : res;
//     }
// }
