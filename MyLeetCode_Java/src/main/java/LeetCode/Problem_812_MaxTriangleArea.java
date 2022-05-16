package LeetCode;

// NOTE: 学习叉乘的知识

public class Problem_812_MaxTriangleArea {

    public double largestTriangleArea(int[][] points) {
        if (points == null || points.length == 0 || points[0] == null || points[0].length == 0) {
            return 0;
        }
        double ans = 0;
        int N = points.length;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    int area = crossProduct(
                            points[j][0] - points[i][0],
                            points[j][1] - points[i][1],
                            points[k][0] - points[i][0],
                            points[k][1] - points[i][1]);
                    ans = Math.max(ans, Math.abs(0.5 * area));
                }
            }
        }
        return ans;
    }

    // (a, b) (c,d)
    private int crossProduct(int a, int b, int c, int d) {
        return a * d - c * b;
    }
}
