package AlgoExpert;

import java.util.HashMap;
import java.util.Map;

public class Problem_397_IntegerReplacement {

    // 暴力递归-->通过
    public int integerReplacement(int n) {
        if (n == 1) {
            return 0;
        }
        if((n&1) != 0 ) { // 奇数
            int p1 = 2 + integerReplacement((n>>1) + 1); // 处理溢出
            int p2 = 1 + integerReplacement(n-1);
            return Math.min(p1, p2);
        }
        // 偶数
        return 1 + integerReplacement(n>>1);
    }

    // 傻缓存 --> 低分通过
    public int integerReplacement1(int n) {
        Map<Integer,Integer> dp = new HashMap<>();
        dp.put(1,0);
        return process1(n, dp);

    }

    private int process1(int n, Map<Integer, Integer> dp) {
        if (dp.containsKey(n)) {
            return dp.get(n);
        }
        int ans = Integer.MAX_VALUE;
        if((n&1) != 0 ) { // 奇数
            int p1 = 2 + integerReplacement((n>>1) + 1);
            int p2 = 1 + integerReplacement(n-1);
            ans = Math.min(p1, p2);
        } else {
            // 偶数
            ans = 1 + integerReplacement(n>>1);
        }
        dp.put(n, ans);
        return ans;
    }

    // dp[i] : i-1的数组最小步数 --> 超出内存限制
    public int integerReplacement3(int n) {
        if (n ==1) {
            return 0;
        }

        int[] dp = new int[n]; //n 放在 n-1 位置
        // dp[0] = 0; // 1-->0的位置
        for(int i = 2;i<=n;i++) { // 从 dp[1]开始计算
            if((i&1)!=0) { //奇数
                int p1 = 2 + dp[i>>1]; // +1
                int p2 = 1 + dp[i-2];
                dp[i-1] = Math.min(p1,p2);
            } else { // 偶数
                dp[i-1] = 1 + dp[(i>>1)-1];
            }
        }
        return dp[n-1];
    }
}
