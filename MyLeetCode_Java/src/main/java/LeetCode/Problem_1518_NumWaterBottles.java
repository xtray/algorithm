package LeetCode;

public class Problem_1518_NumWaterBottles {
    // 模拟
    public int numWaterBottles(int numBottles, int numExchange) {
        int ans = numBottles;
        while(numBottles >=numExchange) {
            int newBottles = numBottles / numExchange;
            int left = numBottles % numExchange;
            numBottles = newBottles + left;
            ans += newBottles;
        }
        return ans;
    }

    // 每个回合损失的瓶子个数 m - 1, 固定值
    // 当 n 为 m−1 的倍数时，最后一个回合不满足兑换条件
    // 最后至少得有m瓶酒才可以兑换，所以如果最后只剩m-1瓶酒的时候我们就不能换了
    public int numWaterBottles1(int n, int m) {
        int cnt = n/(m-1); // 最大交换次数
        return n % (m - 1) == 0 ? n + cnt - 1 : n + cnt;
    }
}
