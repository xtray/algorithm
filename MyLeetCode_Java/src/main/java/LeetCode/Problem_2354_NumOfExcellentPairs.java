package LeetCode;

import java.util.HashSet;
import java.util.Set;

public class Problem_2354_NumOfExcellentPairs {

    // long!!

    /**
     * 给定两个数A, B, 记cnt(A)=x, cnt(B)=y 为A,B二进制形式1的个数
     * cnt(A) & cnt(B) = k
     * cnt(A) || cnt(B) = x + y - k
     * cnt(A) & cnt(B) + cnt(A) || cnt(B) = x + y
     */
    // A&B + A||B = A + B
    public long countExcellentPairs(int[] nums, int k) {
        Set<Integer> set = new HashSet<>(); // 去重
        // [个数, 个数有几个]
        int[] cnt = new int[30]; // 1~29个
        for (int num : nums) {
            if (set.contains(num)) continue;
            set.add(num);
            int bits = getBitCnt(num);
            cnt[bits]++;
        }
        long ans = 0;
        // 2^30 > 1e9
        for (int i = 1; i <= 29; i++) {
            for (int j = 1; j <= 29; j++) {
                if (i + j >= k) {
                    ans += (long) cnt[i] * cnt[j];
                }
            }
        }
        return ans;
    }

    private int getBitCnt(int num) {
        int cnt = 0;
        while (num != 0) {
            if ((num & 1) != 0) {
                cnt++;
            }
            num >>= 1;
        }
        return cnt;
    }

    public long countExcellentPairs2(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        int[] cnt = new int[30]; // 1~29
        for (int num : nums) {
            if (!set.contains(num)) {
                set.add(num);
                ++cnt[Integer.bitCount(num)];
            }
        }
        long ans = 0L;

        // for (int i = 0; i <= 29; i++) {
        //     for (int j = Math.max(0, k - i); j <= 29; j++) {
        //         ans += (long) cnt[i] * cnt[j];
        //     }
        // }
        // NOTE: 下面是使用后缀和的优化

        int s = 0;
        for (int j = k; j < 30; ++j) { // j = Math.max(0, k - i), i == 0 ~ 29
            s += cnt[j]; // 后缀和, 所有>=k个
        }
        for (int i = 0; i <= 29; i++) {
            // IMP: 对于每一个i, 合法的j范围是: k-i~29
            // ans += cnt[i]*cnt[j] + cnt[i]*cnt[j+1]...+cnt[i]*cnt[29]
            // 当i+1后, 合法的j范围是: k-i-1 ~29, j的范围扩大(下一次用的)
            ans += (long) cnt[i] * s;
            // 更新下次使用的后缀累加和
            int j = k - i - 1;
            if (j >= 0 && j < 30) { // j<30 : 如果k特别大(1 <= k <= 60), 则j会超过29的范围
                s += cnt[j]; // 为下一次准备的
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        int k = 3;
        var ans = new Problem_2354_NumOfExcellentPairs().countExcellentPairs2(nums, k);
        System.out.println(ans);
    }
}
