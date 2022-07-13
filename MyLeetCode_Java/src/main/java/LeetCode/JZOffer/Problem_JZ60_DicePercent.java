// package LeetCode.Jianzhi;
//
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
//
// public class Problem_JZ60_DicePercent {
//
//     public double[] dicesProbability(int n) {
//
//         Map<Integer, Integer> map = new HashMap<>();
//         process(n, 0, 0, map);
//         List<Double> probability = new ArrayList<>();
//
//
//     }
//
//     private void process(int n, int index, int preSum, Map<Integer, Integer> map) {
//         if (index == n) {
//             map.put(preSum, map.getOrDefault(preSum, 0) + 1);
//             return;
//         }
//         for (int i = 1; i <= 6; i++) {
//             process(n, index+1, preSum + i, map);
//         }
//     }
//
// }
