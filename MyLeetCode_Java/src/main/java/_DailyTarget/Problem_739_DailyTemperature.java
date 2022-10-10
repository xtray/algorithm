package _DailyTarget;

import java.util.ArrayDeque;

public class Problem_739_DailyTemperature {

    public int[] dailyTemperatures(int[] arr) {
        int N = arr.length;
        int[] ans = new int[N];
        // 栈底大--->小, 碰到大的弹出栈顶结算
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && arr[i] > arr[stack.peekLast()]) {
                int top = stack.pollLast();
                ans[top] = i-top;
            }
            stack.addLast(i);
        }
        return ans;
    }
}
