package LeetCode;

public class Problem_887_SuperEggDrop {


    // 暴力递归
    // k: 鸡蛋个数
    // n: 楼层数
    // 返回最小尝试次数
    public int superEggDrop1(int k, int n) {
        if (k < 1 || n < 1) {
            return 0;
        }
        return process1(n, k);

    }

    // rest: 还需要尝试的楼层数
    // k: 还可以使用的鸡蛋个数
    // 一定要验出最高的不会碎的楼层, 每次都是最差运气
    // (因为要求所有情况都能验证通过, 即使是最差情况也不例外, 所以按最差情况来考虑)
    // 返回至少要几次(选择最差情况下做出的最好选择)
    private int process1(int rest, int k) {
        if (rest == 0) { // 还有 0 层楼
            return 0;
        }
        if (k == 1) {
            return rest; // 还有一个鸡蛋, 几层楼就得尝试几次
        }
        int min = Integer.MAX_VALUE;
        // 尝试所有第一次可能扔到的楼层
        for (int i = 1; i <= rest; i++) {
            int p1 = process1(i - 1, k - 1); // 碎了
            int p2 = process1(rest - i, k);  // 没碎
            min = Math.min(min, Math.max(p1, p2));
        }
        return min + 1;
    }

    // 暴力递归改动态规划
    // n: 还需要尝试的楼层数
    // k: 还可以使用的鸡蛋个数
    public int superEggDrop2(int k, int n) {
        if (k < 1 || n < 1) {
            return 0;
        }
        if (k == 1) {
            return n;
        }
        int[][] dp = new int[n + 1][k + 1];
        // dp[i][j]: 还有 i 层楼要尝试, 还有 j 个鸡蛋, 解决这个问题扔的次数
        // 0 行: 0 层楼, j 个鸡蛋, 无意义
        // 0 列: i 层楼, 0 个鸡蛋, 无意义
        // 1 行: 1 层楼, 不管多少个棋子都是 1 次
        // 1 列: i 层楼, 1 个棋子, 就是层数
        for (int j = 1; j <= k; j++) {
            dp[1][j] = 1;
        }
        for (int i = 1; i <= n; i++) {
            dp[i][1] = i;
        }
        for (int i = 2; i < dp.length; i++) {
            for (int j = 2; j < dp[0].length; j++) {
                int min = Integer.MAX_VALUE;
                // dp[i][j]: 1~i层, 每层都要尝试
                for (int level = 1; level <= i; level++) {
                    int p1 = dp[level - 1][j - 1]; // 碎了
                    int p2 = dp[i - level][j];  // 没碎
                    min = Math.min(min, Math.max(p1, p2));
                }
                dp[i][j] = min + 1;
            }
        }
        return dp[n][k];
    }

    // 四边形不等式优化-->不是最优解
    // dp[i][j]取得的最优解时的第一次扔的层数记在 best[i][j]里
    public int superEggDrop3(int k, int n) {
        if (k < 1 || n < 1) {
            return 0;
        }
        if (k == 1) {
            return n;
        }
        int[][] dp = new int[n + 1][k + 1];
        // best[i][j]: i,j 这个问题中, 最优方案的第一次扔的层数
        int[][] best = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            dp[i][1] = i;
            // best 不存在
        }

        for (int j = 1; j <= k; j++) {
            dp[1][j] = 1;
            best[1][j] = 1;
        }

        for (int i = 2; i < n + 1; i++) {
            for (int j = k; j > 1; j--) { // j 从左往右推
                int ans = Integer.MAX_VALUE;
                int bestChose = -1;
                // dp[i][j]: 1~i层, 每层都要尝试
                // 上+右位置对
                // 上方给下限, 右方给上限
                int down = best[i - 1][j];
                int up = j == k ? i : best[i][j + 1];
                // 只用在 down 和up 中间做枚举
                for (int first = down; first <= up; first++) {
                    int p1 = dp[first - 1][j - 1]; // 碎了
                    int p2 = dp[i - first][j];  // 没碎
                    int cur = Math.max(p1, p2);
                    // 必须小等于, 只是< 过不了, 暴力验证!
                    if (cur <= ans) {
                        ans = cur;
                        bestChose = first;
                    }
                }
                dp[i][j] = ans + 1;
                best[i][j] = bestChose;
            }
        }
        return dp[n][k];
    }

    // 最优解
    // dp[i][j]: 在命运对我最差的情况下, 我有 i 颗棋子, 但我能扔 j 次能搞定多少层楼
    public int superEggDrop4(int k, int n) {
        if (k < 1 || n < 1) {
            return 0;
        }
        if (k == 1) {
            return n;
        }
        // k>=2, n>=2
        int[] dp = new int[k + 1]; // 1~k个棋子
        // 0 列: 扔 0 次, 都是 0, 初始值
        // dp[0]: k 个棋子, 扔 0 次,无效, 不用
        // 从滚动下去, 找对应的列数
        // 例子: 7 颗棋子扔 10 次
        // 依赖 碎了: 6 颗 9次
        //     没碎: 7 颗 9 次
        //     i-1: [ ]
        //     i  : [ ] [x]
        int col = 0; // 列数
        while (true) {
            col++; // 从第1列开始
            int pre = 0;// 上一个格子, 0 行都是 0
            // 从第 1 行开始
            for (int i = 1; i<= k; i++) {
                int tmp = dp[i]; // tmp 就是 dp[i-1]
                dp[i] = dp[i] + pre + 1;
                pre = tmp;
                if (dp[i] >= n) {
                    return col;
                }
            }
        }
    }
}
