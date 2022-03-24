package LeetCode;

import java.util.HashMap;
import java.util.Map;

// NOTE: 采用两点式直线方程的解法
// https://leetcode-cn.com/problems/check-if-it-is-a-straight-line/solution/zhui-dian-cheng-xian-by-leetcode-solutio-lpt6/

public class Problem_1232_CheckStraightLine {

    public boolean checkStraightLine(int[][] cords) {
        if (cords == null || cords.length == 0 || cords[0] == null || cords[0].length == 0) {
            return false;
        }

        // sameX
        // sameY
        // samePoint, 不含重复的点
        // sameGradient
        int N = cords.length;
        int x = cords[0][0];
        int y = cords[0][1];
        int sameX = 0;
        int sameY = 0;
        int sameGradient = 0;
        Map<Integer, Integer> gradient = new HashMap<>();
        for (int i = 1; i < cords.length; i++) {
            int curX = cords[i][0];
            int curY = cords[i][1];
            if (curX == x) {
                sameX++;
            }
            if (curY == y) {
                sameY++;
            }
            int gapX = curX - x;
            int gapY = curY - y;
            if ((sameX > 0 && gapX != 0) || (sameY > 0 && gapY != 0)) {
                return false;
            }
            int gcd = gcd(gapX, gapY);
            gapX = gapX / gcd;
            gapY = gapY / gcd;
            if (gradient.isEmpty()) {
                gradient.put(gapX, gapY);
            } else {
                if (!gradient.containsKey(gapX) || gradient.get(gapX) != gapY) {
                    return false;
                }
            }

        }
        return true;
    }

    public boolean checkStraightLine2(int[][] cords) {
        if (cords == null || cords.length == 0 || cords[0] == null || cords[0].length == 0) {
            return false;
        }

        // sameX
        // sameY
        // samePoint, 不含重复的点
        // sameGradient
        int N = cords.length;
        int x = cords[0][0];
        int y = cords[0][1];
        int sameX = 0;
        int sameY = 0;
        int[] gradient = null;
        for (int i = 1; i < cords.length; i++) {
            int curX = cords[i][0];
            int curY = cords[i][1];
            if (curX == x) {
                sameX++;
            }
            if (curY == y) {
                sameY++;
            }
            int gapX = curX - x;
            int gapY = curY - y;
            if ((sameX > 0 && gapX != 0) || (sameY > 0 && gapY != 0)) {
                return false;
            }
            int gcd = gcd(gapX, gapY);
            gapX = gapX / gcd;
            gapY = gapY / gcd;
            if (gradient == null) {
                gradient = new int[2];
                gradient[0] = gapX;
                gradient[1] = gapY;
            } else {
                if (gapX != gradient[0] || gapY != gradient[1]) {
                    return false;
                }
            }

        }
        return true;
    }

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    /**
     * 不能用普通的斜率来表示，因为如果x1-x2等于0就会出现除以0的情况
     * 一般式：Ax+By+C=0（AB≠0）
     * 两点式：(y-y1)/(x-x1)=(y-y2)/(x-x2) 　（直线过定点(x1,y1),(x2,y2)）
     */
    public boolean checkStraightLine3(int[][] cords) {
        if (cords == null || cords.length == 0 || cords[0] == null || cords[0].length == 0) {
            return false;
        }
        int gapX = cords[1][0] - cords[0][0];
        int gapY = cords[1][1] - cords[0][1];
        for (int i = 2; i < cords.length; i++) {
            int curX = cords[i][0] - cords[0][0];
            int curY = cords[i][1] - cords[0][1];
            if (gapX * curY - gapY * curX != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] cords = {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}};
        // int[][] cords = {{1, -8}, {2, -3}, {1, 2}};
        // int[][] cords = {{0, 0}, {0, 1}, {0, -1}}; // true
        var ans = new Problem_1232_CheckStraightLine().checkStraightLine(cords);
        System.out.println(ans);
    }
}
