package LeetCode;

import java.util.ArrayDeque;

// TAG: 单调栈

public class Problem_1475_FinalPrices {

    public int[] finalPrices(int[] prices) {
        if (prices == null || prices.length == 0) {
            return new int[0];
        }
        int N = prices.length;
        int[] ans = new int[N];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        // 找离自己最近的最小的
        // 栈底-->栈顶
        //  小  -> 大 : 当前值<= 栈顶, 栈顶弹出
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && prices[i] <= prices[stack.peekLast()]) {
                int curPos = stack.pollLast(); // 当前结算位置
                ans[curPos] = prices[curPos] - prices[i];
            }
            stack.addLast(i);
        }
        while (!stack.isEmpty()) {
            int curPos = stack.pollLast(); // 当前结算位置
            ans[curPos] = prices[curPos];
        }
        return ans;
    }
}
