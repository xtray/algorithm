package L_INPRG;


import java.util.*;

/**
 * 一开始只有一台clean vm, 可以clone到其他的vm， 所需时间为c，vm数量不限，
 * 然后有一个size N 的array t，代表安装不同的os所需要的时间，
 * 要求把所有os都装到vm的最短时间。每台vm可以装一种os。
 * example： c=3， times=[1, 8, 4, 3]。answer：12
 * 步骤：1. 从一台clean vm clone到两台 2. 其中一台vm开始装i=1的os， 另
 * 一台继续clone。3. 一台开始装i=3的vm，
 * 另一台继续clone。4.‍‌‌‌‌‍‍‍‌‍‌‌‍‌‌‍‌‍‍‍ 两台分别装剩下两个os
 */

public class Problem_000_InstallOS_P {
    // cost: clone 一台vm的时间
    // times: 安装N个OS的需要的时间
    public static int getMinInstallTime(int cost, int[] times) {
        LinkedList<Integer> clones = new LinkedList<>(); // clone机器数组
        List<Integer> avails = new ArrayList<>(); // 可以装os的机器数组
        int N = times.length;
        clones.add(0);
        Arrays.sort(times);
        return process(clones, avails, N, cost, times);
    }

    private static int process(LinkedList<Integer> clones, List<Integer> avails, int N, int cost, int[] times) {
        if (clones.size() + avails.size() == N) {
            // 计算安装时间
            List<Integer> cal = new ArrayList<>();
            cal.addAll(avails);
            cal.addAll(clones);
            Collections.sort(cal);
            int minTime = Integer.MIN_VALUE;
            for (int i = 0; i < N; i++) {
                minTime = Math.max(minTime, cal.get(i) + times[N - 1 - i]);
            }
            return minTime;
        }
        if (clones.size() + avails.size() > N) {
            return Integer.MIN_VALUE;
        }

        int size = clones.size();
        int p1 = Integer.MAX_VALUE;
        int p2 = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            int cur = clones.pollFirst();
            // 可能性1: 继续克隆
            clones.addLast(cur + cost);
            clones.addLast(cur + cost);
            p1 = process(clones, avails, N, cost, times);
            clones.pollLast();
            clones.pollLast();
            // 可能性2: 装OS
            avails.add(cur + cost);
            clones.addLast(cur + cost);
            p2 = process(clones, avails, N, cost, times);
        }
        return Math.min(p1, p2);
    }

    public static void main(String[] args) {
        int[] times = {1, 8, 4, 3};
        int cost = 3;
        var ans = getMinInstallTime(cost, times);
        System.out.println(ans);

    }

    //=====from waPian

    int[] t;

    public int solution(int c, int[] times) {
        int len = times.length;
        this.t = times;
        Arrays.sort(t);
        Integer[][][] dp = new Integer[(len - 1) * 3 + t[len - 1]][len + 1][len];
        return this.find(0, 1, len - 1, dp);
    }

    private int find(int time, int vmsize, int k, Integer[][][] dp) {
        if (vmsize >= k + 1) {
            return time + t[k];
        }
        if (dp[time][vmsize][k] != null) {
            return dp[time][vmsize][k];
        }
        int ans1 = time + t[k];
        int ans2 = Integer.MAX_VALUE;
        for (int i = 0; i < vmsize; i++) {
            ans2 = Math.min(ans2, this.find(time + 3, (vmsize - i) << 1, k - i, dp));
        }
        int ans = Math.max(ans1, ans2);
        dp[time][vmsize][k] = ans;
        return ans;
    }
}
