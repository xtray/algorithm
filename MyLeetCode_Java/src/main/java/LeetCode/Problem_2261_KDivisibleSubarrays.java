package LeetCode;// package Contest.LeetCode.Contest.W291;
//
// import java.util.HashSet;
// import java.util.Set;
//
// public class Problem_6049_KDivisibleSubarrays {
//
//     public int countDistinct(int[] nums, int k, int p) {
//
//         int N = nums.length;
//         int L = 0;
//         int R = 0;
//         int cnt = 0;
//         int subsCnt = 0;
//         Set<String> set = new HashSet<>(); // 去重
//         while (R < N) {
//             if (cnt <= k) { // 子数组个数<= k都可以扩
//                 while (R < N && nums[R] % p != 0) {
//                     R++;
//                 }
//                 if (R == N) {
//                     // 生成L...R所有子数组 入 set
//                     genSubs(nums, L, R, set);
//                     break;
//                 }
//                 genSubs(nums, L, R, set);
//                 // nums[R] % p == 0
//                 cnt++;
//             }
//             if (nums[L] % p == 0) {
//                 cnt--;
//             }
//             L++;
//         }
//
//
//         return set.size();
//     }
//
//
// }
