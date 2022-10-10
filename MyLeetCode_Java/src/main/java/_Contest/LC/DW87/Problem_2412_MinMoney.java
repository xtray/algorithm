package _Contest.LC.DW87;

// https://leetcode.cn/problems/minimum-money-required-before-transactions/

public class Problem_2412_MinMoney {


    // https://leetcode.cn/problems/minimum-money-required-before-transactions/solution/by-tsreaper-esbt/
    // 交易分为两类: 赚钱交易, 亏钱交易
    // 整个交易过程中，哪个环节会导致交易失败？只可能是我们需要 cost 的代价，但当前金钱不足 cost 才会失败。
    // 枚举哪个交易 T 会让我们失败
    // 最差情况下，进行交易 T 之前，我们会首先完成其它所有负收益交易，这样才会让我们的当前金钱变得最少。
    public long minimumMoney(int[][] trans) {
        long maxLose = 0; // 最低启动资金, 就是吃掉所有亏损后为0的资金
        // 最差情况, 至少要有这么多钱
        for (int[] tran : trans) {
            maxLose += Math.max(0, tran[0] - tran[1]);
        }
        // 对于亏钱交易, 枚举每一笔交易为最后一笔(失败)的交易
        // 其实是在枚举最后一笔交易
        long ans = 0;
        for (int[] tran : trans) {
            long cur = 0; // 到每一笔交易前的最少启动资金
            // 无论正负交易, 求出当前的最低启动资金
            if (tran[0] > tran[1]) { // 亏损
                // 项目A亏损项目, 需要在总和中减去A的亏损额得到除A以外其他项目的亏损额
                cur = maxLose - (tran[0] - tran[1]); // 执行该笔亏钱交易前的资金
            } else { // 盈利
                // 项目盈利, 最大亏损额不变, 但是需要能启动当前盈利
                cur = maxLose;
            }
            // 熬到当前项目的最低启动资金(都吃掉后, 到当前位0) + 启动当前项目
            ans = Math.max(ans, cur + tran[0]);
        }
        return ans;
    }

    // 比如sum  = 52, 最后一笔 (10, 8) 亏损2
    // 处理前的亏损 sum 为 50, 启动最后一笔需要 50 + 10  = 60
    // 比如sum  = 52, 最后一笔 (1, 3) 盈利2
    // 处理最后这一笔 需要 52 + 1 = 53
    // 可能亏损的情况会更大
}
