package LeetCode;

public class Problem_458_PoorPigs {

    // dp[i][j]: i: 实验次数, j: pig 数, 能解决的水桶有一个毒桶数
    // https://leetcode-cn.com/problems/poor-pigs/solution/gong-shui-san-xie-jin-zhi-cai-xiang-xian-69fl/
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int k = minutesToTest/minutesToDie;
        if(buckets == 0 || k ==0) {
            return 0;
        }
        // 1 一只猪 1 轮有: 1 轮死, 1 轮后不死 两种状态
        // 一只猪 k 轮 有 k+1 种状态
        // 1 轮死, 2 轮死, k 轮死, k+1 生
        // (k+1)^ans >= buckets
        // ans = log_{k+1}buckets --> ans = log buckets / log(k+1) 结果向上取整, 否则不够
        return (int)Math.ceil(Math.log10(buckets)/Math.log10(k+1));
    }
}
