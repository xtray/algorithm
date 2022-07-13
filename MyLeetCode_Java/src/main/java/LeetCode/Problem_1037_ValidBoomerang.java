package LeetCode;

import java.util.HashMap;
import java.util.Map;

// IMP: 叉乘的公式!!

public class Problem_1037_ValidBoomerang {

    public boolean isBoomerang(int[][] points) {
        if (points == null || points.length == 0 || points[0] == null || points[0].length == 0) {
            return false;
        }
        boolean ans = true;
        int preX = points[0][0];
        int preY = points[0][1];
        int sameP = 1; // 在同一个点上
        int sameX = 0; // 同X
        int sameY = 0; // 同Y
        int sameG = 0; // 同斜率
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int i = 1; i < points.length; i++) {
            int x = points[i][0] - preX;
            int y = points[i][1] - preY;
            if (x == 0 && y == 0) {
                sameP++;
            } else if (x == 0) {
                sameX++;
            } else if (y == 0) {
                sameY++;
            } else { // 普通斜率
                int gcd = gcd(x, y);
                x /= gcd;
                y /= gcd;
                if (!map.containsKey(x)) {
                    map.put(x, new HashMap<>());
                }
                map.get(x).put(y, map.get(x).getOrDefault(y, 0) + 1);
                sameG = Math.max(sameG, map.get(x).get(y));
            }
        }
        if (sameP + sameX == 3 || sameP + sameY == 3 || sameP + sameG == 3) {
            ans = false;
        }
        return ans;
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // 已知三角形三个点坐标求三角形面积
    public boolean isBoomerang2(int[][] points) {
        if (points == null || points.length == 0 || points[0] == null || points[0].length == 0) {
            return false;
        }
        int x1 = points[0][0];
        int x2 = points[1][0];
        int x3 = points[2][0];
        int y1 = points[0][1];
        int y2 = points[1][1];
        int y3 = points[2][1];
        return x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2) != 0;
    }

    // 海伦公式, 不大行, 精度损失!
    // 也可以判断两边长度之和是否等于第三边长度, 判断是否共线
    public boolean isBoomerang3(int[][] points) {
        if (points == null || points.length == 0 || points[0] == null || points[0].length == 0) {
            return false;
        }
        int x1 = points[0][0];
        int x2 = points[1][0];
        int x3 = points[2][0];
        int y1 = points[0][1];
        int y2 = points[1][1];
        int y3 = points[2][1];
        double a = getEdgeLen(x1, y1, x2, y2);
        double b = getEdgeLen(x1, y1, x3, y3);
        double c = getEdgeLen(x2, y2, x3, y3);
        double p = (a + b + c) / 2.0;
        // double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        // return !(s < 1e-9);
        double max = Math.max(a, Math.max(b, c));
        double min = Math.min(a, Math.min(b, c));
        double rest = a == max ? (b == min ? c : b) : b == max ? (a == min ? c : a) : (a == min ? b : a);
        return !(Math.abs((max - min - rest)) < 1e-9);
    }

    private double getEdgeLen(int x1, int y1, int x2, int y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    // https://leetcode.cn/problems/valid-boomerang/solution/by-nehzil-if8z/
    // NOTE: 斜率相等的做法
    public boolean isBoomerang4(int[][] points) {
        if (points == null || points.length == 0 || points[0] == null || points[0].length == 0) {
            return false;
        }
        int k1 = (points[1][1] - points[0][1]) * (points[2][0] - points[0][0]);
        int k2 = (points[2][1] - points[0][1]) * (points[1][0] - points[0][0]);
        return (k1 != k2);
    }

    // 叉乘
    // 假设有a、b、c三个点，并且给出每个点的(x,y)位置
    // 从a到c的向量，在从a到b的向量的哪一侧？
    // 如果a到c的向量，在从a到b的向量右侧，返回正数
    // 如果a到c的向量，在从a到b的向量左侧，返回负数
    // 如果a到c的向量，和从a到b的向量重合，返回0
    public boolean isBoomerang5(int[][] points) {
        if (points == null || points.length == 0 || points[0] == null || points[0].length == 0) {
            return false;
        }
        int[] a = points[0];
        int[] b = points[1];
        int[] c = points[2];
        return ((b[1] - a[1]) * (c[0] - b[0]) - (b[0] - a[0]) * (c[1] - b[1])) != 0;
    }

    public static void main(String[] args) {
        int[][] points = new int[][] {{1,1},{2,3},{3,2}};
        // int[][] points = new int[][]{{1, 1}, {2, 2}, {9999, 9999}};
        // int[][] points = new int[][]{{0, 1}, {0, 1}, {2, 1}};
        var ans = new Problem_1037_ValidBoomerang().isBoomerang3(points);
        System.out.println(ans);
    }

}
