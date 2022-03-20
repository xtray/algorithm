package LeetCode.Unipay;

import java.util.*;

// https://leetcode-cn.com/contest/cnunionpay-2022spring/
// https://leetcode-cn.com/contest/cnunionpay-2022spring/problems/I4mOGz/
// https://leetcode-cn.com/circle/discuss/YfxFdF/ 题解
// https://leetcode-cn.com/circle/discuss/FI7HiK/
// https://leetcode-cn.com/problems/sell-diminishing-valued-colored-balls/

/**
 * Not Finished!!
 */
public class Problem_003_MaxInvest_F {

    /**
     * 二分答案求出买到不少于limit个产品时最低价值的理财产品的价值
     * Price增大, 次数减小, Price减小, 次数变多
     */
    public int maxInvestment(int[] product, int limit) {
        int mod = (int) 1e9 + 7;
        // Price增大, 次数减小, Price减小, 次数变多
        // high 对应下限, low对应上限
        int low = 1;
        int high = (int) 1e7;
        int minPrice = -1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            int cnt = getCnt(product, mid);
            if (cnt < limit) { // 次数不够, 增大次数, 需要减小mid, 调低上限
                high = mid - 1;
            } else { // 次数超过, 需要调高price, 调高下限
                minPrice = mid;
                low = mid + 1;
            }
        }
        long ans = 0;
        // 价格足够低, 都凑不够limit次, 所有累加
        if (high == 0) { // 最后一次while, low==1, high==1, 进入循环, mid==1, 则high == 0
            for (int p : product) {
                ans = (ans + (long) (1 + p) * (long) p / 2 % mod) % mod;
            }
            return (int) ans;
        }
        // minPrice
        int cnt = 0; // 统计的总次数
        ans = 0;
        for (int p : product) {
            if (p >= minPrice) {
                ans = (ans + (long) (p + minPrice) % mod * (long) (p - minPrice + 1) / 2 % mod) % mod;
                cnt += p - minPrice + 1;
            }
        }
        // 减去多算的
        ans = (ans - ((long) (cnt - limit) * (long) minPrice) % mod + mod) % mod;
        return (int) ans;
    }

    // 最低价格为M时, 一共有多少个数
    private int getCnt(int[] product, int M) {
        int cnt = 0;
        for (int p : product) {
            cnt += Math.max(0, p - M + 1);
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] prod = {4, 5, 3};
        int limit = 8; // 26
        // int[] prod = {2, 1, 3};
        // int limit = 20;
        var ans = new Problem_003_MaxInvest_F().maxInvestment(prod, limit);
        System.out.println(ans);
    }


}
