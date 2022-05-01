package BigComQues;

/**
 * K个城市, 放置N个基站, 允许有城市不放基站, 问最多有多少种放置方案
 * 每个城市可以0个, 或者多个, 有多少种方法
 * 例:
 * K=3,N=7, 答案是8, 具体如下
 * 0, 0, 7
 * 0, 1, 6
 * 0, 2, 5
 * 0, 3, 4
 * 1, 1, 5
 * 1, 2, 4
 * 1, 3, 3
 * 2, 2, 3
 * 注: 2-1-3 和 3-1-2属于同一种方案
 * 注: 本题同整数拆分问题, ref: 拉马努金整数拆分问题
 */
public class Problem_000_SplitNumber {

    // N: 基站数
    // K: 城市数
    // N个基站, 最多分布到K个城市, 每个城市可以0个, 或者多个, 有多少种方法
    // 例: N=7, K=3时, 答案为8
    public static int ways(int n, int k) {
        if (n < 0 || k < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return process(k, 0, 0, n);
    }

    // k: 一共k个城市
    // 当前处理到的城市编号是index
    // 上一个拆出来的数是pre, 为了去重用
    // 还剩rest需要去拆
    // 返回拆解的方法数
    private static int process(int k, int index, int pre, int rest) {
        if (index == k) {
            return rest == 0 ? 1 : 0;
        }
        if (pre > rest) {
            return 0;
        }
        int ways = 0;
        // 从第一块可能的所有情况开始尝试, 不能<=pre, 否则就重复了
        for (int first = pre; first <= rest; first++) {
            ways += process(k, index + 1, first, rest - first);
        }
        return ways;
    }

    // 改动态规划
    // index 范围 0~k
    // pre: 0~n
    // rest: 0~n
    // 复杂度 O(K*N^3)
    public static int dp(int n, int k) {
        if (n < 0 || k < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        // dp[index][pre][rest]
        int[][][] dp = new int[k + 1][n + 1][n + 1];
        for (int pre = 0; pre <= n; pre++) {
            dp[k][pre][0] = 1;
        }
        for (int index = k - 1; index >= 0; index--) {
            for (int pre = n; pre >= 0; pre--) {
                for (int rest = pre; rest <= n; rest++) {
                    int ways = 0;
                    // 从第一块可能的所有情况开始尝试
                    // 此处有枚举行为, 用斜率优化的办法减掉一阶,见dp2
                    for (int first = pre; first <= rest; first++) {
                        ways += dp[index + 1][first][rest - first];
                    }
                    dp[index][pre][rest] = ways;
                }
            }
        }
        return dp[0][0][n];
    }

    // 动态规划--斜率优化枚举行为, 复杂度降低一阶
    // 复杂度 O(K*N^2)
    public static int dp2(int n, int k) {
        if (n < 0 || k < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        // dp[index][pre][rest]
        int[][][] dp = new int[k + 1][n + 1][n + 1];
        for (int pre = 0; pre <= n; pre++) {
            dp[k][pre][0] = 1;
        }
        for (int pre = 0; pre <= n; pre++) {
            dp[k - 1][pre][pre] = 1;
        }
        for (int index = k - 1; index >= 0; index--) {
            for (int pre = n - 1; pre >= 0; pre--) {
                for (int rest = pre + 1; rest <= n; rest++) {
                    // 通过观察优化掉枚举for循环
                    dp[index][pre][rest] = dp[index][pre + 1][rest];
                    dp[index][pre][rest] += dp[index + 1][pre][rest - pre];
                }
            }
        }
        return dp[0][0][n];
    }


    public static void main(String[] args) {
        int n = 7;
        int k = 3;
        var ans = ways(n, k);
        var ans2 = dp(n, k);
        var ans3 = dp2(n, k);
        System.out.println(ans);
        System.out.println(ans2);
        System.out.println(ans3);

        int times = 100;
        int stationMax = 50;
        int cityMax = 10;
        System.out.println("测试开始");
        for (int i = 1; i <= times; i++) {
            int station = (int) (Math.random() * stationMax) + 1;
            int city = (int) (Math.random() * cityMax) + 1;
            var res1 = ways(station, city);
            var res2 = dp(station, city);
            var res3 = dp2(station, city);
            if (res1 != res2 || res1 != res3) {
                System.out.println("Wrong Answer!!");
                System.out.printf("Station: %d, City: %d \n", station, city);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
