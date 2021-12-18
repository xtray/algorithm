// LeetCode Template
// Created by dvlcis @ 2021/11/19 2:38 下午
// 

#include  <stdio.h>
#include  <iostream>
#include <unordered_map>

using namespace std;

class Problem_397_IntegerReplacement {
public:
    // 暴力
    // 从 n 转换到 1 的最少次数
    int integerReplacement(int n) {
        if (n == 1) {
            return 0;
        }
        if (n & 1) { // 奇数
            int p1 = 2 + integerReplacement((n >> 1) + 1);
            int p2 = 1 + integerReplacement(n - 1);
            return min(p1, p2);
        }
        // n 是偶数
        int p3 = 1 + integerReplacement(n / 2);
        return p3;
    }

    // 傻缓存1 --> 超出时间限制
    // 因为数组有大量的地方用不到
    int integerReplacement1(int n) {
        int* dp; // n --> dp[n-1]
        dp = new int[n];
        memset(dp, -1, sizeof(int[n]));
        dp[0] = 0; // 放的是 0+1, dp[i]放的是 i+1
        return process1(n, dp);
    }

    int process1(int n, int dp[]) { // n 的值放在 n-1位置
        if (dp[n - 1] != -1) {
            return dp[n - 1];
        }
        int ans = INT32_MAX;
        if (n == 1) {
            return dp[0];
        }
        if (n & 1) { // 奇数
            // 可能性 1: + 1 处理, 可能会越界
            int p1 = 2 + process1((n >> 1) + 1, dp);
            int p2 = 1 + process1(n - 1, dp);
            ans = min(p1, p2);
        } else {
            // n 是偶数
            int p3 = 1 + process1(n >> 1, dp);
            ans = p3;
        }
        dp[n - 1] = ans;
        return ans;
    }

    // 傻缓存2
    // 使用 map --> 超出时间限制
    unordered_map<int, int> dp;
    int integerReplacement11(int n) {
        dp[1] = 0;
        return process11(n);
    }

    int process11(int n) { // n 的值放在 n-1位置
        if (dp.count(n)) {
            return dp[n];
        }
        int ans = INT32_MAX;
        if (n == 1) {
            return dp[1];
        }
        if (n & 1) { // 奇数
            // 可能性 1: + 1 处理, 可能会越界
            int p1 = 2 + process11((n >> 1) + 1);
            int p2 = 1 + process11(n - 1);
            ans = min(p1, p2);
        } else {
            // n 是偶数
            int p3 = 1 + process11(n >> 1);
            ans = p3;
        }
        dp[n] = ans;
        return ans;
    }

    // 只有一个变量, 改成动态规划
    int* dp1; // n --> dp[n-1]
    int integerReplacement2(int n) {
        if (n == 1) {
            return 0;
        }
        dp1 = new int[n];
        memset(dp1, 0, sizeof(int[n]));
        // dp[1-1] = 0;
        // dp[i] -- > i+1 变到 1 的最少次数
        // dp[i] : i奇数 dp[i] = 1+dp[i-1]
        //         i 偶数 dp[i] = dp[i/2]
        // 最终答案: dp[n]
        for (int i = 2; i <= n; i++) { // 从 i-1
            if (i & 1) {
                int p1 = 2 + dp1[i >> 1];
                int p2 = 1 + dp1[i - 2];
                dp1[i - 1] = min(p1, p2);
            } else {
                dp1[i - 1] = 1 + dp1[(i >> 1) - 1];
            }
        }
        return dp1[n - 1];
    }
};

// for test
int main() {
    Problem_397_IntegerReplacement *sl = new Problem_397_IntegerReplacement();
//    int n = 65535;
    int n = 100000000;
//    int n = 7;
    int ans = sl->integerReplacement(n);
    cout << ans << endl;
    cout << "=======" << endl;
    int ans1 = sl->integerReplacement11(n);
    cout << ans1 << endl;
    cout << "=======" << endl;
    int ans2 = sl->integerReplacement2(n);
    cout << ans2 << endl;
    return 0;
}