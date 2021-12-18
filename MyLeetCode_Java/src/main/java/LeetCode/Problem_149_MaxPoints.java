package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class Problem_149_MaxPoints {

    public int maxPoints(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        if (points.length <= 2) {
            return points.length;
        }
        // 表示斜率
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        int res = 0;
        // 从每一个点出发构成的直线统计斜率后计算点的个数
        for (int i = 0; i < points.length; i++) {
            map.clear();
            int samePostion = 1; // 共点
            int sameX = 0; // 垂直
            int sameY = 0; // 水平
            int line = 0; // 正常斜率斜线
            for (int j = i + 1; j < points.length; j++) {
                int x = points[j][0] - points[i][0];
                int y = points[j][1] - points[i][1];
                if (x == 0 && y == 0) {
                    samePostion++;
                    continue;
                }
                if (x == 0) {
                    sameX++;
                    continue;
                }
                if (y == 0) {
                    sameY++;
                    continue;
                }
                // 普通斜率
                int gcd = gcd(x, y);
                x = x / gcd;
                y = y / gcd;
                if (!map.containsKey(x)) {
                    map.put(x, new HashMap<>());
                }
                if (!map.get(x).containsKey(y)) {
                    map.get(x).put(y, 0);
                }
                map.get(x).put(y, map.get(x).get(y) + 1);
                line = Math.max(line, map.get(x).get(y));
            }
            res = Math.max(res, Math.max(line, Math.max(sameX, sameY)) + samePostion);
        }
        return res;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
