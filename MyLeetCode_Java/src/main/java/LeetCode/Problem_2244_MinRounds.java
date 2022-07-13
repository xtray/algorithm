package LeetCode;

import java.util.Arrays;

public class Problem_2244_MinRounds {

    // 从左往右的尝试模型
    public int minimumRounds(int[] tasks) {
        if (tasks == null || tasks.length == 0) {
            return 0;
        }
        if (tasks.length == 1) {
            return -1;
        }
        // 至少两个
        Arrays.sort(tasks);
        return process(tasks, 0);
    }

    // 从index位置出发做完所有任务的最小轮数
    private int process(int[] tasks, int index) {
        int N = tasks.length;
        if (index >= N) {
            return 0;
        }
        // -1
        if (index == N - 1 || tasks[N - 1] != tasks[N - 2]) {
            return -1;
        }
        int cur = tasks[index];
        int cnt = 0;
        int i = index;
        while (i < N && tasks[i++] == cur) {
            cnt++;
        }
        if (cnt == 1) {
            return -1;
        }
        // cnt >=2
        // 尝试两个消除
        int p1 = Integer.MAX_VALUE;
        int next1 = process(tasks, index + 2);
        if (next1 != -1) {
            p1 = 1 + next1;
        }
        int p2 = Integer.MAX_VALUE;
        if (cnt >= 3) {
            int next = process(tasks, index + 3);
            if (next != -1) {
                p2 = 1 + next;
            }
        }
        return Math.min(p1, p2);
    }

    // 改动态规划, 傻缓存
    public int minimumRounds2(int[] tasks) {
        if (tasks == null || tasks.length == 0) {
            return 0;
        }
        if (tasks.length == 1) {
            return -1;
        }
        // 至少两个
        Arrays.sort(tasks);
        int[] dp = new int[tasks.length + 1];
        Arrays.fill(dp, -1);
        dp[tasks.length] = 0;
        process2(tasks, 0, dp);
        return dp[0];
    }

    // 从index位置出发做完所有任务的最小轮数
    private int process2(int[] tasks, int index, int[] dp) {
        int N = tasks.length;
        if (index >= N) {
            return 0;
        }
        // -1
        if (index == N - 1 || tasks[N - 1] != tasks[N - 2]) {
            return -1;
        }
        if (dp[index] != -1) {
            return dp[index];
        }
        int cur = tasks[index];
        int cnt = 0;
        int i = index;
        while (i < N && tasks[i++] == cur) {
            cnt++;
        }
        if (cnt == 1) {
            return -1;
        }
        // cnt >=2
        // 尝试两个消除
        int p1 = Integer.MAX_VALUE;
        int next1 = process2(tasks, index + 2, dp);
        if (next1 != -1) {
            p1 = 1 + next1;
        }
        int p2 = Integer.MAX_VALUE;
        if (cnt >= 3) {
            int next = process2(tasks, index + 3, dp);
            if (next != -1) {
                p2 = 1 + next;
            }
        }
        int ans = Math.min(p1, p2) == Integer.MAX_VALUE ? -1 : Math.min(p1, p2);
        dp[index] = ans;
        return ans;
    }

    // 改DP
    public int minimumRounds3(int[] tasks) {
        if (tasks == null || tasks.length == 0) {
            return 0;
        }
        if (tasks.length == 1) {
            return -1;
        }
        int N = tasks.length;
        // 至少两个
        Arrays.sort(tasks);
        int[] dp = new int[N + 5];
        Arrays.fill(dp, 0, N, -1);
        if (tasks[N - 1] == tasks[N - 2]) {
            dp[N - 2] = 1;
        }
        // 需要一个从i位置往后多少个当前数字相同的表
        int[] cntMap = new int[N];
        cntMap[N-1] = 1;
        for(int i = N-2; i>=0; i--) {
            if(tasks[i] == tasks[i+1]) {
                cntMap[i] = cntMap[i+1] + 1;
            } else {
                cntMap[i] = 1;
            }
        }

        for (int i = N - 3; i >= 0; i--) {
            // int cur = tasks[i];
            int cnt = cntMap[i];
            int p1 = Integer.MAX_VALUE;
            int p2 = Integer.MAX_VALUE;
            if (cnt >= 2) {
                if (dp[i + 2] != -1) {
                    p1 = 1 + dp[i + 2];
                }
            }
            if (cnt >= 3) {
                if (dp[i + 3] != -1) {
                    p2 = 1 + dp[i + 3];
                }
            }
            int ans = Math.min(p1, p2) == Integer.MAX_VALUE ? -1 : Math.min(p1, p2);
            dp[i] = ans;
        }
        return dp[0];
    }


    public static void main(String[] args) {
        // int[] tasks = {2, 2, 3, 3, 2, 4, 4, 4, 4, 4};
        // int[] tasks = {22, 29, 31, 32, 26, 32, 24, 23, 30, 22, 24, 26}; // -1
        // int[] tasks = {29,26,30,24,28,30,31,31,23,23,23,26,24}; // -1
        // int[] tasks = {2, 3, 3};
        int[] tasks = {2, 2,2, 2, 2, 3};
        var ans = new Problem_2244_MinRounds().minimumRounds3(tasks);
        System.out.println(ans);
    }
}
