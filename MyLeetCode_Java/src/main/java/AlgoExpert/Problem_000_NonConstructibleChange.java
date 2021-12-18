package AlgoExpert;

// https://www.algoexpert.io/questions/Non-Constructible%20Change

import java.util.Arrays;

// 正数数组的最小不可组成和
public class Problem_000_NonConstructibleChange {

    public static int nonConstructibleChange(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 1;
        }

        //定义 dp[i][j]: arr 0~i 随意选择能不能组成 j
        int N = arr.length;
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        boolean[][] dp = new boolean[N][sum + 1];
        dp[0][0] = true;
        dp[0][arr[0]] = true;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= sum; j++) {
                boolean p1 = dp[i - 1][j];
                boolean p2 = (j - arr[i] >= 0) && dp[i - 1][j - arr[i]];
                dp[i][j] = p1 || p2;
            }
        }
        for (int j = 0; j <= sum; j++) {
            if (!dp[N - 1][j]) {
                return j;
            }
        }
        return sum + 1;
    }

    // 最优解
    public static int nonConstructibleChange2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 1;
        }
        Arrays.sort(arr);
        if (arr[0] != 1) {
            return 1;
        }
        // 数组肯定有 1
        int range = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] <= range + 1) { // 注意, 这里是 range +1, 因为 arr[i] 刚好能连上
                range += arr[i];
            } else {
                return range + 1;
            }
        }
        return range + 1;
    }

    public static void main(String[] args) {
//        int[] arr = new int[]{5, 7, 1, 1, 2, 3, 22};
        int[] arr = new int[]{1, 5, 1, 1, 1, 10, 15, 20, 100};
//        int[] arr = new int[]{ 1, 2,5};
        int ans = nonConstructibleChange2(arr);
        System.out.println(ans);
    }
}
