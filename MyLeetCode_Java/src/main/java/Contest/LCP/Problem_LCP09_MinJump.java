package Contest.LCP;

import java.util.Arrays;

public class Problem_LCP09_MinJump {

    // TLE, 不优化的BFS
    public int minJump(int[] jump) {
        if (jump == null || jump.length == 0) {
            return 0;
        }
        int N = jump.length;
        // BFS
        boolean[] used = new boolean[N];
        int L = 0;
        int R = 0;
        int[] queue = new int[N]; // NOTE: 使用数组做队列
        queue[R++] = 0;
        used[0] = true;
        int move = 0;
        while (L != R) {
            int end = R;
            while (L < end) {
                int cur = queue[L];
                if (cur + jump[cur] >= N) {
                    return move + 1;
                }
                // 可能性1: 往右跳
                if (!used[cur + jump[cur]]) {
                    used[cur + jump[cur]] = true;
                    queue[R++] = cur + jump[cur];
                }
                // 可能性2: 往左跳
                for (int i = 0; i < cur; i++) {
                    if (!used[i]) {
                        used[i] = true;
                        queue[R++] = i;
                    }
                }
                L++;
            }
            move++;
        }
        return -1;
    }

    // 黄汀同学的做法
    // 弄出了时间复杂度O(N)的过程
    // 类似大厂10 Problem_45_JumpGameII
    public int minJump2(int[] jump) {
        int N = jump.length;
        int ans = N;
        int next = jump[0];
        if (next >= N) {
            return 1;
        }
        if (next + jump[next] >= N) {
            return 2;
        }
        // dp[i] : 来到i位置，最少跳几步？
        int[] dp = new int[N + 1];
        Arrays.fill(dp, N);
        // dis[i] : <= i步的情况下，最远能跳到哪？
        int[] dis = new int[N];
        // 如果从0开始向前跳，<=1步的情况下，最远当然能到next
        dis[1] = next;
        // 如果从0开始向前跳，<=2步的情况下，最远可能比next + jump[next]要远，
        // 这里先设置，以后可能更新
        dis[2] = next + jump[next];
        dp[next + jump[next]] = 2;
        int step = 1;
        for (int i = 1; i < N; i++) {
            if (i > dis[step]) {
                step++;
            }
            dp[i] = Math.min(dp[i], step + 1);
            next = i + jump[i];
            if (next >= N) {
                ans = Math.min(ans, dp[i] + 1);
            } else if (dp[next] > dp[i] + 1) {
                dp[next] = dp[i] + 1;
                dis[dp[next]] = Math.max(dis[dp[next]], next);
            }
        }
        return ans;
    }
}
