package _Contest.LCP.LCP2022_FALL_TEAM;

import java.util.*;

// // dp[i]: 以每一个i结尾的种类有多少种
// int[] dp = new int[N];
// Arrays.fill(dp, 1);
// // dp[i] = maxPos {0, 当前字符i上次出现位置}

public class Problem_C {

    // 以每一个i结尾的种类有多少种
    public int beautifulBouquet(int[] flowers, int cnt) {
        //  char, pos : 字符上次出现的位置
        Map<Integer, List<Integer>> map = new HashMap<>();
        int N = flowers.length;
        int[] endPos = new int[N]; // dp[i]: 以每一个i结尾的种类的开始位置
        endPos[0] = -1;
        map.computeIfAbsent(flowers[0], k -> new ArrayList<>()).add(0);
        long ans = 1;
        int mod = (int) (1e9) + 7;
        for (int i = 1; i < N; i++) {
            int pos = getPrePos(map, flowers[i], cnt);
            pos = Math.max(pos, endPos[i - 1]);
            endPos[i] = pos;
            int gap = i - pos;
            ans = (ans + gap) % mod;
            map.computeIfAbsent(flowers[i], k -> new ArrayList<>()).add(i);
        }
        return (int) ans;
    }

    // 找到num倒数前cnt个出现的位置
    private int getPrePos(Map<Integer, List<Integer>> map, int num, int cnt) {
        List<Integer> list = map.getOrDefault(num, new ArrayList<>());
        int N = list.size();
        return list.isEmpty() || (N - cnt < 0) ? -1 : list.get(N - cnt);
    }

    /**
     * [1,10,1,10,1,10]
     * 2 // 16
     * <p>
     * [83395,48132,48132,48132,48132,48132,10442,48132]
     * [3,1,1,1,1,1,2,1]
     * 1 // 11
     */

    public static void main(String[] args) {
        // int[] flowers = {1, 2, 3, 2};
        // int cnt = 1; // 8
        // int[] flowers = {5, 3, 3, 3};
        // int cnt = 2; // 8
        int[] flowers = {1, 10, 1, 10, 1, 10};
        int cnt = 2; // 18
        // int[] flowers = {3, 1, 1, 1, 1, 1, 2, 1};
        // int cnt = 1; // 11
        var ans = new Problem_C().beautifulBouquet(flowers, cnt);
        System.out.println(ans);
    }
}
