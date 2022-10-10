package _DailyTarget;

import java.util.ArrayDeque;

public class Problem_84_MaxRectArea {


    // 单调栈
    // 每个位置找比自己小的最近的
    // 栈底小--->大, 当前比栈顶小, 弹出并结算栈顶
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int N = heights.length;
        int area = 0;
        int maxArea = 0;
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peekLast()]) {
                int top = stack.pollLast();
                int L = stack.isEmpty() ? -1 : stack.peekLast();
                int R = i;
                area = heights[top] * (R - L - 1);
                maxArea = Math.max(maxArea, area);
            }
            stack.addLast(i);
        }
        while (!stack.isEmpty()) {
            int top = stack.pollLast();
            int L = stack.isEmpty() ? -1 : stack.peekLast();
            int R = N;
            area = heights[top] * (R - L - 1);
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }
}
