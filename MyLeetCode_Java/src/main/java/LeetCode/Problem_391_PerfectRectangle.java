package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class Problem_391_PerfectRectangle {

    // 结论: 如果所有矩形严丝合缝, 那么必有:
    // 顶点四个定点只出现一次, 剩下其它所有点出现偶数次
    // &&
    // 所有矩形面积和==四个顶点确定的矩形面积
    public boolean isRectangleCover(int[][] rectangles) {
        if (rectangles == null || rectangles.length == 0 || rectangles[0] == null || rectangles[0].length == 0) {
            return false;
        }
        int M = 0;
        // 找到最大的 x
        for (int[] rect : rectangles) {
            M = Math.max(M, rect[2]); // 这里的 x 最大值对应列, y 对应行
        }
        M++; // 列数
        // 二维-->一维: (x,y) --> y*M + x
        Map<Integer, Integer> countMap = new HashMap<>();
        // 四个顶点
        int x1 = Integer.MAX_VALUE;
        int y1 = Integer.MAX_VALUE;
        int x2 = Integer.MIN_VALUE;
        int y2 = Integer.MIN_VALUE;
        int area = 0; // 面积
        for (int[] rect : rectangles) {
            // 左下: rect[0], rect[1]
            // 左上: rect[0], rect[3]
            // 右上: rect[2], rect[3]
            // 右下: rect[2], rect[1]
            // 二维映射到一维
            countMap.put(rect[0] + rect[1] * M, countMap.getOrDefault(rect[0] + rect[1] * M, 0) + 1);
            countMap.put(rect[0] + rect[3] * M, countMap.getOrDefault(rect[0] + rect[3] * M, 0) + 1);
            countMap.put(rect[2] + rect[3] * M, countMap.getOrDefault(rect[2] + rect[3] * M, 0) + 1);
            countMap.put(rect[2] + rect[1] * M, countMap.getOrDefault(rect[2] + rect[1] * M, 0) + 1);
            area += (rect[2] - rect[0]) * (rect[3] - rect[1]);
            x1 = Math.min(x1, rect[0]);
            y1 = Math.min(y1, rect[1]);
            x2 = Math.max(x2, rect[2]);
            y2 = Math.max(y2, rect[3]);
        }
        //检测 4 个顶点是否只出现一次
        return checkPoint(x1, y1, x2, y2, M, countMap) && area == (x2 - x1) * (y2 - y1);
    }

    //检测 4 个顶点是否只出现一次, 其它点是否出现偶数次
    private boolean checkPoint(int x1, int y1, int x2, int y2, int M, Map<Integer, Integer> map) {
        if (map.getOrDefault(x1 + y1 * M, 0) != 1 ||
                map.getOrDefault(x1 + y2 * M, 0) != 1 ||
                map.getOrDefault(x2 + y1 * M, 0) != 1 ||
                map.getOrDefault(x2 + y2 * M, 0) != 1) {
            return false;
        }
        map.remove(x1 + y1 * M);
        map.remove(x1 + y2 * M);
        map.remove(x2 + y1 * M);
        map.remove(x2 + y2 * M);
        for (int cnt : map.values()) {
            if ((cnt & 1) != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Problem_391_PerfectRectangle sl = new Problem_391_PerfectRectangle();
//        int[][]rectangles = new int[][]{{0,0,1,1},{0,1,3,2},{1,0,2,2}};
        int[][] rectangles = new int[][]{{0, 0, 1, 1}, {0, 1, 1, 2}, {0, 2, 1, 3}, {0, 3, 1, 4}};
//        int[][]rectangles = new int[][]{{0,0,4,1},{7,0,8,2},{6,2,8,3},{5,1,6,3},{4,0,5,1},{6,0,7,2},{4,2,5,3},{2,1,4,3},{0,1,2,2},{0,2,2,3},{4,1,5,2},{5,0,6,1}};
        boolean ans = sl.isRectangleCover(rectangles);
        System.out.println(ans);
    }
}
