package LeetCode;

import java.util.ArrayDeque;

// TAG: 单调栈, 相等的时候做错化处理

public class Problem_1856_MaxSumMinProduct {

    private static final int mod = (int) 1e9 + 7;

    public int maxSumMinProduct(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        long[] sums = new long[N];
        sums[0] = arr[0];
        for (int i = 1; i < N; i++) {
            sums[i] = sums[i - 1] + arr[i];
        }
        long res = 0;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            // 单调最小栈 栈底到栈顶由小到大
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) { // NOTE: 相等的时候弹出, 错化处理, 不影响答案
                int j = stack.pop();
                long cur = (stack.isEmpty() ? sums[i - 1] : (sums[i - 1] - sums[stack.peek()])) * arr[j];
                res = Math.max(res, cur);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            long cur = (stack.isEmpty() ? sums[N - 1] : (sums[N - 1] - sums[stack.peek()])) * arr[j];
            res = Math.max(res, cur);
        }
        return (int) (res % mod); // NOTE: 要加括号
        // return (int) res % mod; // 错的!!
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 5, 4, 2, 4, 5, 3, 1, 2, 4}; // 50
        var ans = new Problem_1856_MaxSumMinProduct().maxSumMinProduct(arr);
        System.out.println(ans);
    }
}
