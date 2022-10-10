package LeetCode;

import java.util.ArrayDeque;

public class Problem_85_MaxRect {
    public int maximalRectangle(char[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;
        int[] sum = new int[M];
        int maxArea = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i == 0) {
                    sum[j] = matrix[i][j] - '0';
                } else {
                    sum[j] = matrix[i][j] == '1' ? (sum[j] + 1) : 0;
                }
            }
            int area = process(sum);
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }

    // 直方图数组最大值
    private int process(int[] arr) {
        // 单调队列
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        int N = arr.length;
        int maxArea = 0;
        for (int i = 0; i < N; i++) {
            while (!queue.isEmpty() && arr[i] < arr[queue.peekLast()]) {
                int top = queue.pollLast();
                int L = queue.isEmpty() ? -1 : queue.peekLast();
                int R = i;
                int area = (R - 1 - L) * arr[top];
                maxArea = Math.max(maxArea, area);
            }
            queue.addLast(i);
        }
        while (!queue.isEmpty()) {
            int top = queue.pollLast();
            int L = queue.isEmpty() ? -1 : queue.peekLast();
            int R = N;
            int area = (R - 1 - L) * arr[top];
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        // char[][] matrix = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        char[][] matrix = {{'0', '1'}, {'1', '0'}};
        var ans = new Problem_85_MaxRect().maximalRectangle(matrix);
        System.out.println(ans);
    }
}
