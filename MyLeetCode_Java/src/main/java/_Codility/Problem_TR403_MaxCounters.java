package _Codility;

import java.util.Arrays;

/**
 * MaxCounters
 * Calculate the values of counters after applying all alternating operations:
 * increase counter by 1; set value of all counters to current maximum.
 * https://app.codility.com/demo/results/training2EVY8X-WWD/
 */

public class Problem_TR403_MaxCounters {

    // TLE
    public int[] solution0(int N, int[] A) {
        // write your code in Java SE 8
        int[] ans = new int[N];
        if (A == null || A.length == 0) {
            return ans;
        }
        int max = 0;
        for (int num : A) {
            if (num <= N) {
                ans[num - 1]++;
                max = Math.max(max, ans[num - 1]);
            } else {
                Arrays.fill(ans, max);
            }
        }
        return ans;
    }

    // TLE
    public int[] solution1(int N, int[] A) {
        // write your code in Java SE 8
        int[] ans = new int[N];
        if (A == null || A.length == 0) {
            return ans;
        }
        int max = 0;
        int add = 0;
        for (int num : A) {
            if (num <= N) {
                if (ans[num - 1] == 0) {
                    ans[num - 1] = add + 1;
                } else {
                    ans[num - 1]++;
                }
                max = Math.max(max, ans[num - 1]);
            } else { // 全部赋值为最大值
                add = max;
                ans = new int[N];
            }
        }
        if (add != 0) {
            for (int i = 0; i < N; i++) {
                ans[i] = ans[i] == 0 ? add : ans[i];
            }
        }

        return ans;
    }


    // IMP: 类似线段树的懒更新思想
    public int[] solution(int N, int[] A) {
        int update = N + 1; // 更新操作的门槛
        int max = 0;
        int preMax = 0; // 最近一次更新操作的最大值
        int[] ans = new int[N];

        for (int cur : A) {
            if (cur == update) { // 更新为最大值操作
                preMax = max; // 此动作后, 所有值均<= max, 且除了max之前, 其他小的都需要更新
            } else {
                int pos = cur - 1;
                if (ans[pos] < preMax) {
                    ans[pos] = preMax + 1;
                } else {
                    ans[pos]++;
                }
                if (ans[pos] > max) {
                    max = ans[pos];
                }
            }
        }
        for (int i = 0; i < N; i++) {
            if (ans[i] < preMax) { // 没有碰过, 没来得及更新的, 刷新为上次更新操作最大值
                ans[i] = preMax;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] A = {3, 4, 4, 6, 1, 6, 4};
        int N = 5;
        var ans = new Problem_TR403_MaxCounters().solution(N, A);
        System.out.println(Arrays.toString(ans));
    }
}
