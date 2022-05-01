package LeetCode.Contest.LCUP2022_TEAM;

// https://leetcode-cn.com/problems/PTXy4P/
// LCP 55. 采集果实

public class Problem_001_GetMinTime {


    public int getMinimumTime(int[] time, int[][] fruits, int limit) {
        if (time == null || time.length == 0 ||
                fruits == null || fruits.length == 0) {
            return 0;
        }
        int N = fruits.length;
        int cur = 0;

        for (int i = 0; i < N; i++) {
            int type = fruits[i][0];
            int amount = fruits[i][1];
            int cnt = (amount + limit - 1) / limit;
            cur += time[type] * cnt;
        }
        return cur;
    }

    public static void main(String[] args) {
        // int[] time = {2,3,2};
        // int[][] fruits = {{0,2},{1,4},{2,1}};
        // int limit = 3;
        int[] time = {1};
        int[][] fruits = {{0, 3}, {0, 5}};
        int limit = 2;
        var ans = new Problem_001_GetMinTime().getMinimumTime(time, fruits, limit);
        System.out.println(ans);
    }
}
