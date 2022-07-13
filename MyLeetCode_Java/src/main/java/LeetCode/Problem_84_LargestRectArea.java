package LeetCode;


// TAG: 单调栈, 相等的时候做错化处理

import java.util.ArrayDeque;

public class Problem_84_LargestRectArea {

    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int N = heights.length;
        // 以每一个i位置做高能扩出来的矩形最大面积
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                int cur = (i - k - 1) * heights[j];
                maxArea = Math.max(maxArea, cur);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int cur = (N - k - 1) * heights[j];
            maxArea = Math.max(maxArea, cur);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = {2, 3};
        var ans = new Problem_84_LargestRectArea().largestRectangleArea(heights);
        System.out.println(ans);
    }

}
