package _Contest.LC.W297;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// PENDING

public class Problem_2305_DistributeCookies_P {

    // // 数据分成K组累加和接近的数
    // public int distributeCookies(int[] cookies, int k) {
    //     if (cookies == null) {
    //         return Integer.MAX_VALUE;
    //     }
    //     int sum = Arrays.stream(cookies).sum();
    //
    //     Arrays.sort(cookies);
    //     int eq = sum / k;
    //     int max = cookies[0];
    //     for (int c : cookies) {
    //         max = Math.max(max, c);
    //     }
    //     // 允许的是从 eq ~ max
    //
    //
    // }
    public class Solution3 {

        int len;
        int limit;

        public int distributeCookies(int[] cookies, int k) {
            this.len = cookies.length;
            this.limit = (1 << len) - 1;
//		Integer[][][] dp = new Integer[k + 1][1 << len][sum + 1];
            Map<Integer, Map<Integer, Map<Integer, Integer>>> dp = new HashMap<>();
            return this.distributeCookies(cookies, k, 0, 0, dp);
        }

        private int distributeCookies(int[] cookies, int k, int sum, int used,
                                      Map<Integer, Map<Integer, Map<Integer, Integer>>> dp) {
            if (k == 1) {
                for (int i = 0; i < len; i++) {
                    int cur = 1 << i;
                    if ((used & cur) == 0) {
                        sum += cookies[i];
                    }
                }
                return sum;
            }
//		if (dp.containsKey(k) && dp.get(k).containsKey(used) && dp.get(k).get(used).containsKey(sum)) {
//			return dp.get(k).get(used).get(sum);
//		}
            int ans1 = Math.max(sum, this.distributeCookies(cookies, k - 1, 0, used, dp));
            int ans2 = Integer.MAX_VALUE;
            for (int i = 0; i < len; i++) {
                int cur = 1 << i;
                if ((used & cur) == 0) {
                    ans2 = Math.min(ans2, this.distributeCookies(cookies, k, sum + cookies[i], used | cur, dp));
                }
            }
            int ans = Math.min(ans1, ans2);
//		Map<Integer, Map<Integer, Integer>> map1 = dp.getOrDefault(k, new HashMap<>());
//		Map<Integer, Integer> map2 = map1.getOrDefault(used, new HashMap<>());
//		map2.put(sum, ans);
//		map1.put(used, map2);
//		dp.put(k, map1);
            return ans;
        }

    }

    public static void main(String[] args) {
        // int[] cookies = {8, 15, 10, 20, 8};
        // int k = 2; // 31
        int[] cookies = {6, 1, 3, 2, 2, 4, 1, 2};
        int k = 3;
        System.out.println(Arrays.stream(cookies).sum());
    }
}
