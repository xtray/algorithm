package LeetCode;

import java.util.HashSet;

public class Problem_526_BeautifulArrange {

    public int countArrangement(int n) {
        if (n == 1) {
            return 1;
        }
        // 下标1...n
        return process(1, new HashSet<Integer>(), n);
    }

    private int process(int index, HashSet<Integer> set, int n) {
        if (index == n + 1) {
            return 1;
        }
        // 处理index位置
        // index位置的数, 尝试1...n所有没用过的
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (!set.contains(i)) {
                if (i % index == 0 || index % i == 0) {
                    set.add(i);
                    cnt += process(index + 1, set, n);
                    set.remove(i);
                }
            }
        }
        // 如果都不行就是返回0种
        return cnt;
    }

    public int countArrangement1(int n) {
        if (n == 1) {
            return 1;
        }
        return process1(1, 0, n);
    }

    private int process1(int index, int status, int n) {
        if (index == n + 1) {
            return 1;
        }
        // 处理index位置
        // index位置的数, 尝试1...n所有没用过的
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if ((status & (1 << i)) == 0) {
                if (i % index == 0 || index % i == 0) {
                    cnt += process1(index + 1, status | (1 << i), n);
                }
            }
        }
        // 如果都不行就是返回0种
        return cnt;
    }

    public int countArrangement2(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[1 << 16];
        return process2(1, 0, n, dp);
    }

    private int process2(int index, int status, int n, int[] dp) {
        if (index == n + 1) {
            return 1;
        }
        if (dp[status] != 0) {
            return dp[status];
        }
        // 处理index位置
        // index位置的数, 尝试1...n所有没用过的
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if ((status & (1 << i)) == 0) {
                if (i % index == 0 || index % i == 0) {
                    cnt += process2(index + 1, status | (1 << i), n, dp);
                }
            }
        }
        // 如果都不行就是返回0种
        dp[status] = cnt;
        return cnt;
    }

    public static void main(String[] args) {
        int n = 4;
        var ans = new Problem_526_BeautifulArrange().countArrangement(n);
        System.out.println(ans);
    }
}
