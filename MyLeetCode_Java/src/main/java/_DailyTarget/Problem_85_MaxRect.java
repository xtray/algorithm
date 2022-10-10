package _DailyTarget;

import java.util.ArrayDeque;

public class Problem_85_MaxRect {

    public int maximalRectangle(char[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;
        int[] row = new int[M];
        int maxArea = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                row[j] = matrix[i][j] == '1' ? row[j] + 1 : 0;
            }
            int area = getMaxArea(row);
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }

    private int getMaxArea(int[] arr) {
        int N = arr.length;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int area = 0;
        int maxArea = 0;
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peekLast()]) {
                int top = stack.pollLast();
                int L = stack.isEmpty() ? -1 : stack.peekLast();
                int R = i;
                area = arr[top] * (R - L - 1);
                maxArea = Math.max(maxArea, area);
            }
            stack.addLast(i);
        }
        while (!stack.isEmpty()) {
            int top = stack.pollLast();
            int L = stack.isEmpty() ? -1 : stack.peekLast();
            int R = N;
            area = arr[top] * (R - L - 1);
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }
}
