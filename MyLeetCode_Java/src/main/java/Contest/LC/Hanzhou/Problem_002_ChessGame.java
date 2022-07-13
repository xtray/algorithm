// package Contest.LeetCode.Contest.Hanzhou;
//
// import java.util.Arrays;
// import java.util.TreeMap;
// import java.util.TreeSet;
//
// public class Problem_002_ChessGame {
//
//     // 从每一个1开始计算可以容纳的1的个数
//     public int minSwaps(int[] chess) {
//         int ones = Arrays.stream(chess).sum(); // 1的个数
//         if (ones <= 1) {
//             return 0;
//         }
//         int oneCnt = 0; // 当前遇到的1
//         int zeroCnt = 0; // 当前遇到的1
//         int index = 0;
//         int N = chess.length;
//
//         TreeSet<Integer> set = new TreeSet<>();
//         for (int i = 0; i < N; i++) {
//             if (chess[i] == 1) {
//                 set.add(i);
//             }
//         }
//
//
//         while (index < N && chess[index] != 1) { // 来到第一个1
//             index++;
//         }
//
//         // 尝试每一个1出发
//         while (index < N) {
//             if (chess[index] == 1) {
//                 oneCnt++;
//                 index++;
//                 continue;
//             }
//             // 第一个不是1的位置
//             Integer nextPos = set.ceiling(index);
//
//         }
//
//
//     }
// }
