package LeetCode;

public class Problem_2281_TotalStength {

    private static final long mod = (long) 1e9 + 7;

    public int totalStrength(int[] arr) {
        int n = arr.length;
        long preSum = arr[0];
        long[] sumSum = new long[n]; // 前缀和的前缀和
        sumSum[0] = arr[0];
        for (int i = 1; i < n; i++) {
            preSum += arr[i];
            sumSum[i] = (sumSum[i - 1] + preSum) % mod;
        }
        int[] stack = new int[n]; // 数组做栈
        int size = 0; // 栈的大小
        long ans = 0;
        for (int i = 0; i < n; i++) {
            // 新进来的数<= 栈顶需要弹出结算
            while (size > 0 && arr[stack[size - 1]] >= arr[i]) {
                int m = stack[--size];
                int l = size > 0 ? stack[size - 1] : -1; // 看栈里还有没有数
                // l（<当前值，且最近，到不了）        m(当前数，做为最小值)      i(<=当前数，到不了的！)
                ans += magicSum(arr, sumSum, l, m, i);
                ans %= mod;
            }
            stack[size++] = i;
        }
        // 数组没数了, 栈里的东西单独结算
        while (size > 0) {
            int m = stack[--size];
            int l = size > 0 ? stack[size - 1] : -1;
            ans += magicSum(arr, sumSum, l, m, n);
            ans %= mod;
        }
        return (int) ans;
    }

    private long magicSum(int[] arr, long[] sumSum, int l, int m, int r) {
        long left = (long) (m - l) * (sumSum[r - 1] - (m - 1 >= 0 ? sumSum[m - 1] : 0) + mod) % mod;
        // IMP: 做减法的时候需要补一个mod, 因为在求sumSum时候已经mod过, 会造成原本是正数的变成负值
        long right = (long) (r - m) * ((m - 1 >= 0 ? sumSum[m - 1] : 0) - (l - 1 >= 0 ? sumSum[l - 1] : 0) + mod) % mod;
        return (long) arr[m] * ((left - right + mod) % mod);
    }
}
