package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_1012_NumberWithDumpDigits {

    // https://leetcode.cn/problems/count-numbers-with-unique-digits/solution/by-ac_oier-6tfl/

    // 统计1...n之间有多少没有重复的数字
    // n分为三类:
    // 1. 长度跟n一样, 最高位比n小的  rest1
    // 2. 长度跟n一样, 最高位跟一样的 rest2
    // 2. 长度比n小的, rest3

    // 返回区间 [0, x]内合法数的个数
    public int dp(int x) {
        List<Integer> numList = getNumList(x);
        int N = numList.size();
        int ans = 0;
        if (N <= 1) {
            return N + 1;
        }
        // 位数和 x 相同（res1 + res2）
        // p: 已经选了的位数, 最高位已经定了, p = 1
        // status: 位信息, 记录选过的数字
        for (int i = N - 1, p = 1, status = 0; i >= 0; i--, p++) {
            int cur = numList.get(i); // 取出最高位
            int cnt = 0;
            // rest1: 从0~最高位-1
            for (int j = cur - 1; j >= 0; j--) {
                if (i == N - 1 && j == 0) {
                    // i是最高位的时候, j不能选0
                    continue;
                }
                if (((status >> j) & 1) == 0) { // j这个数没选过
                    cnt++;
                }
            }
            int a = 10 - p; // 总共可选10个, 减去已经用掉的p个
            int b = a - (N - p) + 1; // 剩余长度
            ans += b <= a ? cnt * f[b][a] : cnt;
            // if (((status >> cur) & 1) == 1) {
            if ( (status & (1 << cur)) != 0) {
                // 当前位置不能选择cur这个数, 只有小的数, 那么都算完了, break
                break;
            }
            // 当前i位置可以选择cur这个数, 那么需要加上下一位的数据
            status |= (1 << cur);
            if (i == 0) {
                ans++; // 最后一步了, 说明x本身合格, +1
            }
        }
        // 位数比 x 少（res3）
        ans += 10;
        for (int i = 2, last = 9; i < N; i++) {
            int cur = last * (10 - i + 1);
            ans += cur;
            last = cur;
        }
        return ans;
    }

    // f[i][j] i(小)...j(大)
    // 代表 i * (i + 1) * ... * (j - 1) * j
    // 是一个排列数, 从 j个数里选则 j - i + 1个数
    static int[][] f = new int[10][10];
    static {
        for (int i = 1; i < 10; i++) {
            for (int j = i; j < 10; j++) {
                int cur = 1;
                // 从i一直 * 到 j
                for (int k = i; k <= j; k++) {
                    cur *= k;
                }
                f[i][j] = cur;
            }
        }
    }

    private List<Integer> getNumList(int n) {
        List<Integer> ans = new ArrayList<>();
        while (n != 0) {
            ans.add(n % 10);
            n /= 10;
        }
        return ans;
    }
}
