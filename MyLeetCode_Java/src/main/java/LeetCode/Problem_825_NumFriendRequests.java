package LeetCode;

import java.util.Arrays;

public class Problem_825_NumFriendRequests {


    // https://leetcode-cn.com/problems/friends-of-appropriate-ages/solution/gua-ling-de-peng-you-by-leetcode-solutio-v7yk/
    // 根据题目
    // 1. ages[y] <= 0.5 * ages[x] + 7
    // 2. ages[y] > ages[x]
    // 3. ages[y] > 100 && ages[x] < 100
    // 得知, 2 成立时, 3 一定成立, 因为三个是或的关系, 所以只考虑 1,2 即可
    // 所以 x 加好友的条件需要满足如下布尔表达式：
    // ! ( (ages[y] <= 0.5 * ages[x] + 7) || (ages[y] > ages[x]) )
    // 化简可得：
    // ages[y] > 0.5 * ages[x] + 7 && ages[y] <= ages[x]
    // 即:
    // 0.5 * ages[x] + 7 < ages[y] <= ages[x]
    // 同时存在: 0.5 * ages[x] + 7 < ages[x] ==> ages[x] > 14
    // 根据这个布尔表达式我们知道，x可以添加好友的y是一个范围，可以使用前缀和快速统计一个范围内的人数
    public int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        int N = ages.length;
        int ans = 0;
        // 枚举每一个 x, 找对应 y 的范围
        int L = 0;
        int R = 0;
        for (int i = 0; i < N; i++) {
            if (ages[i] <= 14) continue;
            while (ages[L] <= 0.5 * ages[i] + 7) { // 跳过所有不满的的左边区间
                L++;
            }
            while (R < N && ages[R] <= ages[i]) {
                R++;
            }
            if (R > L) { // R=L, 则 只有 x 自己, 不符合
                ans += R - L - 1; // 因为 ages[i]本身也在内, 所以要多-1
            }
        }
        return ans;
    }

    // 计数排序
    // 题目的限制 1 是 : ages[y1] <= ages[x1] / 2 + 7 是不能发送请求的，
    // 那么满足要求的最小年龄就是 ages[x1] / 2 + 8 了
    public int numFriendRequests2(int[] ages) {
        int[] cnt = new int[121];
        for (int age : ages) { //计数
            ++cnt[age];
        }
        int[] pre = new int[121]; // 前缀和, 0 弃而不用
        for (int i = 1; i <= 120; ++i) {
            pre[i] = pre[i - 1] + cnt[i];
        }
        int ans = 0;
        for (int i = 15; i <= 120; ++i) {
            if (cnt[i] > 0) {
                int bound = (int) (i * 0.5 + 8); // bound~i 是合法的
                ans += cnt[i] * (pre[i] - pre[bound - 1] - 1);
            }
        }
        return ans;
    }

}
