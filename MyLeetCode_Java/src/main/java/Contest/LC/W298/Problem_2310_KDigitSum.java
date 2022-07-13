package Contest.LC.W298;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_2310_KDigitSum {

    public int minimumNumbers(int num, int k) {

        // 构造背包数组
        List<Integer> list = new ArrayList<>();
        if (k == 0) {
            k += 10;
        }
        for (int i = k; i <= num; i += 10) {
            list.add(i);
        }
        int N = list.size();
        Integer[][] dp = new Integer[N + 1][num + 1];
        int res = process(list, 0, num, dp);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int process(List<Integer> list, int i, int rest, Integer[][] dp) {
        if (i == list.size()) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        if (rest < 0) {
            return Integer.MAX_VALUE;
        }
        if (dp[i][rest] != null) {
            return dp[i][rest];
        }
        // 1.不要当前的数
        int p1 = process(list, i + 1, rest, dp);
        // 2.要当前的数, i位置可以重复选, i这里不+1
        int p2 = Integer.MAX_VALUE;
        int next = process(list, i, rest - list.get(i), dp);
        if (next != Integer.MAX_VALUE) {
            p2 = 1 + next;
        }
        dp[i][rest] = Math.min(p1, p2);
        return Math.min(p1, p2);
    }

    public int minimumNumbers1(int num, int k) {
        List<Integer> list = new ArrayList<>(); // 背包数组
        k = k == 0 ? 10 : k; // 背包数组跳过0
        for (int i = k; i <= num; i += 10) {
            list.add(i);
        }
        int N = list.size();
        int[][] dp = new int[N + 1][num + 1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[N][0] = 0;
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= num; j++) {
                int p1 = dp[i + 1][j];
                int p2 = Integer.MAX_VALUE;
                int next = j - list.get(i) >= 0 ? dp[i][j - list.get(i)] : Integer.MAX_VALUE;
                if (next != Integer.MAX_VALUE) {
                    p2 = 1 + next;
                }
                dp[i][j] = Math.min(p1, p2);
            }
        }
        return dp[0][num] == Integer.MAX_VALUE ? -1 : dp[0][num];
    }


    public static void main(String[] args) {
        int num = 0;
        int k = 0;
        // int num = 2998;
        // int k = 9;
        // int num = 10;
        // int k = 0;
        // int num = 1;
        // int k = 1; // 1
        // int num = 9;
        // int k = 3;
        // int num = 5;
        // int k = 1; // 5
        var ans = new Problem_2310_KDigitSum().minimumNumbers(num, k);
        System.out.println(ans);

        // int numMax = 3000;
        // int kMax = 10;
        // for (int num = 0; num <= numMax; num++) {
        //     int k = (int)(Math.random()*kMax);
        //     var ans = new Problem_5218_MinNumbers().minimumNumbers(num, k);
        //     if(ans != -1) {
        //         System.out.println("k: " + k + ", num: "+ num + ", ans: " + ans);
        //     }
        //
        // }

    }
}
